package app.planner.domain;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;


@Entity
@Getter
@Setter
public class FavoriteService implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Timestamp createdAt;

	private UUID userId;

	@ManyToOne(fetch=FetchType.LAZY)
	private ServiceS service;

	public FavoriteService() {
	}

}