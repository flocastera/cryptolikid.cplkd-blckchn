package fr.cryptolikid.blckchn.node.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.cryptolikid.blckchn.blockchain.block.constants.BlockConstants;
import fr.cryptolikid.blckchn.blockchain.block.exceptions.BlockException;
import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.block.service.IBlockGeneratorService;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.chain.service.IBlockchainExplorerService;
import fr.cryptolikid.blckchn.blockchain.chain.service.IBlockchainMutatorService;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.blockchain.transaction.models.FinalTransaction;
import fr.cryptolikid.blckchn.blockchain.transaction.models.transactiondatatypes.AmountTransactionData;
import fr.cryptolikid.blckchn.blockchain.transaction.service.ITransactionService;
import fr.cryptolikid.blckchn.node.models.MinerInformation;
import fr.cryptolikid.blckchn.node.models.MiningResponse;
import fr.cryptolikid.blckchn.node.proofofwork.models.Proof;
import fr.cryptolikid.blckchn.node.proofofwork.service.IProofOfWorkService;
import fr.cryptolikid.blckchn.node.service.INodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	/** blockchainExplorerService */
	private final IBlockchainExplorerService blockchainExplorerService;

	/** blockchainMutatorService */
	private final IBlockchainMutatorService blockchainMutatorService;

	/** proofOfWorkService */
	private final IProofOfWorkService proofOfWorkService;

	/** blockGeneratorService */
	private final IBlockGeneratorService blockGeneratorService;

	/** transactionService */
	private final ITransactionService transactionService;

	/** LOG */
	private Logger LOG = LogManager.getLogger(NodeService.class);


	@Autowired
	public NodeService(IBlockchainExplorerService blockchainExplorerService, IBlockchainMutatorService blockchainMutatorService, IProofOfWorkService proofOfWorkService, IBlockGeneratorService blockGeneratorService, ITransactionService transactionService) {
		this.blockchainExplorerService = blockchainExplorerService;
		this.blockchainMutatorService = blockchainMutatorService;
		this.proofOfWorkService = proofOfWorkService;
		this.blockGeneratorService = blockGeneratorService;
		this.transactionService = transactionService;
	}

	/**
	 * @return
	 */
	@Override
	public Proof findProof() {
		Block lastBlock = this.blockchainExplorerService.getLastAddedBlock();
		Proof lastProof = lastBlock.getProof();
		return this.proofOfWorkService.searchForProof(lastProof);
	}

	/**
	 * @param ifs @NotNull
	 * @return
	 */
	@Override
	public void payMiner(MinerInformation ifs) throws TransactionException {

		FinalTransaction<AmountTransactionData> grant = new FinalTransaction<>();

		ifs.validate();

		grant.setSender(BlockConstants.GENESIS_ADDRESS);
		grant.setReceiver(ifs.getPaymentAddress());
		grant.setData(new AmountTransactionData(new BigDecimal(1)));
		grant.setTransactionId(this.transactionService.getNewTransactionId());

		this.blockchainMutatorService.newAmountTransaction(grant);

	}

	/**
	 * @param proof @NotNull
	 * @return
	 * @throws BlockchainException
	 * @throws BlockException
	 */
	@Override
	public Block claimNewBlock(Proof proof) throws BlockchainException, BlockException {

		Block lastBlock = this.blockchainExplorerService.getLastAddedBlock();

		String previousHash = null;
		try {
			previousHash = this.blockGeneratorService.hashBlock(lastBlock);
		} catch (JsonProcessingException e) {
			LOG.error("Cannot add a block !");
			throw new BlockException("Cannot create block !");
		}
		Block block = this.blockGeneratorService.newBlock(
				proof, previousHash, lastBlock.getIndex(), this.blockchainExplorerService.getPendingTransactions());

		this.blockchainMutatorService.addBlock(block);

		return block;
	}

	/**
	 * @param block @NotNull
	 * @return
	 */
	@Override
	public MiningResponse createMinerResponse(Block block) {

		MiningResponse response = new MiningResponse();
		response.setIndex(block.getIndex());
		response.setMessage("New block mined !");
		response.setPreviousHash(block.getPreviousHash());
		response.setProof(block.getProof());
		response.setTransactions(block.getTransactions());

		return response;
	}

}
