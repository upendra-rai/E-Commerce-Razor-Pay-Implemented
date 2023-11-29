package com.upendra.ecommerce.services;

import org.springframework.data.domain.Pageable;

import com.upendra.ecommerce.dtos.PagedResponseDto;
import com.upendra.ecommerce.dtos.ProductRequest;
import com.upendra.ecommerce.dtos.ProductResponse;

public interface ProductService {

	PagedResponseDto<ProductResponse> getProducts(Pageable pageable);

	ProductResponse getProductById(String productId);

	void createProduct(ProductRequest productRequest);

	void updateProduct(String productId, ProductRequest productRequest);

	void deleteProduct(String productId);

}
