package lbp.toolsfordev.blckchn.blockchain.transaction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TransactionInfoMissingException
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TransactionInfoMissingException extends BadTransactionException {
	/**
	 * Constructs a new exception with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 */
	public TransactionInfoMissingException() {
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
	public TransactionInfoMissingException(String message) {
		super(message);
	}
}
