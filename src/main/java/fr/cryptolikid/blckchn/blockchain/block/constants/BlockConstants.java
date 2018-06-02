package fr.cryptolikid.blckchn.blockchain.block.constants;

import fr.cryptolikid.blckchn.node.proofofwork.models.Proof;

/**
 * BlockConstants
 *
 * @author flocastera
 * @version 1.0
 * @date 01/06/2018
 */
public interface BlockConstants {

	/** GENESIS_BLOCK_INDEX */
	int GENESIS_BLOCK_INDEX = 0;

	/** GENESIS_BLOCK_PROOF */
	Proof GENESIS_BLOCK_PROOF = new Proof(13203);

	/** GENESIS_BLOCK_HASH */
	String GENESIS_BLOCK_HASH = "NoWImbOrN";

}
