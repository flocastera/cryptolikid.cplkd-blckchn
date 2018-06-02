package lbp.toolsfordev.blckchn.blockchain.transaction.models;

import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.BadTransactionException;
import lbp.toolsfordev.blckchn.common.interfaces.IValidableModel;
import lombok.*;

/**
 * TransactionData
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
public abstract class TransactionData implements IValidableModel {

	/** message */
	private String message;

	/** signedMessage */
	private String signedMessage;

	/**
	 * @throws BadTransactionException if model is not valid
	 */
	@Override
	public abstract void validate() throws BadTransactionException;
}
