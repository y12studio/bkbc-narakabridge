/*
 * Copyright 2013 Y12STUDIO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.blackbananacoin.narakabridge;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.util.encoders.Hex;

import com.google.common.primitives.Bytes;

public class Dagger {

	private BigInteger hash;
	private BigInteger xn;

	private static final SecureRandom secureRandom;
	static {
		secureRandom = new SecureRandom();
	}

	public void search(BigInteger shash, BigInteger diff) {
		this.hash = shash;
		BigInteger x = BigInteger.valueOf(2).pow(256);
		BigInteger obj = x.divide(diff);
		boolean found = find(obj);
		System.out.println(found);
	}

	public boolean find(BigInteger obj) {
		boolean r = false;
		for (int i = 0; i < 1000; i++) {
			long ran = secureRandom.nextLong();
			BigInteger res = Eval(BigInteger.valueOf(ran));
			if (res.compareTo(obj) < 0) {
				r = true;
				System.out.println("FOUND " + Hex.toHexString(res.toByteArray()));
				break;
			}
		}
		return r;
	}

	public BigInteger Eval(BigInteger N) {
		BigInteger pow = BigInteger.valueOf(2).pow(26);
		xn = N.divide(pow);
		Digest256 sha = new SHA3.Digest256();
		sha.reset();
		Digest256 d = new SHA3.Digest256();

		byte[] shaData = null;

		for (int k = 0; k < 4; k++) {
			d.reset();
			byte[] dr = Bytes.concat(hash.toByteArray(), xn.toByteArray(),
					N.toByteArray(), BigInteger.valueOf(k).toByteArray());

			BigInteger b = new BigInteger(d.digest());
			BigInteger pk = b.and(BigInteger.valueOf(0x1ffffffL));

			byte[] x = node(9, pk).toByteArray();
			if (shaData != null) {
				shaData = Bytes.concat(shaData, x);
			} else {
				shaData = x;
			}
		}
		sha.update(shaData);
		return new BigInteger(sha.digest());
	}

	public BigInteger node(int L, BigInteger i) {
		if (L == i.intValue()) {
			return hash;
		}
		int m = L == 9 ? 16 : 3;

		Digest256 sha = new SHA3.Digest256();
		sha.reset();

		Digest256 d = new SHA3.Digest256();

		byte[] shaData = null;

		for (int k = 0; k < m; k++) {
			d.reset();
			byte[] dr = Bytes.concat(hash.toByteArray(), xn.toByteArray(),
					BigInteger.valueOf(L).toByteArray(), i.toByteArray(),
					BigInteger.valueOf(k).toByteArray());

			d.update(dr);
			BigInteger b = new BigInteger(d.digest());
			BigInteger pk = b.and(BigInteger
					.valueOf(((1 << ((L - 1) * 3)) - 1)));
			byte[] x = node(L - 1, pk).toByteArray();
			if (shaData != null) {
				shaData = Bytes.concat(shaData, x);
			} else {
				shaData = x;
			}
		}

		sha.update(shaData);
		return new BigInteger(sha.digest());
	}

	public boolean verify(BigInteger vhash, BigInteger diff, BigInteger nonce) {
		boolean r = false;
		this.hash = vhash;
		BigInteger x = BigInteger.valueOf(2).pow(256);
		BigInteger obj = x.divide(diff);
		r = this.Eval(nonce).compareTo(obj) < 0;
		return r;
	}

	public static void main(String[] args) {
		new Dagger().search(BigInteger.valueOf(0x01001L), BigInteger.valueOf(2)
				.pow(26));
	}

}
