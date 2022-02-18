package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

//    @GetMapping("/home")
//    public EcommerceResponse home() {
//        return null;
//    }

    @Autowired
    private EcommerceService ecommerceService;

    @GetMapping("/catalog/search={keyword}")
    public EcommerceResponse search(@PathVariable String keyword){
        return new EcommerceResponse(ecommerceService.searchProduct(keyword));
    }

    @GetMapping("/address/user={userName}")
    public EcommerceResponse address(@PathVariable String userName){
        return new EcommerceResponse(ecommerceService.userAddress(userName));
    }


}
