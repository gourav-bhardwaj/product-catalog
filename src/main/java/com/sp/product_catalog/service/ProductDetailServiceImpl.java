package com.sp.product_catalog.service;

import com.mongodb.client.result.DeleteResult;
import com.sp.product_catalog.dto.ProductDetailDto;
import com.sp.product_catalog.model.ProductCharacteristic;
import com.sp.product_catalog.model.ProductDetails;
import com.sp.product_catalog.repository.ProductDetailsRepository;
import com.sp.product_catalog.util.AppConstants;
import com.sp.product_catalog.util.ProductDetailMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Slf4j
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailsRepository repository;
    private final ProductDetailMapper mapper;

    public ProductDetailServiceImpl(ProductDetailsRepository repository, ProductDetailMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public ProductDetailDto saveProductDetail(ProductDetailDto productDetailRequest) {
        log.info("Save method invoked ProductDetail : {}", productDetailRequest);
        ProductDetails productDetails = mapper.convertToModel(productDetailRequest);
        return mapper.convertToDto(repository.save(productDetails));
    }

    @Transactional
    @Override
    public ProductDetailDto updateProductDetail(String id, ProductDetailDto request) {
        log.info("Update method invoked ProductDetail : {}", request);
        Optional<ProductDetails> productDetailsOpt = repository.findById(id);
        if (productDetailsOpt.isEmpty()) {
            log.info(AppConstants.NO_PRODUCT_FOUND_LOG, id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        }
        ProductDetails productDetails = productDetailsOpt.get();
        productDetails.setDescription(request.getDescription());
        productDetails.setIngredient(request.getIngredient());
        productDetails.setWarningNote(request.getWarningNote());
        productDetails.setCharacteristic(request.getCharacteristics()
                .stream().map(chr -> new ProductCharacteristic(chr.getName(), chr.getValue())).toList());
        return mapper.convertToDto(repository.save(productDetails));
    }

    @Override
    public ProductDetailDto getProductDetail(String id) {
        log.info("Fetch method invoked for ID : {}", id);
        Optional<ProductDetails> productDetailsOpt = repository.findById(id);
        if (productDetailsOpt.isEmpty()) {
            log.info(AppConstants.NO_PRODUCT_FOUND_LOG, id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
        }
        return mapper.convertToDto(productDetailsOpt.get());
    }

    @Override
    public void deleteProductDetail(String id) {
        log.info("Delete method invoked for ID : {}", id);
        DeleteResult deleteResult = repository.deleteById(id);
        if (deleteResult.getDeletedCount() > 0) {
            log.info("Deleted records : {}", deleteResult.getDeletedCount());
        } else {
            log.info("No record found for deletion!!");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid product Id: " + id);
        }
    }


}
