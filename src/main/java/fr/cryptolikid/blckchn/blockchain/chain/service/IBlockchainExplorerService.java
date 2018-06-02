package fr.cryptolikid.blckchn.blockchain.chain.service;

import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;

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

	/**
	 *
	 * @return pending transactions
	 */
	Vector<FinalTransaction<? extends TransactionData>> getPendingTransactions();

}
