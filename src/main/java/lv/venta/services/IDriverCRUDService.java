package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Driver;

public interface IDriverCRUDService {
	ArrayList<Driver> retrieveAllDrivers();
	Driver retrieveDriverById(long id);
	void insertNewDriver(String name, String surname);
	void updateDriverById(long id, String name, String surname);
	void deleteDriverById(long id);
}
