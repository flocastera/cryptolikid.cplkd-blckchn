package fr.cryptolikid.blckchn.node.controller;

import fr.cryptolikid.blckchn.blockchain.block.exceptions.BlockException;
import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.chain.service.IBlockchainExplorerService;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.node.models.MinerInformation;
import fr.cryptolikid.blckchn.node.models.MiningResponse;
import fr.cryptolikid.blckchn.node.proofofwork.models.Proof;
import fr.cryptolikid.blckchn.node.service.INodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	/** blockchainExplorerService */
	private final IBlockchainExplorerService blockchainExplorerService;

	/** nodeService */
	private final INodeService nodeService;


	/** LOG */
	private Logger LOG = LogManager.getLogger(NodeController.class);

	/** nodeIdentifier */
	private String nodeIdentifier = UUID.randomUUID().toString();


	@Autowired
	public NodeController(IBlockchainExplorerService blockchainExplorerService, INodeService nodeService) {
		this.blockchainExplorerService = blockchainExplorerService;
		this.nodeService = nodeService;
	}


	@RequestMapping(value = "/mine", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MiningResponse> mine(
			@RequestBody MinerInformation minerInformation) throws TransactionException, BlockException, BlockchainException {

		Proof proof = this.nodeService.findProof();

		this.nodeService.payMiner(minerInformation);

		Block block = this.nodeService.claimNewBlock(proof);

		return ResponseEntity.ok(this.nodeService.createMinerResponse(block));

	}
}
