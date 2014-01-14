package org.blackbananacoin.narakabridge;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void testBigToByteArray() {
		long target = 123456789L;
		byte[] b2 = BigInteger.valueOf(target).toByteArray();
		String hex2 = Hex.toHexString(b2);
		assertEquals(b2.length,4);
	}


}
