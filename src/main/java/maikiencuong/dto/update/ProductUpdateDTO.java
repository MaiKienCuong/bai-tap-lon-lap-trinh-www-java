package maikiencuong.dto.update;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.entity.Image;

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
	@Min(value = 0, message = "Giá của sản phẩm phải lớn hơn hoặc bằng 0")
	private Double price;

	private String marker;

	@Min(value = 0, message = "Giảm giá phải lớn hơn hoặc bằng 0")
	private double discount = 0;

	private List<Image> imagesUrl;

	private String origin;

	private boolean active;

	@NotNull(message = "Chưa nhập thuế sản phẩm")
	@Min(value = 0, message = "Thuế của sản phẩm phải lớn hơn hoặc bằng 0")
	private double tax;

	private String shortDescription;

	private String longDescription;

	private String material;

	@NotNull(message = "Chưa có thông tin nhà cung cấp")
	private Long supplierId;

	@NotNull(message = "Chưa có thông tin loại sản phẩm")
	private Long categoryId;

	@NotNull(message = "Chưa có danh sách sản phẩm con")
	@Valid
	private List<SubProductUpdateDTO> subProducts;

}
