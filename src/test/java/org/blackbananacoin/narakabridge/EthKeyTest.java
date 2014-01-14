package org.blackbananacoin.narakabridge;

import static org.junit.Assert.*;

import java.security.SecureRandom;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Before;
import org.junit.Test;

public class EthKeyTest {

	private EthKey key;
	private String pubHex;

	@Before
	public void setUp() throws Exception {
		key = new EthKey();
		pubHex = Hex.toHexString(key.getPub());
		System.out.println(pubHex);
	}

	@Test
	public void testGetAddr() {
		String addr = Hex.toHexString(key.getAddr());
		System.out.println(addr);
		assertTrue(pubHex.indexOf(addr) > 0);
	}

}
