package lbp.toolsfordev.blckchn.node.proofofwork.service;

import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;
import lbp.toolsfordev.blckchn.node.proofofwork.service.impl.IProofOfWorkService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ProofOfWorkService
 *
 * @author xekg473
 * @version 1.0
 * @date 01/06/2018
 * @copyright La Poste 2018
 */
@Service
public class ProofOfWorkService implements IProofOfWorkService {

	/** LOG */
	private Log LOG = LogFactory.getLog(ProofOfWorkService.class);

	/**
	 * Execute work to find a proof that matches algorithm
	 *
	 * @param lastProof @NotNull
	 * @return a proof supposed to be valid
	 */
	@Override
	public Proof searchForProof(Proof lastProof) {

		Proof proof = new Proof();

		long startTime = new Date().getTime();

		while (!isProofValid(proof, lastProof)) {
			proof.setValue(proof.getValue()+1);
		}

		long endTime = new Date().getTime();

		LOG.info(String.format("Proof of work has been executed successfully ! Iterations : %s, time : %s milliseconds",
				proof, (endTime - startTime)));

		return proof;
	}

	/**
	 * @param proof     @NotNull
	 * @param lastProof @NotNull
	 * @return true if true matches algorithm
	 */
	@Override
	public boolean isProofValid(Proof proof, Proof lastProof) {
		String formatted = String.format("%s%s", lastProof.getValue(), proof.getValue());
		String formattedHash = DigestUtils.sha256Hex(formatted);

		return formattedHash.startsWith("0000");
	}
}
