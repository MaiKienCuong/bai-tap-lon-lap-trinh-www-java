package maikiencuong.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SubProduct")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubProduct {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sku", columnDefinition = "varchar(50)")
	private String sku;

	@Column(name = "name", columnDefinition = "nvarchar(255)")
	private String name;

	@Column(name = "created_at", columnDefinition = "datetime")
	private LocalDateTime createdAt;

	@Column(name = "updated_at", columnDefinition = "datetime")
	private LocalDateTime updatedAt;

	@Column(name = "color", columnDefinition = "nvarchar(50)")
	private String color;

	@Column(name = "size", columnDefinition = "nvarchar(50)")
	private String size;

	private Integer inventory;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

}
