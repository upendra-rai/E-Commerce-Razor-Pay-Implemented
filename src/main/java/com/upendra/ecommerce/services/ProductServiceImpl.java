package com.upendra.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.upendra.ecommerce.dtos.PagedResponseDto;
import com.upendra.ecommerce.dtos.ProductRequest;
import com.upendra.ecommerce.dtos.ProductResponse;
import com.upendra.ecommerce.entities.Product;
import com.upendra.ecommerce.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public PagedResponseDto<ProductResponse> getProducts(Pageable pageable) {
		log.info(">> getProducts({})", pageable);
		Page<Product> productPage = productRepository.findAll(pageable);
		List<ProductResponse> list = new ArrayList<>();
		productPage.getContent().forEach(res -> {
			ProductResponse data = convertEntityToDto(res);
			list.add(data);
		});
		return PagedResponseDto.<ProductResponse>builder().list(list).page(productPage.getNumber())
				.size(productPage.getSize()).totalElements(productPage.getTotalElements()).build();

	}

	@Override
	@Transactional
	public ProductResponse getProductById(String productId) {
		log.info(">> getProducts({})", productId);
		Product product = productRepository.findByUuid(productId).get();
		return convertEntityToDto(product);
	}

	@Override
	@Transactional
	public void createProduct(ProductRequest productRequest) {
		log.info(">> updateProduct({})", productRequest);
		Product product = convertDtoToEntity(productRequest);
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void updateProduct(String productId, ProductRequest productRequest) {
		log.info(">> updateProduct({}, {})", productId, productRequest);
		Product product = productRepository.findByUuid(productId).get();
		modelMapper.map(productRequest, product);
	}

	@Override
	@Transactional
	public void deleteProduct(String productId) {
		log.info(">> getProducts()");
		Product product = productRepository.findByUuid(productId).get();
		productRepository.delete(product);
	}

	private ProductResponse convertEntityToDto(Product product) {
		ProductResponse resp = modelMapper.map(product, ProductResponse.class);
		resp.setProductId(product.getUuid());
		return resp;

	}

	private Product convertDtoToEntity(ProductRequest productRequest) {
		return modelMapper.map(productRequest, Product.class);

	}

}
