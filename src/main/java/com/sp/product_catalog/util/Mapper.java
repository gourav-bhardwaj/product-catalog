package com.sp.product_catalog.util;

public interface Mapper<M,T,R> {
    M convertToModel(T request);
    R convertToDto(M model);
}
