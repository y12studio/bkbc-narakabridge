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
import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import org.blackbananacoin.narakabridge.foo.Address;
import org.blackbananacoin.narakabridge.foo.BlockObj;
import org.blackbananacoin.narakabridge.foo.Message;
import org.blackbananacoin.narakabridge.foo.MsgRespond;
import org.blackbananacoin.narakabridge.foo.TxObj;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.util.encoders.Hex;

/**
 * @author user http://ethereum.org/ethereum.html
 * 
 */
public class EthereumClient {

	public enum ParseType {
		BLOCK, TX, MSG,
	}

	/**
	 * Ethereum Client P2P Protocol
	 * 
	 * @param msg
	 * @return
	 */
	public MsgRespond readMessage(Message msg) {
		BlockObj blockCurrent = getBlockCurrent();
		byte[] digest = hashSha3(msg.getData());
		MsgRespond resp = null;
		if (!isExistMessage(digest)) {
			ParseType type = parseMsg(digest);
			switch (type) {
			case MSG:
				resp = buildMsgResp(msg);
				break;
			case TX:
				TxObj txobj = buildTxObj(msg);
				Address addr = txobj.getAddr();
				checkState(txFoundEnough(addr));
				try {
					addLocalTxList(txobj);
					processOntoCurrentBlock(blockCurrent);
					publishTx(txobj);
					resp = buildMsgResp(txobj);
				} catch (Exception ex) {

				}
				break;
			case BLOCK:
				BlockObj block = buildBlockObj(msg);
				// 3 parent check
				BlockObj blockParent = getBlockParent(block.getParentId());
				checkState(isBlockParentInDb(blockParent));
				// 4 
				checkState(isProofOfWorkValid(block,block.getUncles()));
				// 5 GHOST select
				checkState(isBlockParentAndUnclesSameParent(block.getUncles()));
				// 6 timestamp
				checkState(block.getTimestamp()>blockParent.getTimestamp());
				checkState(helpIsFuture15Mins(block.getTimestamp()));
				checkState(isMatch(block.getTotalDifficulty(),block.getNumber()));
				// 7
				List<TxObj> txlist = getTxList();
				checkState(isStateUpdaterOutputNotSame(blockParent,
						txlist, block.getTimestamp(), block.getCoinbase()));
				try {
					addBlockToDb(block);
					publishBlock(block);
					// 6
					int tdInNewBlock = getTotalDifficulty(block);
					
					if (tdInNewBlock > blockCurrent.getTotalDifficulty()) {
						blockCurrent = block;
					}
					
					resp = buildMsgResp(block);
				} catch (Exception ex) {
					
				}
				
				break;
			default:
				break;
			}
		}

		checkNotNull(resp);
		return resp;
	}

	private boolean isMatch(int totalDifficulty, int number) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean helpIsFuture15Mins(long timestamp) {
		// TODO Auto-generated method stub
		return false;
	}

	private BlockObj getBlockParent(String parent) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isProofOfWorkValid(BlockObj block, String uncles) {
		// TODO Auto-generated method stub
		return false;
	}

	private void processOntoCurrentBlock(BlockObj blockCurrent) {
		// TODO Auto-generated method stub
		
	}

	private BlockObj getBlockCurrent() {
		// TODO Auto-generated method stub
		return null;
	}

	private int getTotalDifficulty(BlockObj block) {
		// TODO Auto-generated method stub
		return 0;
	}

	private MsgRespond buildMsgResp(BlockObj block) {
		// TODO Auto-generated method stub
		return null;
	}

	private void publishBlock(BlockObj block) {
		// TODO Auto-generated method stub

	}

	private void addBlockToDb(BlockObj block) {
		// TODO Auto-generated method stub

	}

	private boolean isStateUpdaterOutputNotSame(BlockObj blockParent,
			List<TxObj> txlist, long timestamp, String coinbase) {
		// TODO Auto-generated method stub
		return false;
	}

	private List<TxObj> getTxList() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isBlockParentAndUnclesSameParent(String uncles) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isBlockParentInDb(BlockObj blockParent) {
		// TODO Auto-generated method stub
		return false;
	}

	private BlockObj buildBlockObj(Message msg) {
		// TODO Auto-generated method stub
		return null;
	}

	private MsgRespond buildMsgResp(TxObj txobj) {
		// TODO Auto-generated method stub
		return null;
	}

	private MsgRespond buildMsgResp(Message msg) {
		// TODO Auto-generated method stub
		return null;
	}

	private void publishTx(TxObj txobj) {
		// TODO Auto-generated method stub

	}

	private void addLocalTxList(TxObj txobj) {
		// TODO Auto-generated method stub

	}

	private boolean txFoundEnough(Address addr) {
		return false;
	}

	private TxObj buildTxObj(Message msg) {
		return new TxObj();
	}

	private ParseType parseMsg(byte[] digest) {
		return ParseType.BLOCK;

	}

	private boolean isExistMessage(byte[] digest) {
		return false;
	}
	
	private byte[] hashSha3(byte[] data) {
		DigestSHA3 md = new SHA3.Digest256();
		md.update(data);
		byte[] digest = md.digest();
		System.out.println(Hex.toHexString(digest));
		return digest;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
