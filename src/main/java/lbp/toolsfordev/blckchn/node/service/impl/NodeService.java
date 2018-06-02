package lbp.toolsfordev.blckchn.node.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lbp.toolsfordev.blckchn.blockchain.block.models.Block;
import lbp.toolsfordev.blckchn.blockchain.block.service.IBlockGeneratorService;
import lbp.toolsfordev.blckchn.blockchain.chain.service.IBlockchainExplorerService;
import lbp.toolsfordev.blckchn.blockchain.chain.service.IBlockchainMutatorService;
import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.TransactionException;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.FinalTransaction;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.TransactionId;
import lbp.toolsfordev.blckchn.blockchain.transaction.models.transactiondatatypes.AmountTransactionData;
import lbp.toolsfordev.blckchn.node.models.MinerInformation;
import lbp.toolsfordev.blckchn.node.models.MiningResponse;
import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;
import lbp.toolsfordev.blckchn.node.proofofwork.service.impl.IProofOfWorkService;
import lbp.toolsfordev.blckchn.node.service.INodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * NodeService
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 * @copyright La Poste 2018
 */
@Service
public class NodeService implements INodeService {

	/** LOG */
	private Log LOG = LogFactory.getLog(NodeService.class);

	/** blockchainExplorerService */
	private final IBlockchainExplorerService blockchainExplorerService;

	/** blockchainMutatorService */
	private final IBlockchainMutatorService blockchainMutatorService;

	/** proofOfWorkService */
	private final IProofOfWorkService proofOfWorkService;

	/** blockGeneratorService */
	private final IBlockGeneratorService blockGeneratorService;

	@Autowired
	public NodeService(IBlockchainExplorerService blockchainExplorerService, IBlockchainMutatorService blockchainMutatorService, IProofOfWorkService proofOfWorkService, IBlockGeneratorService blockGeneratorService) {
		this.blockchainExplorerService = blockchainExplorerService;
		this.blockchainMutatorService = blockchainMutatorService;
		this.proofOfWorkService = proofOfWorkService;
		this.blockGeneratorService = blockGeneratorService;
	}

	/**
	 * @return
	 */
	@Override
	public Proof findProof() {
		Block lastBlock = this.blockchainExplorerService.getLastAddedBlock();
		Proof lastProof = lastBlock.getProof();
		Proof proof = this.proofOfWorkService.searchForProof(lastProof);
		return proof;
	}

	/**
	 * @param ifs @NotNull
	 * @return
	 */
	@Override
	public void payMiner(MinerInformation ifs) throws TransactionException {

		FinalTransaction<AmountTransactionData> grant = new FinalTransaction<>();

		ifs.validate();

		grant.setSender("0x");
		grant.setReceiver(ifs.getPaymentAddress());
		grant.setData(new AmountTransactionData(new BigDecimal(1)));
		grant.setTransactionId(new TransactionId("dskgd"));

		this.blockchainMutatorService.newAmountTransaction(grant);

	}

	@Override
	public MiningResponse prepareNewBlock(Proof proof) throws JsonProcessingException {

		Block lastBlock = this.blockchainExplorerService.getLastAddedBlock();

		String previousHash = this.blockGeneratorService.hashBlock(lastBlock);
		Block block = this.blockGeneratorService.newBlock(
				proof, previousHash, lastBlock.getIndex(), null);

		this.blockchainMutatorService.


		MiningResponse response = new MiningResponse();
		response.setIndex(block.getIndex());
		response.setMessage("New block mined !");
		response.setPreviousHash(block.getPreviousHash());
		response.setProof(block.getProof());
		response.setTransactions(block.getTransactions());

		return response;

	}
}
