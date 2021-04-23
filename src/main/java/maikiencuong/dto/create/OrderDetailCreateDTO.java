package maikiencuong.dto.create;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Số lượng mua không được để trống")
	@Min(value = 1, message = "Số lượng mua phải lớn hơn 0")
	private Integer quantity;

	@NotNull(message = "Chưa có giá sản phẩm")
	@Min(value = 0, message = "Giá của sản phẩm phải lớn hơn hoặc bằng 0")
	private Double price;

	@NotBlank(message = "Chưa có sản phẩm")
	private String subProductId;

}
