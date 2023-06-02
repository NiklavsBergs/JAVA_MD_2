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
import lv.venta.model.Driver;
import lv.venta.model.Ticket;
import lv.venta.services.ITicketService;
import lv.venta.services.ITripService;

@Controller
public class TicketController {
	
	@Autowired
	private ITicketService ticketService;
	
	@GetMapping(value="/ticket/showAll/onlyChild")
	public String getTicketsByIsChildFunc( Model model){
		model.addAttribute("tickets", ticketService.selectAllChildTickets());
		return"all-tickets-page";
	}
	
	@GetMapping(value="/ticket/showAll/less/{price}")
	public String getTicketsByPriceFunc(@PathVariable("price") float price, Model model){
		model.addAttribute("tickets", ticketService.selectAllTicketsWherePriceIsLow(price));
		return"all-tickets-page";
	}
	
	@GetMapping(value="/ticket/showAll/trip/{tripid}")
	public String getTicketsByTripIdFunc(@PathVariable("tripid") long tripid, Model model){
		model.addAttribute("tickets", ticketService.selectAllTicketsByTripId(tripid));
		return"all-tickets-page";
	}
	
	@GetMapping(value="/ticket/calculate/trip/{tripid}")
	public String getCalculateEarningsByTripFunc(@PathVariable("tripid") long tripid, Model model){
		model.addAttribute("earnings", ticketService.calculateIncomeOfTripByTripId(tripid));
		return"ticket-income-page";
	}
	
	@GetMapping(value="/ticket/calculate/cashier/{cashierid}")
	public String getCalculateEarningsByCashierFunc(@PathVariable("cashierid") long cashierid, Model model){
		
		model.addAttribute("earnings", ticketService.calculateIncomeOfCashierByCashierId(cashierid));
		return"ticket-income-page";
	}
	
	@GetMapping(value="/ticket/add/{tripid}")
	public String getAddTicketFunc(@PathVariable("tripid") long tripid, Model model){
		
		model.addAttribute("ticket", new Ticket());
		return"add-ticket-page";
	}
	
	@PostMapping(value="/ticket/add/{tripid}")
	public String postAddTicketFunc(@PathVariable("tripid") long tripid, @Valid Ticket ticket, BindingResult result){
		if(!result.hasErrors()) {
			try {
				ticketService.insertNewTicketByTripId(tripid, ticket);
				return "redirect:/ticket/showAll/trip/1";
			}
			catch(Exception e) {
				e.printStackTrace();
				return "error-page";
			}
		}
		else {
			return "add-ticket-page";
		}
	}
}
