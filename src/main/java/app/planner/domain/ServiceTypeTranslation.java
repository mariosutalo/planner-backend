package app.planner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the service_type_translation database table.
 * 
 */
@Entity
@Getter
@Setter
public class ServiceTypeTranslation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	private Language language;

	// bi-directional many-to-one association to ServiceType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_type_id")
	private ServiceType serviceType;
}