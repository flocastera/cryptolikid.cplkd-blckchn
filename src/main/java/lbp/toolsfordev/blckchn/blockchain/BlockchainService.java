package lbp.toolsfordev.blckchn.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lbp.toolsfordev.blckchn.model.Block;
import lbp.toolsfordev.blckchn.model.BlockchainInfos;
import lbp.toolsfordev.blckchn.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;

/**
 * BlockchainService
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 */
@Getter
@Setter
@Service
public class BlockchainService {

	private Log LOG = LogFactory.getLog(BlockchainService.class);

	/** chain */
	private Vector<Block> chain;

	/** currentTransactions */
	private Vector<Transaction> currentTransactions;

	public BlockchainService() {
		this.chain = new Vector<Block>();
		this.currentTransactions = new Vector<Transaction>();

		newBlock(100, "1");
	}

	public Block newBlock(long proof, String previousHash) {
		Block block = new Block();
		block.setIndex(this.chain.size() + 1);
		block.setTimestamp(new Date());
		block.setTransactions(this.currentTransactions);
		block.setProof(proof);
		block.setPreviousHash(previousHash);

		this.currentTransactions = new Vector<Transaction>();

		this.chain.add(block);
		return block;
	}

	public Integer newTransaction(String sender, String receiver, BigDecimal amount) {
		return this.newTransaction(new Transaction(sender, receiver, amount));
	}

	public Integer newTransaction(Transaction transaction) {
		this.currentTransactions.add(transaction);

		return this.getLastBlock().getIndex() + 1;
	}

	public static String hashBlock(Block block) throws JsonProcessingException {
		String dump = new ObjectMapper().writeValueAsString(block);

		return DigestUtils.sha256Hex(dump);
	}

	public Block getLastBlock() {
		return this.chain.lastElement();
	}

	public long proofOfWork(long lastProof) {
		long proof = 0;

		long startTime = new Date().getTime();

		while (!validProof(lastProof, proof)) {
			proof++;
		}

		long endTime = new Date().getTime();

		LOG.info(String.format("Proof of work has been executed successfully ! Iterations : %s, time : %s milliseconds",
				proof, (endTime - startTime)));


		return proof;
	}

	private static Boolean validProof(long lastProof, long proof) {
		String formatted = String.format("%s%s", lastProof, proof);
		String formattedHash = DigestUtils.sha256Hex(formatted);

		return formattedHash.startsWith("0000");
	}

	public BlockchainInfos getBlockchainInfos() {
		return new BlockchainInfos(chain, chain.size());
	}
}
