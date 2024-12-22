package com.sp.product_catalog.repository;

import com.mongodb.MongoException;
import com.mongodb.client.result.DeleteResult;
import com.sp.product_catalog.model.ProductDetails;
import com.sp.product_catalog.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Slf4j
@Repository
public class ProductDetailsRepositoryImpl implements ProductDetailsRepository {

    private final MongoTemplate mongoTemplate;

    public ProductDetailsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ProductDetails save(ProductDetails productDetails) {
        return mongoTemplate.save(productDetails, AppConstants.PRODUCT_DETAILS_COLLECTION);
    }

    @Override
    public Optional<ProductDetails> findById(String id) {
        Query query = new Query(Criteria.where(AppConstants._ID).is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, ProductDetails.class, AppConstants.PRODUCT_DETAILS_COLLECTION));
    }

    @Override
    public DeleteResult deleteById(String id) {
        Query query = new Query(Criteria.where(AppConstants._ID).is(id));
        return mongoTemplate.remove(query, AppConstants.PRODUCT_DETAILS_COLLECTION);
    }

    @Override
    public <T> T genericRecover(MongoException mongoException, T object) {
        log.error(AppConstants.RECOVERY_METHOD_LOG, mongoException, object);
        return object;
    }

}
