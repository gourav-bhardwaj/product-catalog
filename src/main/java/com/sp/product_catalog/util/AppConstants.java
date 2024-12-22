package com.sp.product_catalog.util;

public interface AppConstants {
    String RECOVERY_METHOD_LOG = "RECOVER : Mongo connection retries exhausted due to : {}, Kindly have a look on given product details: {}";
    String NO_PRODUCT_FOUND_LOG = "No productDetails found for ID: {}";
    String PRODUCT_DETAILS_COLLECTION = "product_details";
    String _ID = "_id";
}
