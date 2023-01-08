package com.microservice.basedomain.response;

import lombok.Data;

@Data
public class ResponseOrderEntity<T> {

    private int statusCode;
    private T entity;
}
