package quanaotreem.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SizeDTO implements Serializable, Comparable<SizeDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String size;

	@Override
	public int compareTo(SizeDTO o) {
		return size.compareTo(o.getSize());
	}

}
