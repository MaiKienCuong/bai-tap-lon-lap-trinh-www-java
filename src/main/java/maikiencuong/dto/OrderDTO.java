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

	private String shipAddress;

	private EnumStatusOrder status;
	
	private EnumPaymentMethod paymentMethod;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private LocalDateTime orderDate;

	private Double total;

	private CustomerDTO customer;

	private List<OrderDetailDTO> orderDetails;

}
