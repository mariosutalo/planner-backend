package app.planner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	private String name;

	private Point position;

}
