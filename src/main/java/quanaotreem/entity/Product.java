package quanaotreem.entity;

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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UpdateTimestamp;

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
@DynamicUpdate
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Nationalized
	@Column(length = 255)
	private String name;

	private double price;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
	private List<Image> imagesUrl;

	@Nationalized
	@Column(length = 50)
	private String marker;

	private double discount;

	private int views;

	@Nationalized
	@Column(length = 255)
	private String origin;

	private double tax;

	@Nationalized
	@Column(name = "short_description", length = 500)
	private String shortDescription;

	@Nationalized
	@Column(name = "long_description", length = 1000)
	private String longDescription;

	@Nationalized
	@Column(length = 255)
	private String material;

	private boolean active;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@ManyToOne
	@ToString.Exclude
	@JoinColumn(name = "category_id")
	private Category category;

	@ToString.Exclude
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubProduct> subProducts;

	@PrePersist
	public void prePersist() {
		active = true;
	}

}
