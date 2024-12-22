package com.sp.product_catalog.service;

import com.sp.product_catalog.dto.ProductRequest;
import com.sp.product_catalog.dto.ProductResponse;
import com.sp.product_catalog.dto.ProductResponsePage;
import com.sp.product_catalog.model.Product;
import com.sp.product_catalog.repository.CategoryRepository;
import com.sp.product_catalog.repository.ProductRepository;
import com.sp.product_catalog.util.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository, CategoryRepository categoryRepository, ProductMapper mapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductResponse saveProduct(ProductRequest product) {
        if (!categoryRepository.existsById(UUID.fromString(product.getCategoryId()))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found: " + product.getCategoryId());
        }
        try {
            Product productModel = repository.save(mapper.convertToModel(product));
            log.info("Product created successfully");
            return mapper.convertToDto(productModel);
        } catch (Exception e) {
            log.error("Product creation failed : ", e);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                    "Product creation failed");
        }
    }

    @Override
    public ProductResponse updateProduct(String productId, ProductRequest product) {
        if (!repository.existsById(UUID.fromString(productId))) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Product not found");
        }
        try {
            Product productModel = mapper.convertToModel(product);
            productModel.setId(UUID.fromString(productId));
            productModel = repository.save(productModel);
            log.info("Product updated successfully");
            return mapper.convertToDto(productModel);
        } catch (Exception e) {
            log.error("Product update failed : ", e);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                    "Product update failed");
        }
    }

    @Override
    public ProductResponsePage getProductWithPagination(Integer offset, Integer limit, String categoryId) {
        if (!categoryRepository.existsById(UUID.fromString(categoryId))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found: " + categoryId);
        }
        try {
            Page<Product> productPage = repository.findAllByCategoryId(UUID.fromString(categoryId), Pageable.ofSize(limit)
                    .withPage(offset));
            List<ProductResponse> productResponseList = productPage.stream().map(mapper::convertToDto).toList();
            log.info("Product fetch successfully");
            return ProductResponsePage.builder()
                    .products(productResponseList)
                    .totalElements(productPage.getTotalElements())
                    .page(productPage.getTotalPages())
                    .size(productPage.getSize())
                    .build();
        } catch (Exception e) {
            log.error("Product fetch failed : ", e);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                    "Product fetch failed");
        }
    }

    @Override
    public ProductResponse deleteProduct(String productId) {
        Optional<Product> product = repository.findById(UUID.fromString(productId));
        if (product.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Product not found");
        }
        try {
            repository.deleteById(UUID.fromString(productId));
            log.info("Product deletion successfully");
            return mapper.convertToDto(product.get());
        } catch (Exception e) {
            log.error("Product deletion failed : ", e);
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                    "Product deletion failed");
        }
    }

}
