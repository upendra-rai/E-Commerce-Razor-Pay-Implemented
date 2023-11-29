package com.upendra.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.ecommerce.dtos.PagedResponseDto;
import com.upendra.ecommerce.dtos.ProductRequest;
import com.upendra.ecommerce.dtos.ProductResponse;
import com.upendra.ecommerce.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductContoller {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<PagedResponseDto<ProductResponse>> getProducts(Pageable pageable) {
		log.info(">> getProducts()");
		return ResponseEntity.ok(productService.getProducts(pageable));
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable String productId) {
		log.info(">> getProductById({})", productId);
		return ResponseEntity.ok(productService.getProductById(productId));
	}

	@PostMapping
	public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductRequest productRequest) {
		log.info(">> createProduct({})", productRequest);
		productService.createProduct(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Void> updateProduct(@PathVariable String productId,
			@Valid @RequestBody ProductRequest productRequest) {
		log.info(">> updateProduct({}, {})", productId, productRequest);
		productService.updateProduct(productId, productRequest);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
		log.info(">> deleteProduct({})", productId);
		try {
			productService.deleteProduct(productId);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Can't Delete");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}