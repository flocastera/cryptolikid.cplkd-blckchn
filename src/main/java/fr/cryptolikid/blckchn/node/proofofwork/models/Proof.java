package fr.cryptolikid.blckchn.node.proofofwork.models;

import lombok.*;

import java.io.Serializable;

/**
 * Proof
 *
 * @author flocastera
 * @version 1.0
 * @date 01/06/2018
 */
@Getter
@Setter
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Proof implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private long value;
}