package maikiencuong.entity;

import java.time.LocalDateTime;
import java.util.List;

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

	@Column(name = "url", columnDefinition = "varchar(255)")
	private String url;

	@Column(name = "images_folder", columnDefinition = "varchar(255)")
	private String imagesFolder;

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

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@ToString.Exclude
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private List<SubProduct> subProducts;

	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "category_id")
	private Category category;

}
