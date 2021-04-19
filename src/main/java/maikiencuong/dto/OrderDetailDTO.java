package maikiencuong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {

	private Integer quantity;

	private Double price;

	// ------------------------

	private SubProductDTO subProduct;

}
