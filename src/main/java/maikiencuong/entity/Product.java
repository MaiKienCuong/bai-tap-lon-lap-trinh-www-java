package maikiencuong.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "Product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", columnDefinition = "nvarchar(255)")
	private String name;

	@Column(name = "price")
	private Double price;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<Image> imagesUrl;

	@Column(name = "marker", columnDefinition = "nvarchar(50)")
	private String marker;

	@Column(name = "discount")
	private double discount;

	@Column(name = "views")
	private Integer views;

	@Column(name = "origin", columnDefinition = "nvarchar(255)")
	private String origin;

	@Column(name = "tax")
	private double tax;

	@Column(name = "short_description", columnDefinition = "ntext")
	private String shortDescription;

	@Column(name = "long_description", columnDefinition = "ntext")
	private String longDescription;

	@Column(name = "material", columnDefinition = "nvarchar(255)")
	private String material;

	private boolean active;

	@Column(name = "created_at", columnDefinition = "datetime")
	private LocalDateTime createdAt;

	@Column(name = "updated_at", columnDefinition = "datetime")
	private LocalDateTime updatedAt;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@ToString.Exclude
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@ToString.Exclude
	@JoinColumn(name = "category_id")
	private Category category;

	@ToString.Exclude
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SubProduct> subProducts;

	@PrePersist
	public void prePersist() {
		views = 0;
		active = true;
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

}
