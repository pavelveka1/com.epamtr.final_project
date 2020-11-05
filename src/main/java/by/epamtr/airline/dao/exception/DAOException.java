package by.epamtr.airline.dao.exception;

/**
 * Class for processing exception which appear while performing any operations
 * with database
 * 
 * @author Pavel Veka
 *
 */
public class DAOException extends Exception {

	/**
	 * Serial version uid for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor without parameters
	 */
	public DAOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
