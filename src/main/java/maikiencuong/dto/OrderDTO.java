package maikiencuong.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.entity.EnumPaymentMethod;

@Getter
@Setter
public class OrderDTO {

	private String shipAddress;

	private EnumPaymentMethod paymentMethod;

	// ----------------------

	private CustomerDTO customer;

	private List<OrderDetailDTO> orderDetails;

}
