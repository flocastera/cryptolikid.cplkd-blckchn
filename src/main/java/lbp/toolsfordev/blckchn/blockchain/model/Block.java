package lbp.toolsfordev.blckchn.blockchain.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

/**
 * Block
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
public class Block implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Integer index;

	private Date timestamp;

	private Vector<Transaction> transactions;

	private long proof;

	private String previousHash;

}
