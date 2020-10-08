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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epamtr.airline.dao.connection_pool.ConnectionPool;
import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;


public class ConnectionPoolImpl implements ConnectionPool {
	private final String url;
	private final String user;
	private final String password;
	private final List<Connection> connectionPool;
	private final List<Connection> usedConnections = new ArrayList<>();
	private static final int INITIAL_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 10;
	private static final int MAX_TIMEOUT = 5;
	private static Properties dbProperties;
	private static final String NAME_JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DB_USER="db.user";
	private static final String DB_URL="db.url";
	private static final String DB_PASSWORD="db.password";

	private static final ConnectionPool instance = new ConnectionPoolImpl();
	private static final Logger rootLogger = LogManager.getLogger(ConnectionPoolImpl.class);

	private ConnectionPoolImpl() {
		try {
			Class.forName(NAME_JDBC_DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
			rootLogger.error(e1);
		}
		dbProperties = new Properties();
		try(InputStream in = ConnectionPool.class.getClassLoader().getResourceAsStream("db_properties.properties")) {
			dbProperties.load(in);
		} catch (IOException e) {
			rootLogger.error(e);
		}
	
		url=dbProperties.getProperty(DB_URL);
		user=dbProperties.getProperty(DB_USER);
		password=dbProperties.getProperty(DB_PASSWORD);
		
	
		connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			try {
				connectionPool.add(createConnection(url, user, password));
			} catch (ConnectionPoolException e) {
				rootLogger.error(e);
			}
		}
	}

	@Override
	public Connection getConnection() throws ConnectionPoolException  {
		if (connectionPool.isEmpty()) {
			if (usedConnections.size() < MAX_POOL_SIZE) {
				connectionPool.add(createConnection(url, user, password));
			} else {
				throw new ConnectionPoolException("Maximum pool size reached, no available connections!");
			}
		}

		Connection connection = connectionPool.remove(connectionPool.size() - 1);

		try {
			if (!connection.isValid(MAX_TIMEOUT)) {
				connection = createConnection(url, user, password);
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException(e);
		} 

		usedConnections.add(connection);
		return connection;
	}

	@Override
	public boolean releaseConnection(Connection connection) {
		connectionPool.add(connection);
		return usedConnections.remove(connection);
	}

	private static Connection createConnection(String url, String user, String password)
			throws ConnectionPoolException {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new ConnectionPoolException("Connection is not created", e);
		}
	}

	@Override
	public int getSize() {
		return connectionPool.size() + usedConnections.size();
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void shutdown() throws ConnectionPoolException {
		for(Connection c: usedConnections) {
			this.releaseConnection(c);
		}
	//usedConnections.forEach(this::releaseConnection);
		for (Connection c : connectionPool) {
			try {
				c.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException("Connection is not closed", e);
			}
		}
		connectionPool.clear();
	}

	@Override
	public List<Connection> getConnectionPool() {
		return connectionPool;
	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}
}
