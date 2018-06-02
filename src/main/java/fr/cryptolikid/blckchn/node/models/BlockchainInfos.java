package fr.cryptolikid.blckchn.node.models;

import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import lombok.*;

import java.io.Serializable;
import java.util.Vector;

/**
 * BlockchainInfos
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
public class BlockchainInfos implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** blocks */
	private Vector<Block> blocks;

	/** depth */
	private int depth;

}
