package by.epamtr.airline.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;

/**
 * Application Lifecycle Listener implementation class
 * ServletContextListenerImpl
 *
 */
public class ServletContextListenerImpl implements ServletContextListener {
	Logger rootLogger = LogManager.getLogger(ServletContextListenerImpl.class);
	private static final String CONTEXT_CREATED = "Context of application is created";
	private static final String CONTEXT_DESTROYED = "Context of application is destroyed";

	/**
	 * Default constructor.
	 */
	public ServletContextListenerImpl() {

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		rootLogger.info(CONTEXT_DESTROYED);
		try {
			ConnectionPoolImpl.getInstance().shutdown();
		} catch (ConnectionPoolException e) {
			rootLogger.error(e);
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		rootLogger.info(CONTEXT_CREATED);
	}

}
