package lv.venta.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Ticket;
import lv.venta.model.Trip;
import lv.venta.repos.ITicketRepo;
import lv.venta.services.ITicketService;

@Service
public class TicketServiceImpl implements ITicketService{

	@Autowired
	private ITicketRepo ticketRepo;
	
	@Override
	public ArrayList<Ticket> selectAllChildTickets() {
		ArrayList<Ticket> filteredResults = ticketRepo.findAllByIsChild(true);
		return filteredResults;
	}

	@Override
	public ArrayList<Ticket> selectAllTicketsWherePriceIsLow(float price) {
		ArrayList<Ticket> filteredResults = ticketRepo.findAllByPriceLessThan(price);
		return filteredResults;
	}

	@Override
	public ArrayList<Ticket> selectAllTicketsByTripId(long id) {
		ArrayList<Ticket> filteredResults = ticketRepo.findAllByTripIdtr(id);
		return filteredResults;
	}

	@Override
	public float calculateIncomeOfTripByTripId(long id) {
		ArrayList<Ticket> filteredTickets = ticketRepo.findAllByTripIdtr(id);
		float sum = 0;
		for(Ticket temp: filteredTickets) {
			sum += temp.getPrice();
		}
	
		return sum;
	}

	@Override
	public float calculateIncomeOfCashierByCashierId(long id) {
		ArrayList<Ticket> filteredTickets = ticketRepo.findAllByCashierIdc(id);
		float sum = 0;
		for(Ticket temp: filteredTickets) {
			sum += temp.getPrice();
		}
	
		return sum;
	}

	@Override
	public void insertNewTicketByTripId(long idtr, Ticket ticket) {
		ticketRepo.save(ticket);
		
	}

}
