package fr.cryptolikid.blckchn.blockchain.transaction.models;

import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadTransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionInfoMissingException;
import fr.cryptolikid.blckchn.common.interfaces.IValidableModel;
import lombok.*;

/**
 * RequestTransactionResource
 *
 * @author flocastera
 * @version 1.0
 * @date 01/06/2018
 */
@Getter
@Setter
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransactionResource<T extends TransactionData> implements IValidableModel {

	/** receiver */
	private String receiver;

	/** sender */
	private String sender;

	/** data */
	private T data;

	/**
	 * @throws BadTransactionException if model is not valid
	 */
	@Override
	public void validate() throws BadTransactionException {
		if( receiver == null || sender == null)
			throw new TransactionInfoMissingException("Receiver and/or sender are missing !");
		data.validate();
	}
}
