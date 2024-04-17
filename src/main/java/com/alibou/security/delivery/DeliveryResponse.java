package com.alibou.security.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryResponse {
    private final String message;

    public DeliveryResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
