package fr.cryptolikid.blckchn.blockchain.transaction.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * TransactionId
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
public class TransactionId implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** txId */
	@JsonProperty("transactionId")
	private String txId;

}