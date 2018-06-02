package lbp.toolsfordev.blckchn.blockchain.transaction.service.impl;

import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.TransactionException;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.RequestTransactionResource;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.TransactionData;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.TransactionId;
import lbp.toolsfordev.blckchn.blockchain.transaction.service.ITransactionService;
import org.springframework.stereotype.Service;

/**
 * TransactionService
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@Service
public class TransactionService implements ITransactionService {

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
		out.setTransactionId(new TransactionId("645f6sqd4f6qsdw")); //TODO
		return out;
	}
}
