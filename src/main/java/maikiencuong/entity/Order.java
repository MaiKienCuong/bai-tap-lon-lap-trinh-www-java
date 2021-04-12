package maikiencuong.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "shipped_date")
	private Date shippedDate;

	@Column(name = "ship_address", columnDefinition = "nvarchar(255)")
	private String shipAddress;

	@Column(name = "status", columnDefinition = "nvarchar(255)")
	private String status;

	@Column(name = "total")
	private BigDecimal total;

	@Column(name = "payment_method", columnDefinition = "nvarchar(255)")
	private String paymentMethod;

	@Column(name = "payment_status", columnDefinition = "nvarchar(255)")
	private String paymentStatus;

	// ----------------------

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetailss;

}
