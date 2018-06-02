package fr.cryptolikid.blckchn.blockchain.chain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * BlockchainAccessException
 *
 * @author flocastera
 * @version 1.0
 * @date 01/06/2018
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BlockchainException extends RuntimeException {
	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public BlockchainException() {

	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public BlockchainException(String message) {
		super(message);
	}
}
