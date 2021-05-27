package quanaotreem.dto.update;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import quanaotreem.enumvalue.EnumStatusOrder;

@Getter
@Setter
public class OrderUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message = "Id hóa đơn không được để trống")
	private Long id;

	private EnumStatusOrder status;

}
