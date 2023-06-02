package lv.venta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.venta.services.ITripService;

@Controller
public class TripController {
	
	@Autowired
	private ITripService tripService;
	
	@GetMapping(value="/trip/showAll/city/{citytitle}")
	public String getTripsByCityFunc(@PathVariable("citytitle") String citytitle, Model model){
		model.addAttribute("trips", tripService.selectAllTripsByCityTitle(citytitle));
		return"all-trips-page";
	}
	
	@GetMapping(value="/trip/showAll/driver/{driverid}")
	public String getTripsByDriverIdFunc(@PathVariable("driverid") long driverid, Model model){
		model.addAttribute("trips", tripService.selectAllTripsByDriverId(driverid));
		return"all-trips-page";
	}
	
	@GetMapping(value="/trip/showAll/today")
	public String getTripsTodayFunc(Model model){
		model.addAttribute("trips", tripService.selectAllTripsToday());
		return"all-trips-page";
	}
	
	@GetMapping(value="/trip/changeDriver/{tripid}/{driverid}")
	public String getChangeDriverByIdFunc(@PathVariable("tripid") long tripid, @PathVariable("driverid") long driverid, Model model){
		tripService.changeTripDriverByDriverId(tripid, driverid);
		model.addAttribute("trips", tripService.selectAllTripsToday());
		return"all-trips-page";
	}
}
