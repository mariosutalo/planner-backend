package app.planner.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
public class ServiceImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Timestamp createdAt;

	private String imageType;

	@ManyToOne(fetch=FetchType.LAZY)
	private ServiceS service;

	@OneToMany(mappedBy="serviceImage")
	private Set<ServiceImageVariant> serviceImageVariants;

}