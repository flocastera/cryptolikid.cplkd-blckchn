package fr.cryptolikid.blckchn.blockchain.chain.service;

import fr.cryptolikid.blckchn.blockchain.block.exceptions.BlockException;
import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.transactiondatatypes.AmountTransactionData;

public interface IBlockchainMutatorService {

	/**
	 * @param transaction @NotNull
	 * @throws TransactionException if transaction is not valid
	 * @throws BlockchainException  if something went wrong with blockchain
	 */
	void newAmountTransaction(FinalTransaction<AmountTransactionData> transaction) throws TransactionException, BlockchainException;

	/**
	 * @param block @NotNull
	 * @throws BlockException
	 * @throws BlockchainException
	 */
	void addBlock(Block block) throws BlockException, BlockchainException;
}
