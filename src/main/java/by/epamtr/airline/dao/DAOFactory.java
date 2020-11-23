package by.epamtr.airline.dao;

import by.epamtr.airline.dao.impl.SQLAircraftDAO;
import by.epamtr.airline.dao.impl.SQLFlightDAO;
import by.epamtr.airline.dao.impl.SQLUserDAO;

/**
 * Class factory for DAO objects
 * 
 * @author Pavel Veka
 *
 */
public class DAOFactory {
	/**
	 * Instance of factory
	 */
	private static final DAOFactory instance = new DAOFactory();
	/**
	 * Instance of SQLUserDAO objects for operations with users
	 */
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	/**
	 * Instance os SQLAircraftDAO object for operations with aircrafts
	 */
	private final AircraftDAO sqlAircraftImpl = new SQLAircraftDAO();
	/**
	 * Instance of SQLFlightDAO object for operations with flights
	 */
	private final FlightDAO sqlFlightImpl = new SQLFlightDAO();

	/**
	 * Private constructor
	 */
	private DAOFactory() {
	}

	/**
	 * Method return instance of factory
	 * 
	 * @return instance of factory
	 */
	public static DAOFactory getInstance() {
		return instance;
	}

	/**
	 * 
	 * @return SQLUserDAO object
	 */
	public UserDAO getSqlUserImpl() {
		return sqlUserImpl;
	}

	/**
	 * 
	 * @return SQLAircraftDAO object
	 */
	public AircraftDAO getSqlAircraftImpl() {
		return sqlAircraftImpl;
	}

	/**
	 * 
	 * @return SQLFlightDAO object
	 */
	public FlightDAO getSqlFlightImpl() {
		return sqlFlightImpl;
	}

}
