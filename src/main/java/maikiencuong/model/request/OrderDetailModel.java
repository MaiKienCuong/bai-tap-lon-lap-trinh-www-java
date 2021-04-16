package maikiencuong.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailModel {

	private Integer quantity;

	private Double price;

	// ------------------------

	private SubProductModel subProduct;

}
