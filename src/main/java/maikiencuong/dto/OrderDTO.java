package maikiencuong.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.enumvalue.EnumPaymentMethod;
import maikiencuong.enumvalue.EnumStatusOrder;

@Getter
@Setter
public class OrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private LocalDateTime orderDate;

	private String shipAddress;

	private EnumStatusOrder status;

	private Double total;

	private EnumPaymentMethod paymentMethod;

	private CustomerDTO customer;

	private List<OrderDetailDTO> orderDetails;

}
