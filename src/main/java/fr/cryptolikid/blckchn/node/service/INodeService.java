package fr.cryptolikid.blckchn.node.service;

import fr.cryptolikid.blckchn.blockchain.block.exceptions.BlockException;
import fr.cryptolikid.blckchn.blockchain.block.models.Block;
import fr.cryptolikid.blckchn.blockchain.chain.exceptions.BlockchainException;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.TransactionException;
import fr.cryptolikid.blckchn.node.models.MinerInformation;
import fr.cryptolikid.blckchn.node.models.MiningResponse;
import fr.cryptolikid.blckchn.node.proofofwork.models.Proof;
import org.springframework.stereotype.Service;

/**
 * INodeService
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@Service
public interface INodeService {

	/**
	 * @return
	 */
	Proof findProof();

	/**
	 * @param ifs @NotNull
	 * @throws TransactionException
	 * @throws BlockchainException
	 */
	void payMiner(MinerInformation ifs) throws TransactionException, BlockchainException;

	/**
	 * @param proof @NotNull
	 * @return
	 * @throws BlockchainException
	 * @throws BlockException
	 */
	Block claimNewBlock(Proof proof) throws BlockchainException, BlockException;

	/**
	 * @param block @NotNull
	 * @return
	 */
	MiningResponse createMinerResponse(Block block);

}
