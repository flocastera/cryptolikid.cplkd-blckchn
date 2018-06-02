package lbp.toolsfordev.blckchn.client.controller;

import lbp.toolsfordev.blckchn.blockchain.chain.service.IBlockchainMutatorService;
import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.TransactionException;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.RequestTransactionResource;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.TransactionId;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.transactiondatatypes.AmountTransactionData;
import lbp.toolsfordev.blckchn.blockchain.transaction.service.ITransactionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	private Log LOG = LogFactory.getLog(ClientController.class);

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
