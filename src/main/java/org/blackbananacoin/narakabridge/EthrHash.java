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

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.util.encoders.Hex;

import com.google.common.io.ByteStreams;

/**
 * @author ref com.google.bitcoin.core.Sha256Hash.java
 * 
 */
public class EthrHash implements Serializable, Comparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2057222708433155179L;
	private byte[] bytes;
	public static final EthrHash ZERO_HASH = new EthrHash(new byte[32]);

	public EthrHash(byte[] bs) {
		checkArgument(bs.length == 32);
		this.bytes = bs;
	}

	public EthrHash(String hexString) {
		checkArgument(hexString.length() == 64);
		this.bytes = Hex.decode(hexString);
	}

	public static EthrHash create(byte[] contents) {
		DigestSHA3 md = new SHA3.Digest256();
		md.update(contents);
		byte[] digest = md.digest();
		return new EthrHash(digest);
	}

	public static EthrHash hashFileContents(File f) throws IOException {
		FileInputStream in = new FileInputStream(f);
		try {
			return create(ByteStreams.toByteArray(in));
		} finally {
			in.close();
		}
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof EthrHash))
			return false;
		return Arrays.equals(bytes, ((EthrHash) other).bytes);
	}

	/**
	 * Hash code of the byte array as calculated by {@link Arrays#hashCode()}.
	 * Note the difference between a SHA256 secure bytes and the type of
	 * quick/dirty bytes used by the Java hashCode method which is designed for
	 * use in bytes tables.
	 */
	@Override
	public int hashCode() {
		// Use the last 4 bytes, not the first 4 which are often zeros in
		// Bitcoin.
		return (bytes[31] & 0xFF) | ((bytes[30] & 0xFF) << 8)
				| ((bytes[29] & 0xFF) << 16) | ((bytes[28] & 0xFF) << 24);
	}

	@Override
	public String toString() {
		return org.bouncycastle.util.encoders.Hex.toHexString(bytes);
	}

	/**
	 * Returns the bytes interpreted as a positive integer.
	 */
	public BigInteger toBigInteger() {
		return new BigInteger(1, bytes);
	}

	public byte[] getBytes() {
		return bytes;
	}

	public EthrHash duplicate() {
		return new EthrHash(bytes);
	}

	public int compareTo(Object o) {
		checkArgument(o instanceof EthrHash);
		int thisCode = this.hashCode();
		int oCode = ((EthrHash) o).hashCode();
		return thisCode > oCode ? 1 : (thisCode == oCode ? 0 : -1);
	}

}
