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
import java.util.List;

public class Block {
	
	private long number;
	private EthrHash prevBlockHash;
	private List<Block> uncles;
	private byte[] uncleSha;
	
	private String coinbaseAddr;
	
	private Trie state;
	
	private BigInteger difficulty;
	private long time;
	
	// Block Nonce for verification
	private BigInteger nonce;
	
	private List<Transaction> transactions;
	
    private byte[] txSha;
	
    /**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}
	/**
	 * @return the prevBlockHash
	 */
	public EthrHash getPrevBlockHash() {
		return prevBlockHash;
	}
	/**
	 * @param prevBlockHash the prevBlockHash to set
	 */
	public void setPrevBlockHash(EthrHash prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
	}
	/**
	 * @return the uncles
	 */
	public List<Block> getUncles() {
		return uncles;
	}
	/**
	 * @param uncles the uncles to set
	 */
	public void setUncles(List<Block> uncles) {
		this.uncles = uncles;
	}
	/**
	 * @return the uncleSha
	 */
	public byte[] getUncleSha() {
		return uncleSha;
	}
	/**
	 * @param uncleSha the uncleSha to set
	 */
	public void setUncleSha(byte[] uncleSha) {
		this.uncleSha = uncleSha;
	}
	/**
	 * @return the coinbaseAddr
	 */
	public String getCoinbaseAddr() {
		return coinbaseAddr;
	}
	/**
	 * @param coinbaseAddr the coinbaseAddr to set
	 */
	public void setCoinbaseAddr(String coinbaseAddr) {
		this.coinbaseAddr = coinbaseAddr;
	}
	/**
	 * @return the state
	 */
	public Trie getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Trie state) {
		this.state = state;
	}
	/**
	 * @return the difficulty
	 */
	public BigInteger getDifficulty() {
		return difficulty;
	}
	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(BigInteger difficulty) {
		this.difficulty = difficulty;
	}
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * @return the nonce
	 */
	public BigInteger getNonce() {
		return nonce;
	}
	/**
	 * @param nonce the nonce to set
	 */
	public void setNonce(BigInteger nonce) {
		this.nonce = nonce;
	}
	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}
	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	/**
	 * @return the txSha
	 */
	public byte[] getTxSha() {
		return txSha;
	}
	/**
	 * @param txSha the txSha to set
	 */
	public void setTxSha(byte[] txSha) {
		this.txSha = txSha;
	}

}
