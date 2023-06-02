package lv.venta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "ticket_table")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Ticket {
	@Column(name = "Idt")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idt;
	
	@Column(name = "Purchase_date_time")
	private LocalDateTime purchaseDateTime;
	
	@Column(name = "Price")
	@Min(1)
	@Max(100)
	private float price;
	
	@Column(name = "IsChild")
	private boolean isChild;
	
	@ManyToOne
	@JoinColumn(name="idc")
	private Cashier cashier;
	
	@ManyToOne
	@JoinColumn(name="idtr")
	private Trip trip;

	public Ticket(@Min(1) @Max(100) float price, boolean isChild, Cashier cashier,
			Trip trip) {
		this.purchaseDateTime = LocalDateTime.now();
		this.price = price;
		this.isChild = isChild;
		this.cashier = cashier;
		this.trip = trip;
	}
	
	
}
