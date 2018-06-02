package fr.cryptolikid.blckchn.blockchain.chain.service;

import fr.cryptolikid.blckchn.blockchain.block.exceptions.BlockException;
import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;

import java.util.Vector;

interface IBLCKCHNService {

	/**
	 * @return Blockchain length
	 */
	int getCurrentIndex();

	/**
	 * @return Transactions that haven't been included in blocks
	 */
	Vector<FinalTransaction<? extends TransactionData>> getPendingTransactions();

	/**
	 * @param transaction @NotNull
	 * @throws TransactionException if transaction not added
	 */
	void addPendingTransaction(FinalTransaction<? extends TransactionData> transaction) throws TransactionException, TransactionException;

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
