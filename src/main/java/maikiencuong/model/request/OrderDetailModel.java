package maikiencuong.model.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailModel {

	private Integer quantity;

	private BigDecimal price;

	// ------------------------

	private SubProductModel subProduct;
	
}
