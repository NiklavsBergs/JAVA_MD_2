package lv.venta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lv.venta.model.BusCategory;
import lv.venta.model.Cashier;
import lv.venta.model.City;
import lv.venta.model.Driver;
import lv.venta.model.Ticket;
import lv.venta.model.Trip;
import lv.venta.repos.ICashierRepo;
import lv.venta.repos.ICityRepo;
import lv.venta.repos.IDriverRepo;
import lv.venta.repos.ITicketRepo;
import lv.venta.repos.ITripRepo;

@SpringBootApplication
public class JavaMd2Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaMd2Application.class, args);
	}

	@Bean
	public CommandLineRunner testModel(IDriverRepo drRepo, ICashierRepo caRepo, ITicketRepo tiRepo, ITripRepo trRepo, ICityRepo ciRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
				Driver d1 = new Driver("Andris", "Kalnins", new ArrayList<>(Arrays.asList(BusCategory.largebus)));
				Driver d2 = new Driver("Zane", "Berzina", new ArrayList<>(Arrays.asList(BusCategory.largebus)));
				Driver noDriver = new Driver("Not", "Assigned", new ArrayList<>());
				drRepo.save(d1);
				drRepo.save(d2);
				drRepo.save(noDriver);
				
				Cashier c1 = new Cashier("Janis", "Abolins");
				Cashier c2 = new Cashier("Jana", "Kalnina");
				caRepo.save(c1);
				caRepo.save(c2);
				
				City ci1 = new City("Ventspils", "Ventspils autoosta");
				City ci2 = new City("Riga", "Rigas autoosta");
				City ci3 = new City("Liepaja", "Liepajas autoosta");
				ciRepo.save(ci1);
				ciRepo.save(ci2);
				ciRepo.save(ci3);
				
				Trip t1 = new Trip(d1, LocalDateTime.now(), 3, new ArrayList<>(Arrays.asList(ci1, ci2)));
				Trip t2 = new Trip(d2, LocalDateTime.now(), 2, new ArrayList<>(Arrays.asList(ci1, ci3)));
				Trip t3 = new Trip(d2, LocalDateTime.of(2023, 7, 14, 10, 34) , 2, new ArrayList<>(Arrays.asList(ci2, ci3)));
				trRepo.save(t1);
				trRepo.save(t2);
				trRepo.save(t3);
				
				Ticket ti1 = new Ticket(13, false, c1, t1);
				Ticket ti2 = new Ticket(10, true, c2, t2);
				Ticket ti3 = new Ticket(5, true, c2, t2);
				tiRepo.save(ti1);
				tiRepo.save(ti2);
				tiRepo.save(ti3);
				
			}
		};
	}
}
