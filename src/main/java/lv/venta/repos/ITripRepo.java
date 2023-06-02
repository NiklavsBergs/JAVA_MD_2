package lv.venta.repos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Trip;

public interface ITripRepo extends CrudRepository<Trip, Long> {

	ArrayList<Trip> findAllByDriverIdd(long id);

	Trip findByIdtr(long idtr);

	ArrayList<Trip> findAllByCitiesTitle(String title);

	ArrayList<Trip> findAllByStartDateTimeBefore(LocalDateTime time);

}
