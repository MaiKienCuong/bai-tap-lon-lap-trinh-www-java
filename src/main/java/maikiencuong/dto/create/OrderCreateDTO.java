package maikiencuong.dto.create;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.enumvalue.EnumPaymentMethod;
import maikiencuong.enumvalue.EnumStatusOrder;

@Getter
@Setter
public class OrderCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	@JsonIgnore
	private LocalDateTime orderDate = LocalDateTime.now();

	@JsonIgnore
	private EnumStatusOrder status = EnumStatusOrder.PENDING;

	private EnumPaymentMethod paymentMethod;

	private String shipAddress;

	private String customerId;

	private List<OrderDetailCreateDTO> orderDetails;

}
