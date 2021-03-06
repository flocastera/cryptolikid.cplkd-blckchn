package fr.cryptolikid.blckchn.common.interfaces;

/**
 * IValidableModel
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
public interface IValidableModel {

	/**
	 * @throws Exception if model is not valid
	 */
	void validate() throws Exception;

}
