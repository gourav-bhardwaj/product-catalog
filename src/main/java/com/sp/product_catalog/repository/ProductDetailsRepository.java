package com.sp.product_catalog.repository;

import com.mongodb.MongoException;
import com.mongodb.client.result.DeleteResult;
import com.sp.product_catalog.model.ProductDetails;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.Optional;

public interface ProductDetailsRepository {

    @Retryable(maxAttemptsExpression = "${retry.max-attempts}",
            retryFor = {MongoException.class},
            recover = "genericRecover",
            backoff = @Backoff(delayExpression = "${retry.backoff.delay}"))
    ProductDetails save(ProductDetails productDetails);

    @Retryable(maxAttemptsExpression = "${retry.max-attempts}",
            retryFor = {MongoException.class},
            recover = "genericRecover",
            backoff = @Backoff(delayExpression = "${retry.backoff.delay}"))
    Optional<ProductDetails> findById(String id);

    @Retryable(maxAttemptsExpression = "${retry.max-attempts}",
            retryFor = {MongoException.class},
            recover = "genericRecover",
            backoff = @Backoff(delayExpression = "${retry.backoff.delay}"))
    DeleteResult deleteById(String id);

    @Recover
    <T> T genericRecover(MongoException mongoException, T object);


}
