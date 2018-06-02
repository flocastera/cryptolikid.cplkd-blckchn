package fr.cryptolikid.blckchn.blockchain.chain.service;

import fr.cryptolikid.blckchn.blockchain.block.exceptions.BlockException;
import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.transactiondatatypes.AmountTransactionData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * BlockchainService
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 */
@Service
public class BlockchainMutatorService implements IBlockchainMutatorService {

	private Logger LOG = LogManager.getLogger(BlockchainMutatorService.class);

	/** blckchnBlockchain */
	private final IBLCKCHNService blckchnBlockchain;

	@Autowired
	public BlockchainMutatorService(IBLCKCHNService blckchnBlockchain) {
		this.blckchnBlockchain = blckchnBlockchain;
	}

	/**
	 * @param transaction @NotNull
	 * @throws TransactionException if transaction is not valid
	 * @throws BlockchainException  if something went wrong with blockchain
	 */
	@Override
	public void newAmountTransaction(FinalTransaction<AmountTransactionData> transaction) throws TransactionException, BlockchainException {

		transaction.validate();

		this.blckchnBlockchain.addPendingTransaction(transaction);
	}

	/**
	 * @param block @NotNull
	 * @throws BlockException
	 * @throws BlockchainException
	 */
	@Override
	public void addBlock(Block block) throws BlockException, BlockchainException {
		block.validate();

		this.blckchnBlockchain.addBlock(block);
	}
}
