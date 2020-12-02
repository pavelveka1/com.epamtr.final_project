package by.epamtr.airline.dao.connection_pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;

/**
 * Interface for connection to database
 * 
 * @author Pavel Veka
 * @throws ConnectionPoolException if error while processing
 */
public interface ConnectionPool {
	
	/**
	 * 
	 * @return Connection object
	 * @throws ConnectionPoolException
	 */
	Connection getConnection() throws ConnectionPoolException;

	/**
	 * Release Connection object
	 * 
	 * @param connection
	 * @return true if Connection was returned to pool
	 */
	boolean releaseConnection(Connection connection);

	/**
	 * Get list of Connection from pool
	 * 
	 * @return list of Connection from pool
	 */
	BlockingQueue<Connection> getConnectionPool();

	/**
	 * Get size of pool (number of Connection objects)
	 * 
	 * @return bumber of Connection objects
	 */
	int getSize();

	/**
	 * Get database url
	 * 
	 * @return url of database
	 */
	String getUrl();

	/**
	 * Get user of database
	 * 
	 * @return name of user for database
	 */
	String getUser();

	/**
	 * Get password for database
	 * 
	 * @return password for database
	 */
	String getPassword();

	/**
	 * Shut down connection pool (close all Connections)
	 * 
	 * @throws ConnectionPoolException
	 */
	void shutdown() throws ConnectionPoolException;

	/**
	 * Overloaded Method for close resources. Method close ResultSet and Statement
	 * and return to pool Connection
	 * 
	 * @param statement
	 * @param resultSet
	 * @param connection
	 * @throws ConnectionPoolException
	 */
	void releaseResourses(Statement statement, ResultSet resultSet, Connection connection)
			throws ConnectionPoolException;

	/**
	 * Overloaded method for close resources. Method close ResultSet and Statement
	 * and return to pool Connection
	 * 
	 * @param firstStatement
	 * @param secondStatement
	 * @param firsrResultSet
	 * @param secondResultSet
	 * @param connection
	 * @throws ConnectionPoolException
	 */
	void releaseResourses(Statement firstStatement, Statement secondStatement, ResultSet firsrResultSet,
			ResultSet secondResultSet, Connection connection) throws ConnectionPoolException;
}
