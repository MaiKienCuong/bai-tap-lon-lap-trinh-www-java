package quanaotreem.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import quanaotreem.entity.Image;

@Getter
@Setter
public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private Double price;

	private List<Image> imagesUrl;

	private String marker;

	private Double discount;

	private Integer views;

	private String origin;

	private Double tax;

	private String shortDescription;

	private String longDescription;

	private String material;

	private boolean active;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private LocalDateTime createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private LocalDateTime updatedAt;

	private SupplierDTO supplier;

	private CategoryDTO category;

	private List<SubProductDTO> subProducts;

}
