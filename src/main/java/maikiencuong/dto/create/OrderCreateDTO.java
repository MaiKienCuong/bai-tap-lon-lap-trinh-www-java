package maikiencuong.dto.create;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.enumvalue.EnumPaymentMethod;

@Getter
@Setter
public class OrderCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Chưa có phương thức thanh toán")
	private EnumPaymentMethod paymentMethod;

	@NotBlank(message = "Địa chỉ giao hàng không được để trống")
	private String shipAddress;

	@NotBlank(message = "Chưa có thông tin khách hàng")
	private String customerId;

	@Valid
	@NotNull(message = "Hóa đơn trống")
	private List<OrderDetailCreateDTO> orderDetails;

}
