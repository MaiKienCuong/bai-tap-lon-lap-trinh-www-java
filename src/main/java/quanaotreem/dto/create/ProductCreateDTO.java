package quanaotreem.dto.create;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Size(max = 255, message = "Tên sản phẩm không được vượt quá 255 ký tự")
	private String name;

	@NotNull(message = "Giá sản phẩm không được để trống")
	@Min(value = 0, message = "Giá của sản phẩm phải lớn hơn hoặc bằng 0")
	@DecimalMax(value = "10000000000", message = "Giá sản phẩm phải nhỏ hơn 10,000,000,000")
	private Double price;

	@Min(value = 0, message = "Giảm giá phải lớn hơn hoặc bằng 0")
	@Max(value = 1, message = "Giảm giá phải nhỏ hơn hoặc bàng 1")
	private double discount = 0;

	private List<Image> imagesUrl;

	@Size(max = 255, message = "Xuất xứ sản phẩm không được vượt quá 255 ký tự")
	private String origin;

	@NotNull(message = "Chưa nhập thuế sản phẩm")
	@Min(value = 0, message = "Thuế của sản phẩm phải lớn hơn hoặc bằng 0")
	@Max(value = 1, message = "Thuế của sản phẩm phải nhỏ hơn 1")
	private double tax;

	@Size(max = 500, message = "Mô tả ngắn không được vượt quá 500 ký tự")
	private String shortDescription;

	@Size(max = 1000, message = "Mô tả dài không được vượt quá 1000 ký tự")
	private String longDescription;

	@Size(max = 255, message = "Chất liệu không được vượt quá 255 ký tự")
	private String material;

	@NotNull(message = "Chưa có thông tin nhà cung cấp")
	private Long supplierId;

	@NotNull(message = "Chưa có thông tin loại sản phẩm")
	private Long categoryId;

	@Valid
	@NotNull(message = "Chưa có danh sách sản phẩm con")
	private List<SubProductCreateDTO> subProducts;

}
