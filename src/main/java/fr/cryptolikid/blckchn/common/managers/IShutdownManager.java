package fr.cryptolikid.blckchn.common.managers;

/**
 * IShutdownManager
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
public interface IShutdownManager {

	/**
	 * @param returnCode
	 */
	void initiateShutdown(int returnCode);
}
