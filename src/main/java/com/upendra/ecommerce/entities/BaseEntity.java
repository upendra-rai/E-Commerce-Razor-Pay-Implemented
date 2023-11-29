package com.upendra.ecommerce.entities;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, exclude = { "isActive" })
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid = UUID.randomUUID().toString();

	@Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
	private Boolean isActive = true;

}
