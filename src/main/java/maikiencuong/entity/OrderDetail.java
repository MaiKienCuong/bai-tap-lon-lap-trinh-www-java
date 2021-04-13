package maikiencuong.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "OrderDetail")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDetail {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	// ------------------------

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "subProduct_id")
	private SubProduct subProduct;

}
