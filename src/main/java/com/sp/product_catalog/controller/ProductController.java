package com.sp.product_catalog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sp.product_catalog.dto.ProductDetailDto;
import com.sp.product_catalog.dto.ProductRequest;
import com.sp.product_catalog.dto.ProductResponse;
import com.sp.product_catalog.dto.ProductResponsePage;
import com.sp.product_catalog.service.ProductDetailService;
import com.sp.product_catalog.service.ProductService;
import com.sp.product_catalog.util.Utility;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final ProductDetailService productDetailService;
    private final Utility utility;

    public ProductController(ProductService service, ProductDetailService productDetailService, Utility utility) {
        this.service = service;
        this.productDetailService = productDetailService;
        this.utility = utility;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> saveProductEndpoint(@RequestBody @Valid ProductRequest productRequest) throws JsonProcessingException {
        log.info("Product creation endpoint invoked");
        ProductRequest sanitizedRequestBody = utility.sanitizeRequestBody(productRequest, productRequest.getClass());
        ProductResponse productResponse = service.saveProduct(sanitizedRequestBody);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<ProductResponse> saveProductEndpoint(@PathVariable String productId,
                                                               @RequestBody @Valid ProductRequest productRequest) throws JsonProcessingException {
        log.info("Product modification endpoint invoked");
        ProductRequest sanitizedRequestBody = utility.sanitizeRequestBody(productRequest, productRequest.getClass());
        String sanitizedProductId = utility.sanitizeObject(productId, String.class);
        ProductResponse productResponse = service.updateProduct(sanitizedProductId, sanitizedRequestBody);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponse> saveProductEndpoint(@PathVariable String productId) {
        log.info("Product deletion endpoint invoked");
        String sanitizedProductId = utility.sanitizeObject(productId, String.class);
        ProductResponse productResponse = service.deleteProduct(sanitizedProductId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/list")
    public ResponseEntity<ProductResponsePage> getProductWithPaginationEndpoint(@PathVariable String categoryId,
                                                                                @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                                                @RequestParam(required = false, defaultValue = "10") Integer limit) {
        log.info("Product fetch endpoint invoked");
        ProductResponsePage productWithPagination = service.getProductWithPagination(offset, limit, categoryId);
        return new ResponseEntity<>(productWithPagination, HttpStatus.OK);
    }

    //---------------Product Details-------------------------

    @PostMapping("/details")
    public ResponseEntity<ProductDetailDto> saveProductDetails(@RequestBody ProductDetailDto productDetailDto) {
        ProductDetailDto productDetail = productDetailService.saveProductDetail(productDetailDto);
        return ResponseEntity.ok(productDetail);
    }

    @PutMapping("/{id}/details")
    public ResponseEntity<ProductDetailDto> updateProductDetails(@PathVariable String id,
                                                                 @RequestBody ProductDetailDto productDetailDto) {
        ProductDetailDto productDetail = productDetailService.updateProductDetail(id, productDetailDto);
        return ResponseEntity.ok(productDetail);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ProductDetailDto> getProductDetails(@PathVariable String id) {
        ProductDetailDto productDetailDto = productDetailService.getProductDetail(id);
        return ResponseEntity.ok(productDetailDto);
    }

    @DeleteMapping("/{id}/details")
    public ResponseEntity<String> deleteProductDetails(@PathVariable String id) {
        productDetailService.deleteProductDetail(id);
        return ResponseEntity.ok("Product with ID: %s deleted successfully".formatted(id));
    }

}
