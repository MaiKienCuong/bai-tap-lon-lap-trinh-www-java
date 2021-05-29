package quanaotreem.dto.update;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubProductUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
//	@NotNull(message = "Id sản phẩm không được để trống")
	private Long id;

	@NotBlank(message = "Tên không được để trống")
	@Size(max = 255, message = "Tên không được vượt quá 255 ký tự")
	private String name;

	@NotBlank(message = "Màu sắc không được để trống")
	@Size(max = 50, message = "Màu sắc không được vượt quá 50 ký tự")
	private String color;

	@NotBlank(message = "Size không được để trống")
	@Size(max = 50, message = "Size không được vượt quá 50 ký tự")
	private String size;

	@NotNull(message = "Số lượng tồn không được để trống")
	@Min(value = 1, message = "Số lượng tồn phải lớn hơn 0")
	@Max(value = 1000000, message = "Số lượng tồn không được vượt quá 1,000,000")
	private Integer inventory;

}
