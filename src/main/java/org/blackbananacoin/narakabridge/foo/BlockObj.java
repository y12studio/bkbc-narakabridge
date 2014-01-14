package org.blackbananacoin.narakabridge.foo;

public class BlockObj {
	
	private int number;

	private String parentId;
	
	private String uncles;
	
	private String coinbase;
	
	private int totalDifficulty;
		
	private long timestamp;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
