package fr.cryptolikid.blckchn.blockchain.transaction.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadTransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadAddressException;
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
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Address receiver;

	/** sender */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Address sender;

	/** data */
	private T data;

	/**
	 * @throws BadTransactionException if model is not valid
	 * @throws BadAddressException
	 */
	@Override
	public void validate() throws BadTransactionException, BadAddressException {
		receiver.validate();
		sender.validate();
		data.validate();
	}

	@JsonProperty(value = "receiver", access = JsonProperty.Access.READ_ONLY)
	public String receiver(){
		return receiver.getValue();
	}

	@JsonProperty(value = "sender", access = JsonProperty.Access.READ_ONLY)
	public String sender(){
		return sender.getValue();
	}
}
