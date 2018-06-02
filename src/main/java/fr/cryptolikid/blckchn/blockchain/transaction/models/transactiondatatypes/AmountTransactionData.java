package fr.cryptolikid.blckchn.blockchain.transaction.models.transactiondatatypes;

import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadTransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionInvalidDataException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * AmountTransactionData
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
public class AmountTransactionData extends TransactionData implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** amount */
	private BigDecimal amount;

	/**
	 * @throws BadTransactionException if model is not valid
	 */
	@Override
	public void validate() throws BadTransactionException {
		if(BigDecimal.valueOf(0).compareTo(amount) > 0)
			throw new TransactionInvalidDataException("Amount can't be negative !");
	}
}