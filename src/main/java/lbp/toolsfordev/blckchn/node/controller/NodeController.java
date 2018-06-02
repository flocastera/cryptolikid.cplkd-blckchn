package lbp.toolsfordev.blckchn.node.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lbp.toolsfordev.blckchn.blockchain.block.models.Block;
import lbp.toolsfordev.blckchn.blockchain.chain.service.IBlockchainExplorerService;
import lbp.toolsfordev.blckchn.node.models.MiningResponse;
import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	private Log LOG = LogFactory.getLog(NodeController.class);

	private String nodeIdentifier = UUID.randomUUID().toString();

	private final IBlockchainExplorerService blockchainExplorerService;

	@Autowired
	public NodeController(IBlockchainExplorerService blockchainExplorerService) {
		this.blockchainExplorerService = blockchainExplorerService;
	}


	@RequestMapping(value = "/mine", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MiningResponse> mine() throws JsonProcessingException {


		return ResponseEntity.ok(response);
		return null;

	}
}
