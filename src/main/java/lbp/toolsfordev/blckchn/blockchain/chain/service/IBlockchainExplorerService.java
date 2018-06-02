package lbp.toolsfordev.blckchn.blockchain.chain.service;

import lbp.toolsfordev.blckchn.blockchain.block.models.Block;

import java.util.Vector;

public interface IBlockchainExplorerService {

	/**
	 * @return the full blockchain
	 */
	Vector<Block> getFullBlockChain();

	/**
	 * @return the last added/mined block
	 */
	Block getLastAddedBlock();

}
