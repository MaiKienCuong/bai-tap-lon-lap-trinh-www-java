package quanaotreem.dto.create;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Tên của loại sản phẩm không được để trống")
	@Size(max = 255, message = "Tên không được vượt quá 255 ký tự")
	private String name;

}
