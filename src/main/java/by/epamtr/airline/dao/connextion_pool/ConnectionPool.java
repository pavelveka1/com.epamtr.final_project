package by.epamtr.airline.dao.connextion_pool;

import java.sql.Connection;
import java.util.List;
import by.epamtr.airline.dao.exception.DAOException;

public interface ConnectionPool {
	Connection getConnection() throws DAOException;

	boolean releaseConnection(Connection connection);

	List<Connection> getConnectionPool();

	int getSize();

	String getUrl();

	String getUser();

	String getPassword();

	void shutdown() throws DAOException;
}
