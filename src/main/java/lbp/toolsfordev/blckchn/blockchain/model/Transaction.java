package lbp.toolsfordev.blckchn.blockchain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Transaction
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
public class Transaction implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private String sender;

	private String receiver;

	private BigDecimal amount;

}
