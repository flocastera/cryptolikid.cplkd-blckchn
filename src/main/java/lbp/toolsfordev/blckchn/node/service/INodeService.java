package lbp.toolsfordev.blckchn.node.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lbp.toolsfordev.blckchn.blockchain.chain.exceptions.BlockchainException;
import lbp.toolsfordev.blckchn.blockchain.transaction.exceptions.TransactionException;
import lbp.toolsfordev.blckchn.node.models.MinerInformation;
import lbp.toolsfordev.blckchn.node.models.MiningResponse;
import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;
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
	 * @return
	 */
	void payMiner(MinerInformation ifs) throws TransactionException, BlockchainException;

	/**
	 * @return
	 */
	MiningResponse prepareNewBlock(Proof proof) throws JsonProcessingException;

}
