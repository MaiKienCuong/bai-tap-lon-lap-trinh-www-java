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

	@NotNull(message = "Chưa có phương thức thanh toán (STORE/COD)")
	private EnumPaymentMethod paymentMethod;

	@NotBlank(message = "Địa chỉ giao hàng không được để trống")
	private String shipAddress;

	@NotNull(message = "Chưa có thông tin khách hàng")
	private Long customerId;

	@Valid
	@NotNull(message = "Chưa có sản phẩm nào trong hóa đơn")
	private List<OrderDetailCreateDTO> orderDetails;

}
