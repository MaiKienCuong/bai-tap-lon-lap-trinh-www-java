package maikiencuong.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import maikiencuong.enumvalue.EnumPaymentMethod;
import maikiencuong.enumvalue.EnumStatusOrder;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orderr {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_date", columnDefinition = "datetime")
	private LocalDateTime orderDate;

	@Column(name = "ship_address", columnDefinition = "nvarchar(255)")
	private String shipAddress;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "nvarchar(255)")
	private EnumStatusOrder status;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method", columnDefinition = "nvarchar(255)")
	private EnumPaymentMethod paymentMethod;
	
	@Column(name = "total")
	private Double total;

	@PrePersist
	public void prePersist() {
		sumTotal();
	}

	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ToString.Exclude
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;

	public Double sumTotal() {
		total = 0d;
		if (!orderDetails.isEmpty()) {
			orderDetails.forEach(x -> total += x.lineTotal());
		}
		return total;
	}

}
