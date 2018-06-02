package fr.cryptolikid.blckchn.blockchain.transaction.service.impl;

import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.chain.service.IBlockchainExplorerService;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.RequestTransactionResource;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionId;
import fr.cryptolikid.blckchn.blockchain.transaction.service.ITransactionService;
import fr.cryptolikid.blckchn.common.hash.Sha256HashesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * TransactionService
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@Service
public class TransactionService implements ITransactionService {

	/** LOG */
	private Logger LOG = LogManager.getLogger(TransactionService.class);

	/** blockchainExplorerService */
	private final IBlockchainExplorerService blockchainExplorerService;

	/** hashesUtils */
	private final Sha256HashesUtils hashesUtils;

	@Autowired
	public TransactionService(IBlockchainExplorerService blockchainExplorerService, Sha256HashesUtils hashesUtils) {
		this.blockchainExplorerService = blockchainExplorerService;
		this.hashesUtils = hashesUtils;
	}

	/**
	 * @param requestTransactionResource @NotNull
	 * @return a valid and final transaction
	 * @throws TransactionException if transaction is not valid
	 */
	@Override
	public <T extends TransactionData> FinalTransaction<T> validateRequestTransaction(RequestTransactionResource<T> requestTransactionResource) throws TransactionException {
		requestTransactionResource.validate();

		FinalTransaction<T> out = new FinalTransaction<>();
		out.setReceiver(requestTransactionResource.getReceiver());
		out.setSender(requestTransactionResource.getSender());
		out.setData(requestTransactionResource.getData());
		out.setTransactionId(getNewTransactionId());
		return out;
	}

	/**
	 * @return a valid and unique transaction Id
	 * @throws BlockchainException if blockchain can't be accessed
	 */
	@Override
	public TransactionId getNewTransactionId() throws BlockchainException {
		Block lastBlock = this.blockchainExplorerService.getLastAddedBlock();

		String txId = String.format("%s%s", lastBlock.getPreviousHash(), UUID.randomUUID().toString());

		return new TransactionId(hashesUtils.hash(txId));
	}
}
