package lbp.toolsfordev.blckchn.blockchain.chain.service;

import lbp.toolsfordev.blckchn.blockchain.block.exceptions.BlockException;
import lbp.toolsfordev.blckchn.blockchain.block.models.Block;
import lbp.toolsfordev.blckchn.blockchain.chain.exceptions.BlockchainException;
import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.TransactionException;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.transactiondatatypes.AmountTransactionData;

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
