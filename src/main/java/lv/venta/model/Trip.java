package lv.venta.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "trip_table")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Trip {
	@Column(name = "Idtr")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idtr;
	
	@ManyToOne
	@JoinColumn(name="idd")
	private Driver driver;
	
	@Column(name = "Start_date_time")
	private LocalDateTime startDateTime;
	
	@Column(name = "duration")
	@Min(0)
	@Max(10)
	private float duration;
	
	@OneToMany(mappedBy="trip")
	@ToString.Exclude
    private Collection<Ticket> tickets;
	
	@ManyToMany
	@JoinTable(
			  name = "trip_city_table", 
			  joinColumns = @JoinColumn(name = "idtr"), 
			  inverseJoinColumns = @JoinColumn(name = "idc"))
	private Collection<City> cities = new ArrayList<>();

	public Trip(Driver driver, LocalDateTime startDateTime, @Min(0) @Max(10) float duration, Collection<City> cities) {
		super();
		this.driver = driver;
		this.startDateTime = startDateTime;
		this.duration = duration;
		this.cities = cities;
	}
	
	public void addCity(City city){
		if(!cities.contains(city)) {
			cities.add(city);
		}
	}

	public void removeCity(City city){
		if(cities.contains(city)) {
			cities.remove(city);
		}
	}
}
