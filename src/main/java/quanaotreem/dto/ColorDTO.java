package quanaotreem.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ColorDTO implements Serializable, Comparable<ColorDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String color;

	@Override
	public int compareTo(ColorDTO o) {
		return color.compareTo(o.getColor());
	}

}
