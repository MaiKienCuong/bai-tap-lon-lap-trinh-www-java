package maikiencuong.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import maikiencuong.views.Views;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(Include.NON_NULL)
public class InvenColorSize {

	@Column(name = "color", columnDefinition = "nvarchar(50)")
	private String color;

	@JsonView(Views.Level1.class)
	@Column(name = "size", columnDefinition = "nvarchar(50)")
	private String size;

	@JsonView(Views.Level2.class)
	private Integer inventory;

}
