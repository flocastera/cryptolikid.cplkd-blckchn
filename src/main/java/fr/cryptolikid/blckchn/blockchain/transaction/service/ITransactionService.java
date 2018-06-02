package fr.cryptolikid.blckchn.blockchain.transaction.service;

import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.RequestTransactionResource;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionId;

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
	<T extends TransactionData> FinalTransaction<T> validateRequestTransaction(RequestTransactionResource<T> requestTransactionResource) throws TransactionException;

	/**
	 * @return a valid and unique transaction Id
	 * @throws BlockchainException if blockchain can't be accessed
	 */
	TransactionId getNewTransactionId() throws BlockchainException;

}
