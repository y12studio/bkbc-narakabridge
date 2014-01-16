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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndex;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECPoint;


public class EthKey {

	private static final SecureRandom secureRandom;
	private static final ECDomainParameters ecParams;
	private BigInteger priv;
	private byte[] pub;
	private long creationTimeSeconds;
	private byte[] pubAddr;

	static {
		X9ECParameters params = SECNamedCurves.getByName("secp256k1");
		ecParams = new ECDomainParameters(params.getCurve(), params.getG(),
				params.getN(), params.getH());
		secureRandom = new SecureRandom();
	}

	public EthKey(byte[] seed) {
		priv = new BigInteger(1, Utils.sha3(seed));
		pub = privateToPublicKey(priv);
		checkNotNull(pub);
		checkPositionIndex(19, pub.length);
		pubAddr = new byte[20];
		System.arraycopy(pub, pub.length - 20, pubAddr, 0, 20);
		setCreationTimeSeconds(new Date().getTime() / 1000);
	}

	public EthKey() {
		this(secureRandom.generateSeed(32));
	}

	public static byte[] privateToPublicKey(BigInteger privKey) {
		ECPoint point = ecParams.getG().multiply(privKey);
		return point.getEncoded();
	}

	public byte[] getAddr() {
		return pubAddr;
	}

	public byte[] getPub() {
		return pub;
	}

	public void setPub(byte[] pub) {
		this.pub = pub;
	}

	public long getCreationTimeSeconds() {
		return creationTimeSeconds;
	}

	public void setCreationTimeSeconds(long creationTimeSeconds) {
		this.creationTimeSeconds = creationTimeSeconds;
	}
}
