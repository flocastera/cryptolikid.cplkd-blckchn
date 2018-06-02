package lbp.toolsfordev.blckchn.node.models;

import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;
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

	private Vector<FinalTransaction> transactions;

	private Proof proof;

	private String previousHash;

}
