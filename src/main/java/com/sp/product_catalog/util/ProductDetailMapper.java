package com.sp.product_catalog.util;

import com.sp.product_catalog.dto.ProductCharacteristicDto;
import com.sp.product_catalog.dto.ProductDetailDto;
import com.sp.product_catalog.dto.ProductMediaDto;
import com.sp.product_catalog.model.ProductCharacteristic;
import com.sp.product_catalog.model.ProductDetails;
import com.sp.product_catalog.model.ProductMedia;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper implements Mapper<ProductDetails, ProductDetailDto, ProductDetailDto> {
    
    @Override
    public ProductDetails convertToModel(ProductDetailDto request) {
        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(request.getId());
        productDetails.setMediaList(request.getMediaList().stream()
                .map(pmd -> new ProductMedia(pmd.getUrl(),
                        pmd.getMediaType(), pmd.getWidth(), pmd.getHeight())).toList());
        productDetails.setDescription(request.getDescription());
        productDetails.setIngredient(request.getIngredient());
        productDetails.setWarningNote(request.getWarningNote());
        productDetails.setCharacteristic(request.getCharacteristics()
                .stream().map(chr -> new ProductCharacteristic(chr.getName(), chr.getValue())).toList());
        return productDetails;
    }

    @Override
    public ProductDetailDto convertToDto(ProductDetails model) {
        ProductDetailDto productDetails = new ProductDetailDto();
        productDetails.setId(model.getId());
        productDetails.setMediaList(model.getMediaList().stream()
                .map(pm -> new ProductMediaDto(pm.getUrl(),
                        pm.getMediaType(), pm.getWidth(), pm.getHeight())).toList());
        productDetails.setDescription(model.getDescription());
        productDetails.setIngredient(model.getIngredient());
        productDetails.setWarningNote(model.getWarningNote());
        productDetails.setCharacteristics(model.getCharacteristic()
                .stream().map(chr -> new ProductCharacteristicDto(chr.getName(), chr.getValue())).toList());
        return productDetails;
    }
    
}
