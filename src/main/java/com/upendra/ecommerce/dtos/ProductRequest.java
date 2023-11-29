package com.upendra.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRequest {

	private String productName;

	private String description;

	private Double actualPrice;

	private Double discountPrice;


}
