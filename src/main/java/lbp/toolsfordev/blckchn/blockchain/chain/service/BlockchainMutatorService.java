package lbp.toolsfordev.blckchn.blockchain.chain.service;

import lbp.toolsfordev.blckchn.blockchain.block.exceptions.BlockException;
import lbp.toolsfordev.blckchn.blockchain.block.models.Block;
import lbp.toolsfordev.blckchn.blockchain.chain.exceptions.BlockchainException;
import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.TransactionException;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.RequestTransactionResource;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.transactiondatatypes.AmountTransactionData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 * BlockchainService
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 */
@Service
public class BlockchainMutatorService implements IBlockchainMutatorService {

	private Log LOG = LogFactory.getLog(BlockchainMutatorService.class);

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
	}
}
