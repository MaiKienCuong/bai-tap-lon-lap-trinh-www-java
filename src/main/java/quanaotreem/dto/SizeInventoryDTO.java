package quanaotreem.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
public class SizeInventoryDTO implements Serializable, Comparable<SizeInventoryDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "subProductId")
	private Long id;

	private String size;

	private Integer inventory;

	@Override
	public int compareTo(SizeInventoryDTO o) {
		return size.compareTo(o.getSize());
	}
}
