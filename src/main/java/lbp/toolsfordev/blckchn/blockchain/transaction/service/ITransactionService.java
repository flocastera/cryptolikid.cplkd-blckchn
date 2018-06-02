package lbp.toolsfordev.blckchn.blockchain.transaction.service;

import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.TransactionException;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.RequestTransactionResource;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.TransactionData;

/**
 * ITransactionService
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
public interface ITransactionService {

	/**
	 * @param requestTransactionResource @NotNull
	 * @return a valid and final transaction
	 * @throws TransactionException if transaction is not valid
	 */
	public <T extends TransactionData> FinalTransaction<T> validateRequestTransaction(RequestTransactionResource<T> requestTransactionResource) throws TransactionException;

}
