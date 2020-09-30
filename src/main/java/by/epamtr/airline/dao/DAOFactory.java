package by.epamtr.airline.dao;

import by.epamtr.airline.dao.impl.SQLAircraftDAO;
import by.epamtr.airline.dao.impl.SQLFlightDAO;
import by.epamtr.airline.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final AircraftDAO sqlAircraftImpl = new SQLAircraftDAO();
	private final FlightDAO sqlFlightImpl = new SQLFlightDAO();
	

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserDAO getSqlUserImpl() {
		return sqlUserImpl;
	}

	public AircraftDAO getSqlAircraftImpl() {
		return sqlAircraftImpl;
	}

	public FlightDAO getSqlFlightImpl() {
		return sqlFlightImpl;
	}


}
