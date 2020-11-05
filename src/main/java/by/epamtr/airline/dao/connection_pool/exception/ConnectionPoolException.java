package by.epamtr.airline.dao.connection_pool.exception;

/**
 * Class for processing exception which appear while connection pool is working
 * 
 * @author Pavel veka
 *
 */
public class ConnectionPoolException extends Exception {

	/**
	 * Serial version uid for serialization
	 */
	private static final long serialVersionUID = 8211418386913907350L;

	/**
	 * Constructor without parameters
	 */
	public ConnectionPoolException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public ConnectionPoolException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public ConnectionPoolException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
