package fr.cryptolikid.blckchn.blockchain.transaction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * BadTransactionException
 *
 * @author flocastera
 * @version 1.0
 * @date 01/06/2018
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadTransactionException extends TransactionException{
	/**
	 * Constructs a new exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public BadTransactionException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public BadTransactionException(String message) {
		super(message);
	}
}
