package fr.cryptolikid.blckchn.blockchain.transaction.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cryptolikid.blckchn.blockchain.transaction.TransactionConstants;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadTransactionException;
import fr.cryptolikid.blckchn.common.interfaces.IValidableModel;
import lombok.*;

/**
 * TransactionMessage
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@Getter
@Setter
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSignedMessage implements IValidableModel {

	@JsonProperty("signedMessage")
	protected String value;

	/**
	 * @throws BadTransactionException if model is not valid
	 */
	@Override
	public void validate() throws BadTransactionException {
		if (value != null && value.length() > TransactionConstants.SIGNED_MESSAGE_MAX_LENGTH)
			throw new BadTransactionException(String.format("Message is noo long ! Size : %s", value.length()));
	}
}
