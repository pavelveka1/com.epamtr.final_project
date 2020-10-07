package by.epamtr.airline.dao.connection_pool;

import java.sql.Connection;
import java.util.List;

import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;

public interface ConnectionPool {
	Connection getConnection() throws ConnectionPoolException ;

	boolean releaseConnection(Connection connection) ;

	List<Connection> getConnectionPool();

	int getSize();

	String getUrl();

	String getUser();

	String getPassword();

	void shutdown() throws ConnectionPoolException;
}
