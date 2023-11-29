package com.upendra.ecommerce.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Product extends BaseEntity {

	private String productName;

	private String description;

	private Double actualPrice;

	private Double discountPrice;
}
