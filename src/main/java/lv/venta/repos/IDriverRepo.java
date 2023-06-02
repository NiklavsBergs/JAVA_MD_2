package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Driver;

public interface IDriverRepo extends CrudRepository<Driver, Long>{

	Driver findByIdd(long id);

	Driver findByName(String string);

}
