package org.blackbananacoin.narakabridge.foo;

import org.iq80.leveldb.*;

import static org.iq80.leveldb.impl.Iq80DBFactory.*;

import java.io.*;

public class DbStore {

	public static void testLevelDb(File dbf) throws IOException {
		Options options = new Options();
		options.createIfMissing(true);
		DB db = factory.open(dbf, options);
		try {
			// Use the db in here....
			db.put(bytes("Tampa"), bytes("rocks"));
			String value = asString(db.get(bytes("Tampa")));
			System.out.println(value);
		} finally {
			// Make sure you close the db to shutdown the
			// database and avoid resource leaks.
			db.close();
		}
	}

	public static void main(String[] args) {
		File dbf = new File("testdata/leveldb");
		System.out.println(dbf.getAbsolutePath());
		//checkState(dbf.exists() && dbf.isFile());
		try {
			testLevelDb(dbf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
