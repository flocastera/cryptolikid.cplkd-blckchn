package fr.cryptolikid.blckchn.blockchain.transaction.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadTransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionInfoMissingException;
import fr.cryptolikid.blckchn.common.interfaces.IValidableModel;
import lombok.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * FinalTransaction
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 */
@Getter
@Setter
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class FinalTransaction<T extends TransactionData>
		implements Serializable, IValidableModel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** transactionId */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonUnwrapped
	private TransactionId transactionId;

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
	 */
	@Override
	public void validate() throws BadTransactionException {
		if (transactionId == null || StringUtils.isEmpty(transactionId.getTxId()))
			throw new TransactionInfoMissingException("No TxID !");
		data.validate();
	}

	@JsonProperty(value = "transactionId", access = JsonProperty.Access.READ_ONLY)
	public String getTxId() {
		return transactionId.getTxId();
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
