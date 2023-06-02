package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "city_table")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class City {
	@Column(name = "Idc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idc;
	
	@Column(name = "Title")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters")
	@NotNull
	private String title;
	
	@Column(name = "Adress")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters")
	@NotNull
	private String addressOfStation;
	
	@ManyToMany(mappedBy = "cities")
	@ToString.Exclude
	private Collection<Trip> trips = new ArrayList<>();

	public City(
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") @NotNull String title,
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") @NotNull String addressOfStation) {
		this.title = title;
		this.addressOfStation = addressOfStation;
	}
	
	public void addTrip(Trip trip){
		if(!trips.contains(trip)) {
			trips.add(trip);
		}
	}
	
	public void removeCourse(Trip trip){
		if(trips.contains(trip)) {
			trips.remove(trip);
		}
	}
	
}
