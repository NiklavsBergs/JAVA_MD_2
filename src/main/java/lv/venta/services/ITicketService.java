package lv.venta.services;

import java.util.ArrayList;

import lv.venta.model.Ticket;

public interface ITicketService {
	ArrayList<Ticket> selectAllChildTickets();
	ArrayList<Ticket> selectAllTicketsWherePriceIsLow(float price);
	ArrayList<Ticket> selectAllTicketsByTripId(long id);
	float calculateIncomeOfTripByTripId(long id);
	float calculateIncomeOfCashierByCashierId(long id);
	void insertNewTicketByTripId(long idtr, Ticket ticket);
	
}
