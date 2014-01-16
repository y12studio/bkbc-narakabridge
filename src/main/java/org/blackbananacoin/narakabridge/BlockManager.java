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

import static com.google.common.base.Preconditions.checkState;

public class BlockManager {

	private BlockChain bc;

	private IEthrVm vm;

	private final IDb db;

	public BlockManager(BlockChain bc, IEthrVm vm, IDb db) {
		super();
		this.bc = bc;
		this.vm = vm;
		this.db = db;
	}

	public BlockChain newBlockChain() {
		BlockChain bc = new BlockChain();
		bc.setGenesisBlock(EthrParameters.GenesisBlock);
		bc.setTotalDiff(db.lastKnownTotalDiff());
		bc.setLastBlock(db.lastBlock());
		return bc;
	}

	public void processBlock(Block b) {
		// first validate block
		checkState(validateBlock(b));
		accumelateRewards(b);
		
		// TODO

	}

	public void accumelateRewards(Block b) {
		// Get the coinbase RLP data ?
		String x = b.getState().get(b.getCoinbaseAddr());
		// TODO
	}

	private boolean validateBlock(Block b) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasBlock(String hash) {
		Block block = db.findBlock(hash);
		return block != null;
	}

	/**
	 * @return the genesisBlock
	 */
	public Block getGenesisBlock() {
		return bc.getGenesisBlock();
	}

}
