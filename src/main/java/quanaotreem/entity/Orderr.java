package quanaotreem.entity;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import quanaotreem.enumvalue.EnumPaymentMethod;
import quanaotreem.enumvalue.EnumStatusOrder;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(name = "order_date", updatable = false)
	private LocalDateTime orderDate;

	@Nationalized
	@Column(name = "ship_address", length = 500)
	private String shipAddress;

	@Nationalized
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private EnumStatusOrder status;

	@Nationalized
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method", length = 50)
	private EnumPaymentMethod paymentMethod;

	private double total;

	@PrePersist
	public void prePersist() {
		status = EnumStatusOrder.PENDING;
		sumTotal();
	}

	@ToString.Exclude
	@JoinColumn(name = "customer_id")
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Customer customer;

	@ToString.Exclude
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;

	public double sumTotal() {
		total = 0d;
		if (!orderDetails.isEmpty()) {
			orderDetails.forEach(detail -> total += detail.lineTotal());
		}
		return total;
	}

}
