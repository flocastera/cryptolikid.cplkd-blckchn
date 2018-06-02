package fr.cryptolikid.blckchn.blockchain.transaction.models;

import fr.cryptolikid.blckchn.blockchain.block.constants.BlockConstants;
import fr.cryptolikid.blckchn.blockchain.transaction.exceptions.BadAddressException;
import fr.cryptolikid.blckchn.common.interfaces.IValidableModel;
import lombok.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * Address
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
public class Address implements Serializable, IValidableModel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** value */
	private String value;

	/**
	 * @throws BadAddressException if model is not valid
	 */
	@Override
	public void validate() throws BadAddressException {
		if(StringUtils.isEmpty(value))
			throw new BadAddressException("Address is empty !");
		if( !value.matches("^[a-zA-Z0-9]{10,30}$") && !BlockConstants.GENESIS_ADDRESS.getValue().equals(value))
			throw new BadAddressException(("Address is not standard !"));
	}
}