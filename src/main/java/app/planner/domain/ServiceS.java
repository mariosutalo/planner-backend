package app.planner.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@Table(name = "service")
@Getter
@Setter
public class ServiceS implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	private String email;

	@Column(name = "end_price")
	private BigDecimal endPrice;

	@Column(name = "owner_id")
	private UUID ownerId;

	@Column(name = "phone_number")
	private String phoneNumber;

	private Point position;

	private String properties;

	@Column(name = "start_price")
	private BigDecimal startPrice;

	@Column(name = "street_address")
	private String streetAddress;

	private String title;

	// bi-directional many-to-one association to FavoriteService
	@OneToMany(mappedBy = "service")
	private Set<FavoriteService> favoriteServices;

	// bi-directional many-to-one association to ServiceType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_type_id")
	private ServiceType serviceType;

	// bi-directional many-to-one association to ServiceImage
	@OneToMany(mappedBy = "service")
	private Set<ServiceImage> serviceImages;

}