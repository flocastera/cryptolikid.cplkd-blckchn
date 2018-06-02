package fr.cryptolikid.blckchn.blockchain.chain.service;

import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return this.blckchnBlockchain.getLastBlockAddedInBlockchain().toBuilder().build();
	}

	/**
	 * @return
	 */
	@Override
	public Vector<FinalTransaction<? extends TransactionData>> getPendingTransactions() {
		return this.blckchnBlockchain.getPendingTransactions();
	}
}
