package fr.cryptolikid.blckchn.node.models;

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
	private String paymentAddress;

	/**
	 * @throws MinerMissingInformationException if model is not valid
	 */
	@Override
	public void validate() throws MinerMissingInformationException {
		if (StringUtils.isEmpty(minerIdentifier) || StringUtils.isEmpty(paymentAddress))
			throw new MinerMissingInformationException();
	}
}