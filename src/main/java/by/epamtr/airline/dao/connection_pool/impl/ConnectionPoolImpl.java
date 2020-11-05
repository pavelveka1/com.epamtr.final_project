package by.epamtr.airline.dao.connection_pool.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epamtr.airline.controller.command.impl.AddFlightCommand;
import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;

/**
 * Class for connection to database
 * 
 * @author Pavel Veka
 *
 */

public class ConnectionPoolImpl implements ConnectionPool {
	
	/**
	 * url of DB
	 */
	private final String url;
	
	/**
	 * User name for DB
	 */
	private final String user;
	
	/**
	 * password for DB
	 */
	private final String password;
	
	/**
	 * Queue of free Conntections
	 */
	private final BlockingQueue<Connection> connectionPool;
	
	/**
	 * Queue of used Connections
	 */
	private final BlockingQueue<Connection> usedConnections;
	
	/**
	 * Max size of pool of Connections
	 */
	private static final int MAX_POOL_SIZE = 10;
	
	/**
	 * Properties object for downloading settings of DB from db.properties file
	 */
	private static Properties dbProperties;
	
	/**
	 * Nme of driver for DB
	 */
	private static final String NAME_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * Key by which get user's name of DB
	 */
	private static final String DB_USER = "db.user";
	
	/**
	 * Key by which get url of DB
	 */
	private static final String DB_URL = "db.url";
	
	/**
	 * Key by which get password of DB
	 */
	private static final String DB_PASSWORD = "db.password";
	
/**
 * Instance of Connection pool
 */
	private static final ConnectionPool instance = new ConnectionPoolImpl();
	
	/**
	 * Logger for logging events
	 */
	private static final Logger LOGGER = Logger.getLogger(ConnectionPoolImpl.class);

	/**
	 * Constructor of Connection pool
	 */
	private ConnectionPoolImpl() {
		try {
			Class.forName(NAME_JDBC_DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
			LOGGER.error(e1);
		}
		dbProperties = new Properties();
		try (InputStream in = ConnectionPool.class.getClassLoader().getResourceAsStream("db.properties")) {
			dbProperties.load(in);
		} catch (IOException e) {
			LOGGER.error(e);
		}

		url = dbProperties.getProperty(DB_URL);
		user = dbProperties.getProperty(DB_USER);
		password = dbProperties.getProperty(DB_PASSWORD);

		usedConnections = new ArrayBlockingQueue<Connection>(MAX_POOL_SIZE);
		connectionPool = new ArrayBlockingQueue<Connection>(MAX_POOL_SIZE);
		for (int i = 0; i < MAX_POOL_SIZE; i++) {
			try {
				connectionPool.add(createConnection(url, user, password));
			} catch (ConnectionPoolException e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * Get Connection from pool if there is free one
	 * @return free Connection
	 */
	@Override
	public Connection getConnection() throws ConnectionPoolException {
		Connection connection;
		try {
			connection = connectionPool.take();
			usedConnections.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting to the data source.", e);
		}
		return connection;
	}

	/**
	 * Return Connection to connection pool
	 * @return true if Connection was returned to connection pool of free Connections
	 */
	@Override
	public boolean releaseConnection(Connection connection) {
		connectionPool.add(connection);
		return usedConnections.remove(connection);
	}

	/**
	 * Create Connection (Method create Conntections objects while initializing)
	 * @param url
	 * @param user
	 * @param password
	 * @return Connection which was created
	 * @throws ConnectionPoolException if there is any problem while creating Connection
	 */
	private static Connection createConnection(String url, String user, String password)
			throws ConnectionPoolException {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new ConnectionPoolException("Connection is not created", e);
		}
	}

	/**
	 * Get size of pool
	 */
	@Override
	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

	/**
	 * Get url of DB
	 */
	@Override
	public String getUrl() {
		return url;
	}

	/**
	 * Get User's name of DB
	 */
	@Override
	public String getUser() {
		return user;
	}

	/**
	 * Get password of DB
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Close connection pool (all Connections will be close)
	 */
	@Override
	public void shutdown() throws ConnectionPoolException {
		for (Connection c : usedConnections) {
			this.releaseConnection(c);
		}
		for (Connection c : connectionPool) {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				throw new ConnectionPoolException("Connection is not closed", e);
			}
		}
		connectionPool.clear();
	}

	/**
	 * Get BlockingQueue with free Connections
	 */
	@Override
	public BlockingQueue<Connection> getConnectionPool() {
		return connectionPool;
	}

	/**
	 * Get instance of connection pool
	 * @return connection pool
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}
}
