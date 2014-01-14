package org.blackbananacoin.narakabridge;

public class BlockObj {

	private String parent;
	
	private String uncles;
	
	private String coinbase;
	
	private int totalDifficulty;
	
	
	private long timestamp;

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getUncles() {
		return uncles;
	}

	public void setUncles(String uncles) {
		this.uncles = uncles;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getCoinbase() {
		return coinbase;
	}

	public void setCoinbase(String coinbase) {
		this.coinbase = coinbase;
	}

	public int getTotalDifficulty() {
		return totalDifficulty;
	}

	public void setTotalDifficulty(int totalDifficulty) {
		this.totalDifficulty = totalDifficulty;
	}
}
