package fr.cryptolikid.blckchn.client.controller;

import fr.cryptolikid.blckchn.blockchain.chain.service.IBlockchainMutatorService;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.RequestTransactionResource;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionId;
import fr.cryptolikid.blckchn.blockchain.transaction.models.transactiondatatypes.AmountTransactionData;
import fr.cryptolikid.blckchn.blockchain.transaction.service.ITransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientController
 *
 * @author flocastera
 * @version 1.0
 * @date 01/06/2018
 */
@RestController(value = "/client")
public class ClientController {

	/** LOG */
	private Logger LOG = LogManager.getLogger(ClientController.class);

	/** blockchainMutatorService */
	private final IBlockchainMutatorService blockchainMutatorService;

	/** transactionService */
	private final ITransactionService transactionService;

	@Autowired
	public ClientController(IBlockchainMutatorService blockchainMutatorService, ITransactionService transactionService) {
		this.blockchainMutatorService = blockchainMutatorService;
		this.transactionService = transactionService;
	}

	@RequestMapping(value = "/transactions/new/amount", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TransactionId> newAmountTransaction(
			@RequestBody final RequestTransactionResource<AmountTransactionData> requestTransaction) throws TransactionException {

		FinalTransaction<AmountTransactionData> finalTransaction = this.transactionService.validateRequestTransaction(requestTransaction);

		this.blockchainMutatorService.newAmountTransaction(finalTransaction);

		return ResponseEntity.ok(finalTransaction.getTransactionId());
	}
}
