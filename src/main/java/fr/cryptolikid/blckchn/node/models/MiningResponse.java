package fr.cryptolikid.blckchn.node.models;

import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;
import fr.cryptolikid.blckchn.node.proofofwork.models.Proof;
import lombok.*;

import java.io.Serializable;
import java.util.Vector;

/**
 * MiningResponse
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 * @copyright La Poste 2018
 */
@Getter
@Setter
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class MiningResponse implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private String message;

	private int index;

	private Vector<FinalTransaction<? extends TransactionData>> transactions;

	private Proof proof;

	private String previousHash;

}
