package fr.cryptolikid.blckchn.blockchain.transaction.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadTransactionException;
import fr.cryptolikid.blckchn.common.interfaces.IValidableModel;
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
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;

	/** signedMessage */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String signedMessage;

	/**
	 * @throws BadTransactionException if model is not valid
	 */
	@Override
	public abstract void validate() throws BadTransactionException;
}
