package lbp.toolsfordev.blckchn.blockchain.block.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lbp.toolsfordev.blckchn.blockchain.block.constants.BlockConstants;
import lbp.toolsfordev.blckchn.blockchain.block.models.Block;
import lbp.toolsfordev.blckchn.blockchain.block.service.IBlockGeneratorService;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Vector;

@Setter
@Service
public class BlockGeneratorService implements IBlockGeneratorService {

	/** LOG */
	private Log LOG = LogFactory.getLog(BlockGeneratorService.class);

	/**
	 * @param proof               @NotNull
	 * @param hash                @NotNull
	 * @param previousIndex       @NotNull
	 * @param pendingTransactions @NotNull
	 * @return
	 */
	@Override
	public Block newBlock(Proof proof, String hash, int previousIndex, Vector<FinalTransaction> pendingTransactions) {
		Block block = new Block();
		block.setIndex(previousIndex + 1);
		block.setTimestamp(new Date());
		block.setTransactions(pendingTransactions);
		block.setProof(proof);
		block.setPreviousHash(hash);
		return block;
	}

	/**
	 * Genesis block is the first block added to blockchain.
	 *
	 * @return the genesis block
	 */
	@Override
	public Block createGenesisBlock() {
		return new Block(
				BlockConstants.GENESIS_BLOCK_INDEX,
				new Date(),
				new Vector<>(),
				BlockConstants.GENESIS_BLOCK_PROOF,
				BlockConstants.GENESIS_BLOCK_HASH);
	}

	/**
	 * @param block @NotNull
	 * @return a hashed block, hashing algorithm need to be implemented
	 */
	@Override
	public String hashBlock(Block block) throws JsonProcessingException {

		String dump;
		try {
			dump = new ObjectMapper().writeValueAsString(block);
		} catch (JsonProcessingException e) {
			LOG.error("Block cannot be hashed !");
			throw e;
		}
		return DigestUtils.sha256Hex(dump);
	}
}
