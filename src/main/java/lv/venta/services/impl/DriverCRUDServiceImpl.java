package lv.venta.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.BusCategory;
import lv.venta.model.Driver;
import lv.venta.repos.IDriverRepo;
import lv.venta.services.IDriverCRUDService;

@Service
public class DriverCRUDServiceImpl implements IDriverCRUDService{

	@Autowired
	private IDriverRepo driverRepo;
	
	@Override
	public ArrayList<Driver> retrieveAllDrivers() {
		return (ArrayList<Driver>) driverRepo.findAll();
	}

	@Override
	public Driver retrieveDriverById(long id) {
		Driver dr = driverRepo.findByIdd(id);
		return dr;
	}

	@Override
	public void deleteDriverById(long id) {
		driverRepo.deleteById(id);
		
	}

	@Override
	public void insertNewDriver(String name, String surname) {
		Driver driver = new Driver(name, surname, new ArrayList<>());
		driverRepo.save(driver);
		
	}

	@Override
	public void updateDriverById(long id, String name, String surname) {
		Driver driver = retrieveDriverById(id);
		driver.setName(name);
		driver.setSurname(surname);
		driverRepo.save(driver);
	}
	
}
