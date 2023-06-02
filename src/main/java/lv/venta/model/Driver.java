package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "driver_table")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Driver {
	@Column(name = "Idd")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idd;
	
	@Column(name = "Name")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters")
	@NotNull
	private String name;
	
	@Column(name = "Surname")
	@Size(min = 3, max = 30)
	@Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters")
	@NotNull
	private String surname;
	
	@Column(name = "Categories")
	@NotNull
	private Collection<BusCategory> categories = new ArrayList<>();
	
	@OneToMany(mappedBy="driver")
	@ToString.Exclude
    private Collection<Trip> trips;

	public Driver(
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") @NotNull String name,
			@Size(min = 3, max = 30) @Pattern(regexp = "[A-Z]{1}[a-z\\ ]+", message = "Only latin letters") @NotNull String surname,
			@NotNull Collection<BusCategory> categories) {
		super();
		this.name = name;
		this.surname = surname;
		this.categories = categories;
	}
	
	
	
}
