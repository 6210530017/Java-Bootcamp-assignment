package com.example.Ecommerce;

import org.springframework.stereotype.Component;

@Component
public class PaymentMethod {

    private String method;
    private Object detail;

    public PaymentMethod(String method, Object detail) {
        this.method = method;
        this.detail = detail;
    }

    public PaymentMethod() {
        method = null;
        detail = null;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "method='" + method + '\'' +
                ", detail=" + detail +
                '}';
    }
}


