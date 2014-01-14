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

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

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
		DigestSHA3 md = new SHA3.Digest256();
		md.update("1234".getBytes());
		byte[] digest = md.digest();
		BlockObj blockCurrent = getBlockCurrent();
		MsgRespond resp = null;
		if (!isExistMessage(digest)) {
			ParseType type = parseMsg(digest);
			switch (type) {
			case BLOCK:
				BlockObj block = buildBlockObj(msg);
				// 3
				checkState(isBlockParentInDb(block.getParent()));
				// 4 GHOST select
				checkState(isBlockParentAndUnclesSameParent(block.getUncles()));
				// 5
				List<TxObj> txlist = getTxList();
				checkState(isStateUpdaterOutputNotSame(block.getParent(),
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
			case MSG:
				resp = buildMsgResp(msg);
				break;
			case TX:
				TxObj txobj = buildTxObj(msg);
				Address addr = txobj.getAddr();
				checkState(txFoundEnough(addr));
				try {
					addLocalTxList(txobj);
					publishTx(txobj);
					resp = buildMsgResp(txobj);
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

	private boolean isStateUpdaterOutputNotSame(String parent,
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

	private boolean isBlockParentInDb(String parent) {
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
