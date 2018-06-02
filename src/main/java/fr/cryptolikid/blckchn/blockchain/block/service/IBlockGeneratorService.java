package fr.cryptolikid.blckchn.blockchain.block.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.TransactionData;
import fr.cryptolikid.blckchn.node.proofofwork.models.Proof;

import java.util.Vector;

public interface IBlockGeneratorService {

	/**
	 * @param proof               @NotNull
	 * @param hash        @NotNull
	 * @param previousIndex       @NotNull
	 * @param pendingTransactions @NotNull
	 * @return
	 */
	Block newBlock(Proof proof, String hash, int previousIndex, Vector<FinalTransaction<? extends TransactionData>> pendingTransactions);

	/**
	 * @param proof               @NotNull
	 * @param hash        @NotNull
	 * @param previousIndex       @NotNull
	 * @return
	 */
	Block newBlock(Proof proof, String hash, int previousIndex);

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
