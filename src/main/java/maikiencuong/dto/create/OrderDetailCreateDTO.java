package maikiencuong.dto.create;

import java.io.Serializable;

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
	private Integer quantity;

	@NotNull(message = "Chưa có giá sản phẩm")
	private Double price;

	@NotBlank(message = "Chưa có sản phẩm")
	private String subProductId;

}
