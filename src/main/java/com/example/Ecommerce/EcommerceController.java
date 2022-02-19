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

    private void makeSureResponseIsExist() {
        if (response == null) {
            response = new EcommerceResponse();
        }
    }

    @GetMapping("/login/{username}")
    public EcommerceResponse login(@PathVariable String username) {
        if (response == null) {
            response = new EcommerceResponse(ecommerceService.login(username));
        } else {
            response.setUser(ecommerceService.login(username));
        }
        return response;
    }

    @GetMapping("/home")
    public EcommerceResponse loadHomePage() {
        makeSureResponseIsExist();
        response.setProducts(ecommerceService.getAllProduct());
        return response;

//        return new EcommerceResponse(ecommerceService.getAllProduct());
    }

    @GetMapping("/catalog/search={keyword}")
    public EcommerceResponse search(@PathVariable String keyword) {
        makeSureResponseIsExist();
        response.setProducts(ecommerceService.searchProduct(keyword));
        return response;

//        return new EcommerceResponse(ecommerceService.searchProduct(keyword));
    }

    @GetMapping("/product/{productId}")
    public EcommerceResponse productDetail(@PathVariable String productId) {
        makeSureResponseIsExist();
        response.setProducts(ecommerceService.getProductById(productId));
        return response;

//        return new EcommerceResponse(ecommerceService.getProductById(productId));
    }

    @PostMapping("/product/{productId}")
    public EcommerceResponse addToBasket(@PathVariable String productId) {
        makeSureResponseIsExist();
        response.setBasket(ecommerceService.addToBasket(productId,1, response.getBasket()));
        return response;
    }

    @GetMapping("/myCart")
    public EcommerceResponse getProductInBasket() {
        makeSureResponseIsExist();
        response.setProducts(null);  // สมมติว่าไม่มีการแนะนำสินค้าใดๆต่อ เลยทำการเคลียร์ทิ้ง ปกติ product คือสินค้าแนะนำหรือผลจากการ search ของ user
        return response;
    }

//    @GetMapping("/address/user={userName}")
//    public EcommerceResponse address(@PathVariable String userName){
//        return new EcommerceResponse(ecommerceService.userAddress(userName));
//    }


}
