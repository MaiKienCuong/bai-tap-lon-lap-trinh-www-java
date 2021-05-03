package maikiencuong.dto.create;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubProductCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Tên không được để trống")
	private String name;

	@NotBlank(message = "Màu sắc không được để trống")
	private String color;

	@NotBlank(message = "Size không được để trống")
	private String size;

	@NotNull(message = "Số lượng tồn không được để trống")
	@Min(value = 1, message = "Số lượng tồn phải lớn hơn 0")
	private Integer inventory;

}
