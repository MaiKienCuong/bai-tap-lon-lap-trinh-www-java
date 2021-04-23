package maikiencuong.dto.create;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.dto.CategoryDTO;
import maikiencuong.dto.SupplierDTO;

@Getter
@Setter
public class ProductCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Tên sản phẩm không được để trống")
	private String name;

	@NotNull(message = "Giá sản phẩm không được để trống")
	private Double price;

	@NotBlank(message = "Chưa có thumbnail sản phẩm")
	private String url;

	private String imagesFolder;

	private String marker;

	private double discount = 0;

	private String origin;

	@NotNull(message = "Chưa nhập thuế sản phẩm")
	private double tax;

	private String shortDescription;

	private String longDescription;

	private String material;

	@NotNull(message = "Chưa có thông tin nhà cung cấp")
	private SupplierDTO supplier;

	@NotNull(message = "Chưa có thông tin loại sản phẩm")
	private CategoryDTO category;

	@NotNull(message = "Chưa có danh sách sản phẩm con")
	private List<SubProductCreateDTO> subProducts;

}
