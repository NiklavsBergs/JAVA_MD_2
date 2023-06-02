package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.City;

public interface ICityRepo extends CrudRepository<City, Long> {

}
