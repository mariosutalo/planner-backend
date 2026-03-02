package app.planner.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ServiceImageVariant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer height;

	private String url;

	private Integer width;

	// bi-directional many-to-one association to ServiceImage
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_image_id")
	private ServiceImage serviceImage;

}