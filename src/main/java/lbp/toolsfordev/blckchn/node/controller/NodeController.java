package lbp.toolsfordev.blckchn.node.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lbp.toolsfordev.blckchn.blockchain.model.Block;
import lbp.toolsfordev.blckchn.blockchain.model.Transaction;
import lbp.toolsfordev.blckchn.blockchain.service.BlockchainService;
import lbp.toolsfordev.blckchn.node.model.BlockchainInfos;
import lbp.toolsfordev.blckchn.node.model.MiningResponse;
import lbp.toolsfordev.blckchn.node.model.ResponseMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * NodeController
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 */
@RestController(value = "/node")
public class NodeController {
	private Log LOG = LogFactory.getLog(NodeController.class);

	private String nodeIdentifier = UUID.randomUUID().toString();

	/** blockchainService */
	private final BlockchainService blockchainService;

	@Autowired
	public NodeController(BlockchainService blockchainService) {
		this.blockchainService = blockchainService;
	}

	@RequestMapping(value = "/transactions/new", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseMessage> newTx(@RequestBody final Transaction requestTransaction) {
		Integer index = blockchainService.newTransaction(requestTransaction);

		return ResponseEntity.ok(new ResponseMessage(String.format("Transaction added ! Index : %s", index)));
	}

	@RequestMapping(value = "/mine", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MiningResponse> mine() throws JsonProcessingException {
		Block lastBlock = blockchainService.getLastBlock();
		long lastProof = lastBlock.getProof();
		long proof = blockchainService.proofOfWork(lastProof);

		blockchainService.newTransaction("0x", nodeIdentifier, BigDecimal.valueOf(1));

		String previousHash = BlockchainService.hashBlock(lastBlock);
		Block block = blockchainService.newBlock(proof, previousHash);

		MiningResponse response = new MiningResponse();
		response.setIndex(block.getIndex());
		response.setMessage("New block mined !");
		response.setPreviousHash(block.getPreviousHash());
		response.setProof(block.getProof());
		response.setTransactions(block.getTransactions());

		return ResponseEntity.ok(response);

	}

	@RequestMapping(value = "/fullChain", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BlockchainInfos> getFullChain() {
		return ResponseEntity.ok(blockchainService.getBlockchainInfos());
	}
}
