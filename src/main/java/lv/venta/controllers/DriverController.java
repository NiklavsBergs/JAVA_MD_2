package lv.venta.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.venta.model.Trip;
import lv.venta.repos.IDriverRepo;
import lv.venta.repos.ITripRepo;
import lv.venta.model.Driver;
import lv.venta.services.IDriverCRUDService;
import lv.venta.services.ITripService;

@Controller
public class DriverController {
	
	@Autowired
	private IDriverCRUDService driverService;
	
	@Autowired
	private ITripService tripService;
	
	@Autowired
	private ITripRepo tripRepo;
	
	@Autowired
	private IDriverRepo driverRepo;
	
	@GetMapping(value="/driver/showAll")
	public String getAllDriverssFunc(Model model){
		model.addAttribute("drivers", driverService.retrieveAllDrivers());
		return"all-drivers-page";
	}
	
	@GetMapping(value="/driver/showAll/{id}")
	public String getDriverByIdFunc(@PathVariable("id") long id,Model model){
		model.addAttribute("drivers", driverService.retrieveDriverById(id));
		return"all-drivers-page";
	}
	
	@GetMapping(value="/driver/remove/{id}")
	public String removeDriverByIdFunc(@PathVariable("id") long id,Model model){
		ArrayList<Trip> tripsByDriver = tripService.selectAllTripsByDriverId(id);
		Driver noDriver = driverRepo.findByName("Not");
		for(Trip trip : tripsByDriver) {
			trip.setDriver(noDriver);
			tripRepo.save(trip);
		}
		driverService.deleteDriverById(id);
		
		model.addAttribute("drivers", driverService.retrieveAllDrivers());
		return"all-drivers-page";
	}
	
	@GetMapping(value="/driver/addNew")
	public String getAddDriverFunc(Model model) {
		model.addAttribute("driver", new Driver());
		return "add-driver-page";
	}
	
	@PostMapping(value="/driver/addNew")
	public String postAddDriverFunc(@Valid Driver driver, BindingResult result) {
		
		if(!result.hasErrors()) {
			try {
				driverService.insertNewDriver(driver.getName(), driver.getSurname());
				return "redirect:/driver/showAll";
			}
			catch(Exception e) {
				e.printStackTrace();
				return "error-page";
			}
		}
		else {
			return "add-driver-page";
		}
	
	}
	
	@GetMapping(value="/driver/update/{id}")
	public String getUpdateDriverFunc(@PathVariable("id") long id, Model model) {
		Driver driver = driverService.retrieveDriverById(id);
		model.addAttribute("driver", driver);
		return "edit-driver-page";
	}
	
	@PostMapping(value="/driver/update/{id}")
	public String postUpdateDriverFunc(@PathVariable("id") long id, @Valid Driver driver, BindingResult result) {
		
		if(!result.hasErrors()) {
			try {
				driverService.updateDriverById(id, driver.getName(), driver.getSurname());
				return "redirect:/driver/showAll";
			}
			catch(Exception e) {
				e.printStackTrace();
				return "error-page";
			}
		}
		else {
			return "edit-driver-page";
		}
	
	}
}
