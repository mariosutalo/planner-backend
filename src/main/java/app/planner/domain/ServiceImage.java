package app.planner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class ServiceImage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	private String imageType;

	@ManyToOne(fetch=FetchType.LAZY)
	private ServiceS service;

	@OneToMany(mappedBy="serviceImage", cascade = CascadeType.ALL)
	private Set<ServiceImageVariant> serviceImageVariants;

}