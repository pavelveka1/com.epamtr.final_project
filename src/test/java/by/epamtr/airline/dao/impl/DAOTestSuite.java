package by.epamtr.airline.dao.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class DAOTestSuite {

	@RunWith(Suite.class)
	@Suite.SuiteClasses({ 
		SQLAircraftDAOTest.class,
		SQLFlightDAOTest.class, 
		SQLUserDAOTest.class, })
	public class DaoTestSuite {
	}
}
