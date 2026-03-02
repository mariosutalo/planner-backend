package app.planner.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Language {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String name;

	@OneToMany(mappedBy="language")
	private Set<ServiceTypeTranslation> serviceTypeTranslations;

}