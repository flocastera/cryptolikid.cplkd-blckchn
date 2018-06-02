package lbp.toolsfordev.blckchn.blockchain.block.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lbp.toolsfordev.blckchn.blockchain.block.models.Block;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;

import java.util.Vector;

public interface IBlockGeneratorService {

	/**
	 * @param proof               @NotNull
	 * @param previousHash        @NotNull
	 * @param previousIndex       @NotNull
	 * @param pendingTransactions @NotNull
	 * @return
	 */
	Block newBlock(Proof proof, String previousHash, int previousIndex, Vector<FinalTransaction> pendingTransactions);

	/**
	 * Genesis block is the first block added to blockchain.
	 *
	 * @return the genesis block
	 */
	Block createGenesisBlock();

	/**
	 * @param block @NotNull
	 * @return a hashed block, hashing algorithm need to be implemented
	 */
	String hashBlock(Block block) throws JsonProcessingException;

}
