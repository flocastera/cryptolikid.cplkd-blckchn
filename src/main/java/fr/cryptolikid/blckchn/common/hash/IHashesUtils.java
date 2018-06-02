package fr.cryptolikid.blckchn.common.hash;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * IHashesUtils
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
public interface IHashesUtils {

	/**
	 * @param bytes @NotNull
	 * @param algo @Enum
	 * @return
	 */
	default String process(byte[] bytes, HashAlgorithms algo) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(algo.getValue());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		assert digest != null;
		return Arrays.toString(digest.digest(bytes));
	}

	/**
	 * @param str @NotNull
	 * @param algo @Enum
	 * @return
	 */
	default String process(String str, HashAlgorithms algo) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(algo.getValue());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		assert digest != null;
		return new String(Hex.encodeHex(digest.digest(str.getBytes(StandardCharsets.UTF_8))));
	}

	/**
	 * @param str @NotNull
	 * @return a hashed string
	 */
	default String hash(String str) {
		return String.valueOf(String.format("%s", str).hashCode());
	}

	/**
	 * @param bytes @NotNull
	 * @return a hashed string
	 */
	default String hash(byte[] bytes) {
		return String.valueOf(Arrays.hashCode(bytes));
	}
}
