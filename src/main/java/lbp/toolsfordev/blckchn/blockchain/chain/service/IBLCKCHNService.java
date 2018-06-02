package lbp.toolsfordev.blckchn.blockchain.chain.service;

import lbp.toolsfordev.blckchn.blockchain.block.exceptions.BlockException;
import lbp.toolsfordev.blckchn.blockchain.block.models.Block;
import lbp.toolsfordev.blckchn.blockchain.chain.exceptions.BlockchainException;
import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.TransactionException;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;

import java.util.Vector;

interface IBLCKCHNService {

	/**
	 * @return Blockchain length
	 */
	int getCurrentIndex();

	/**
	 * @return Transactions that haven't been included in blocks
	 */
	Vector<FinalTransaction> getPendingTransactions();

	/**
	 * @param transaction @NotNull
	 * @throws TransactionException if transaction not added
	 */
	void addPendingTransaction(FinalTransaction transaction) throws TransactionException;

	/**
	 * Delete all pending transactions. Used when adding new valid block to blockchain
	 */
	void clearPendingTransactions();

	/**
	 * @param block @NotNull Block will be added to blockchain
	 * @throws BlockchainException if blockchain not updated
	 * @throws BlockException if block is invalid
	 */
	void addBlock(Block block) throws BlockchainException, BlockException;

	/**
	 * @return the last element added to active blockchain
	 */
	Block getLastBlockAddedInBlockchain();

	/**
	 * @return the full blockchain
	 */
	Vector<Block> getFullBlockChain();
}
