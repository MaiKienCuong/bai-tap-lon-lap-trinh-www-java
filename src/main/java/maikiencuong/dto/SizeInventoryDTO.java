package maikiencuong.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SizeInventoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long subproductId;

	@EqualsAndHashCode.Exclude
	private String size;

	@EqualsAndHashCode.Exclude
	private Integer inventory;
}
