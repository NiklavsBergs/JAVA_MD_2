package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Trip;

public interface ITripService {
	ArrayList<Trip> selectAllTripsByCityTitle(String title);
	ArrayList<Trip> selectAllTripsByDriverId(long id);
	ArrayList<Trip> selectAllTripsToday();
	void changeTripDriverByDriverId(long idd, long idtr);
}
