package app.planner.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class ServiceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// bi-directional many-to-one association to ServiceS
	@OneToMany(mappedBy = "serviceType")
	private Set<ServiceS> services;

	// bi-directional many-to-one association to ServiceTypeTranslation
	@OneToMany(mappedBy = "serviceType")
	private Set<ServiceTypeTranslation> serviceTypeTranslations;

}