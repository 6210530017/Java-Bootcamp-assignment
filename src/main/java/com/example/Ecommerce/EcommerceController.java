package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

//    @GetMapping("/home")
//    public EcommerceResponse home() {
//        return new EcommerceResponse("Hello");
//    }

    @Autowired
    private EcommerceService ecommerceService;

    @GetMapping("/home")
    public EcommerceResponse loadHomePage() {
        return new EcommerceResponse(ecommerceService.getAllProduct());
    }

    @GetMapping("/catalog/search={keyword}")
    public EcommerceResponse search(@PathVariable String keyword) {
        return new EcommerceResponse(ecommerceService.searchProduct(keyword));
    }

    @GetMapping("/product/{productId}")
    public EcommerceResponse productDetail(@PathVariable String productId) {
        return new EcommerceResponse(ecommerceService.getProductById(productId));
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
