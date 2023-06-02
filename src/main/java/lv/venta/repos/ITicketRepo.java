package lv.venta.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Ticket;

public interface ITicketRepo extends CrudRepository<Ticket, Long> {

	ArrayList<Ticket> findAllByIsChild(boolean b);

	ArrayList<Ticket> findAllByTripIdtr(long id);

	ArrayList<Ticket> findAllByCashierIdc(long id);

	ArrayList<Ticket> findAllByPriceLessThan(float price);

}
