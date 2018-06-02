package fr.cryptolikid.blckchn.blockchain.transaction.exceptions;

/**
 * BadAddressException
 *
 * @author flocastera
 * @version 1.0
 * @date 02/06/2018
 */
public class BadAddressException extends AddressException {

	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public BadAddressException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param message the detail message. The detail message is saved for
	 *                later retrieval by the {@link #getMessage()} method.
	 */
	public BadAddressException(String message) {
		super(message);
	}
}
