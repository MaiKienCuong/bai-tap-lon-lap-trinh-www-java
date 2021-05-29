package quanaotreem.dto.create;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import quanaotreem.entity.Image;

@Getter
@Setter
public class ProductCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
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

	@Valid
	@NotNull(message = "Chưa có danh sách sản phẩm con")
	private List<SubProductCreateDTO> subProducts;

}
