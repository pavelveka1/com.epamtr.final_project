package by.epamtr.airline.dao;

import by.epamtr.airline.dao.exception.DAOException;
import by.epamtr.airline.entity.Aircraft;
import by.epamtr.airline.entity.AircraftType;

public interface AircraftDAO {
	void addAircraft(Aircraft aircraft) throws DAOException;

	void deliteAircraft(int idAircraft) throws DAOException;

	void updateAircraft(int idAircraft) throws DAOException;

	void changeAircraftStatus(int idAircraft, AircraftStatusDAO aircraftStatus) throws DAOException;

	void addAircraftType(AircraftType aircraftType) throws DAOException;

	void deliteAircraftType(int idAircraftType) throws DAOException;
}
