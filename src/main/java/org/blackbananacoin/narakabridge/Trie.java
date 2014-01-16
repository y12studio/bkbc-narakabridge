package org.blackbananacoin.narakabridge;

public class Trie {
	     
     private String root;
     private IDb db;
     

     public String get(String key){
    	 int[] k = compactHexDecode(key);
    	 return getState(root,k);
     }
     
     private int[] compactHexDecode(String key) {
		return null;
	}

	public String getState(String node, int[] key){
		return null;
     }

}
