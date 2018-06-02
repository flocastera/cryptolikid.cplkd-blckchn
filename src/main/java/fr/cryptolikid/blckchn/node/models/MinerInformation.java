package fr.cryptolikid.blckchn.node.models;

import fr.cryptolikid.blckchn.blockchain.transaction.models.Address;
import fr.cryptolikid.blckchn.common.interfaces.IValidableModel;
import fr.cryptolikid.blckchn.node.exceptions.MinerMissingInformationException;
import lombok.*;
import org.springframework.util.StringUtils;

/**
 * MinerInformation
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@Getter
@Setter
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class MinerInformation implements IValidableModel {

	/** minerIdentifier */
	private String minerIdentifier;

	/** paymentAddress */
	private Address paymentAddress;

	/**
	 * @throws MinerMissingInformationException if model is not valid
	 */
	@Override
	public void validate() throws MinerMissingInformationException {
		paymentAddress.validate();
		if (StringUtils.isEmpty(minerIdentifier))
			throw new MinerMissingInformationException();
	}
}
