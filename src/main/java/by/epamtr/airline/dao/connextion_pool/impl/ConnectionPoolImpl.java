package by.epamtr.airline.dao.connextion_pool.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import by.epamtr.airline.dao.connextion_pool.ConnectionPool;
import by.epamtr.airline.dao.exception.DAOException;

public class ConnectionPoolImpl implements ConnectionPool {
	private  final String url;
	private  final String user;
	private  final String password;
	private final List<Connection> connectionPool;
	private final List<Connection> usedConnections = new ArrayList<>();
	private static final int INITIAL_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 10;
	private static final int MAX_TIMEOUT = 5;
	private static Properties dbProperties;

	private static final ConnectionPool instance = new ConnectionPoolImpl();

	private ConnectionPoolImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		dbProperties = new Properties();
		try(InputStream in = ConnectionPool.class.getClassLoader().getResourceAsStream("db_properties.properties")) {
			dbProperties.load(in);
		} catch (IOException e) {
			e.printStackTrace();;
		}
	
		url=dbProperties.getProperty("db.url");
		user=dbProperties.getProperty("db.user");
		password=dbProperties.getProperty("db.password");
		
	
		connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			try {
				connectionPool.add(createConnection(url, user, password));
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Connection getConnection() throws DAOException {
		if (connectionPool.isEmpty()) {
			if (usedConnections.size() < MAX_POOL_SIZE) {
				connectionPool.add(createConnection(url, user, password));
			} else {
				throw new DAOException("Maximum pool size reached, no available connections!");
			}
		}

		Connection connection = connectionPool.remove(connectionPool.size() - 1);

		try {
			if (!connection.isValid(MAX_TIMEOUT)) {
				connection = createConnection(url, user, password);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
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
			throws DAOException {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DAOException("Connection is not created", e);
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
	public void shutdown() throws DAOException {
		usedConnections.forEach(this::releaseConnection);
		for (Connection c : connectionPool) {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DAOException("Connection is not closed", e);
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
