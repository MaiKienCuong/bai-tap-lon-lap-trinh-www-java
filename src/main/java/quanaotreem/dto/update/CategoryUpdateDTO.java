package quanaotreem.dto.update;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message = "Id loại sản phẩm không được để trống")
	private Long id;

	@NotBlank(message = "Tên loại sản phẩm không được để trống")
	@Size(max = 255, message = "Tên không được vượt quá 255 ký tự")
	private String name;

}
