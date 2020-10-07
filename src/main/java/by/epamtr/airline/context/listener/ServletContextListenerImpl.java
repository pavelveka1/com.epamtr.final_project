package by.epamtr.airline.context.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epamtr.airline.dao.connection_pool.exception.ConnectionPoolException;
import by.epamtr.airline.dao.connection_pool.impl.ConnectionPoolImpl;

/**
 * Application Lifecycle Listener implementation class ServletContextListenerImpl
 *
 */
public class ServletContextListenerImpl implements ServletContextListener {
	Logger rootLogger = LogManager.getRootLogger();
    /**
     * Default constructor. 
     */
    public ServletContextListenerImpl() {
    	rootLogger.info("Context of application is created");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         try {
			ConnectionPoolImpl.getInstance().shutdown();
		} catch (ConnectionPoolException e) {
			rootLogger.error(e);
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}