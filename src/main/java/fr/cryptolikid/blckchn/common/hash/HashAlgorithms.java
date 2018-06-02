package fr.cryptolikid.blckchn.common.hash;

import lombok.Getter;

/**
 * HashAlgorithms
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@Getter
public enum HashAlgorithms {
	MD2("MD2"),
	MD5("MD5"),
	SHA1("SHA-1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");

	private String value = "";

	HashAlgorithms(String value) {
		this.value = value;
	}
}
