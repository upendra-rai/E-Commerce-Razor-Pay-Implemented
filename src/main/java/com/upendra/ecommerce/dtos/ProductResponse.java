package com.upendra.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
	
	private String productId;

	private String productName;

	private String description;

	private Double actualPrice;

	private Double discountPrice;

}
