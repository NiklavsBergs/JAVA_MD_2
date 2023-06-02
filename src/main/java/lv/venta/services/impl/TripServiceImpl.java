package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Driver;
import lv.venta.model.Trip;
import lv.venta.repos.IDriverRepo;
import lv.venta.repos.ITripRepo;
import lv.venta.services.ITripService;

import java.time.LocalDateTime;

@Service
public class TripServiceImpl implements ITripService{
	
	@Autowired
	private ITripRepo tripRepo;
	
	@Autowired
	private IDriverRepo driverRepo;

	@Override
	public ArrayList<Trip> selectAllTripsByCityTitle(String title) {
		ArrayList<Trip> filteredResults = tripRepo.findAllByCitiesTitle(title);
		return filteredResults;
	}

	@Override
	public ArrayList<Trip> selectAllTripsByDriverId(long id) {
		ArrayList<Trip> filteredResults = tripRepo.findAllByDriverIdd(id);
		return filteredResults;
	}

	@Override
	public ArrayList<Trip> selectAllTripsToday() {
		LocalDateTime time = LocalDateTime.now();
		ArrayList<Trip> trips = (ArrayList<Trip>) tripRepo.findAll();
		ArrayList<Trip> filteredResults = new ArrayList<>();
		for(Trip trip : trips) {
			if(time.getDayOfYear()==trip.getStartDateTime().getDayOfYear()) {
				filteredResults.add(trip);
			}
		}
		return filteredResults;
	}

	@Override
	public void changeTripDriverByDriverId(long idd, long idtr) {
		Trip trip = tripRepo.findByIdtr(idtr);
		Driver driver = driverRepo.findByIdd(idd);
		
		trip.setDriver(driver);
		
		tripRepo.save(trip);
	}

}
