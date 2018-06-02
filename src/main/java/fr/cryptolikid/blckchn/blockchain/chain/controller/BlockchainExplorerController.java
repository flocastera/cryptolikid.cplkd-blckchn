package fr.cryptolikid.blckchn.blockchain.chain.controller;

import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.chain.service.IBlockchainExplorerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

/**
 * BlockchainExplorerController
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 * @copyright La Poste 2018
 */
@RestController(value = "/blockchain")
public class BlockchainExplorerController {

	private final IBlockchainExplorerService blockchainExplorerService;

	@Autowired
	public BlockchainExplorerController(IBlockchainExplorerService blockchainExplorerService) {
		this.blockchainExplorerService = blockchainExplorerService;
	}


	@RequestMapping(value = "/fullChain", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Vector<Block>> getFullChain() {
		return ResponseEntity.ok(this.blockchainExplorerService.getFullBlockChain());
	}
}
