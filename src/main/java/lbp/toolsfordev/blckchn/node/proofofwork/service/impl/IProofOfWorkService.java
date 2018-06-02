package lbp.toolsfordev.blckchn.node.proofofwork.service.impl;

import lbp.toolsfordev.blckchn.node.proofofwork.models.Proof;

/**
 * IProofOfWorkService
 *
 * @author flocastera
 * @version 1.0
 * @date 01/06/2018
 */
public interface IProofOfWorkService {

	/**
	 * Execute work to find a proof that matches algorithm
	 *
	 * @param lastProof @NotNull
	 * @return a proof supposed to be valid
	 */
	Proof searchForProof(Proof lastProof);

	/**
	 * @param proof @NotNull
	 * @param lastProof @NotNull
	 * @return true if true matches algorithm
	 */
	boolean isProofValid(Proof proof, Proof lastProof);
}
