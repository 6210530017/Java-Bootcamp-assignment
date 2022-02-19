package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

    @Autowired
    private EcommerceService ecommerceService;

    private EcommerceResponse response;

    @GetMapping("/login/{username}")
    public EcommerceResponse login(@PathVariable String username) {
        response = new EcommerceResponse(ecommerceService.login(username));
        return response;
    }

    @GetMapping("/home")
    public EcommerceResponse loadHomePage() {
        response.setProducts(ecommerceService.getAllProduct());
        return response;

//        return new EcommerceResponse(ecommerceService.getAllProduct());
    }

    @GetMapping("/catalog/search={keyword}")
    public EcommerceResponse search(@PathVariable String keyword) {
        response.setProducts(ecommerceService.searchProduct(keyword));
        return response;

//        return new EcommerceResponse(ecommerceService.searchProduct(keyword));
    }

    @GetMapping("/product/{productId}")
    public EcommerceResponse productDetail(@PathVariable String productId) {
        response.setProducts(ecommerceService.getProductById(productId));
        return response;

//        return new EcommerceResponse(ecommerceService.getProductById(productId));
    }

//    @PostMapping("/product/{productId}")
//    public EcommerceResponse addToBasket(@PathVariable String productId) {
//
//    }

//    @GetMapping("/address/user={userName}")
//    public EcommerceResponse address(@PathVariable String userName){
//        return new EcommerceResponse(ecommerceService.userAddress(userName));
//    }


}
