package maikiencuong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class SizeColorInventory {

	private String color;

	private String size;

	private Integer inventory;

	public SizeColorInventory() {
		color = null;
		size = null;
		inventory = null;
	}

	public SizeColorInventory(String color) {
		this();
		this.color = color;
	}

	public SizeColorInventory(String size, Integer inventory) {
		this();
		this.size = size;
		if (inventory >= 0)
			this.inventory = inventory;
	}

	public SizeColorInventory(String color, String size, Integer inventory) {
		this();
		this.color = color;
		this.size = size;
		this.inventory = inventory;
	}

}
