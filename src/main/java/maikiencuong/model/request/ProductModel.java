package maikiencuong.model.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {

	private Long id;

	private BigDecimal price;

	private double discount;

}
