package lbp.toolsfordev.blckchn.blockchain.chain.service;

import lbp.toolsfordev.blckchn.blockchain.block.models.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Vector;

@Service
public class BlockchainExplorerService implements IBlockchainExplorerService {

	/** blckchnBlockchain */
	private final IBLCKCHNService blckchnBlockchain;

	@Autowired
	public BlockchainExplorerService(IBLCKCHNService blckchnBlockchain) {
		this.blckchnBlockchain = blckchnBlockchain;
	}

	/**
	 * @return the full blockchain
	 */
	@Override
	public Vector<Block> getFullBlockChain() {
		return this.blckchnBlockchain.getFullBlockChain();
	}

	/**
	 * @return the last added/mined block
	 */
	@Override
	public Block getLastAddedBlock() {
		return this.blckchnBlockchain.getLastBlockAddedInBlockchain();
	}
}
