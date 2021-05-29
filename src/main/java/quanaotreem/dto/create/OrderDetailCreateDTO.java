package quanaotreem.dto.create;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
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
	@Max(value = 1000, message = "Chỉ được mua tối đa 1000 sản phẩm trên một lần")
	private Integer quantity;

	@NotNull(message = "Chưa có giá sản phẩm")
	@Min(value = 0, message = "Giá của sản phẩm phải lớn hơn hoặc bằng 0")
	@DecimalMax(value = "10000000000", message = "Giá sản phẩm phải nhỏ hơn 10,000,000,000")
	private Double price;

	@NotBlank(message = "Chưa có sản phẩm")
	private String subProductId;

}
