package fr.cryptolikid.blckchn.common.hash;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * Sha256HashesUtils
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@Component
public class Sha256HashesUtils implements IHashesUtils {

	/** HASH_NAME */
	private static final HashAlgorithms HASH_NAME = HashAlgorithms.SHA256;

	/**
	 * @param str @NotNull
	 * @return a hashed string
	 */
	@Override
	public String hash(String str) {
		return this.process(str, HASH_NAME);
	}

	/**
	 * @param bytes @NotNull
	 * @return a hashed string
	 */
	@Override
	public String hash(byte[] bytes) {
		return this.process(bytes, HASH_NAME);
	}
}
