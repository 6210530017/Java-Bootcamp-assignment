package com.example.Ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EcommerceController {

    



    @GetMapping("/catalog/search={keyword}")
    public EcommerceResponse search(@PathVariable String keyword){
        return new EcommerceResponse("Object" + keyword);
    }


}
