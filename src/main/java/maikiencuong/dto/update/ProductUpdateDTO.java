package maikiencuong.dto.update;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.dto.CategoryDTO;
import maikiencuong.dto.SupplierDTO;

@Getter
@Setter
public class ProductUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message = "Id sản phẩm không được để trống")
	private Long id;

	@NotBlank(message = "Tên sản phẩm không được để trống")
	private String name;

	@NotNull(message = "Giá sản phẩm không được để trống")
	private Double price;

	@NotBlank(message = "Chưa có thumbnail sản phẩm")
	private String url;

	private String imagesFolder;

	private String marker;

	private double discount;

	@NotBlank(message = "Chưa có xuất xứ sản phẩm")
	private String origin;

	@NotNull(message = "Chưa nhập thuế sản phẩm")
	private double tax;

	@NotBlank(message = "Chưa có mô tả ngắn về sản phẩm")
	private String shortDescription;

	@NotBlank(message = "Chưa có mô tả dài về sản phẩm")
	private String longDescription;

	@NotBlank(message = "Chưa có chất liệu sản phẩm")
	private String material;

	@NotNull(message = "Chưa có thông tin nhà cung cấp")
	private SupplierDTO supplier;

	@NotNull(message = "Chưa có thông tin loại sản phẩm")
	private CategoryDTO category;

}
