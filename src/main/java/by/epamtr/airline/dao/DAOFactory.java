package by.epamtr.airline.dao;

import by.epamtr.airline.dao.impl.SQLAdministratorDAO;
import by.epamtr.airline.dao.impl.SQLCrewDAO;
import by.epamtr.airline.dao.impl.SQLDispatcherDAO;
import by.epamtr.airline.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final AdministratorDAO sqlAdministratorImpl = new SQLAdministratorDAO();
	private final DispatcherDAO sqlDispatcherImpl = new SQLDispatcherDAO();
	private final CrewDAO sqlCrewImpl = new SQLCrewDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserDAO getSqlUserImpl() {
		return sqlUserImpl;
	}

	public AdministratorDAO getSqlAdministratorImpl() {
		return sqlAdministratorImpl;
	}

	public DispatcherDAO getSqlDispatcherImpl() {
		return sqlDispatcherImpl;
	}

	public CrewDAO getSqlCrewImpl() {
		return sqlCrewImpl;
	}

}
