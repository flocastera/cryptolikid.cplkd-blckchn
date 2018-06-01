package lbp.toolsfordev.blckchn.model;

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

	private Vector<Transaction> transactions;

	private long proof;

	private String previousHash;

}
