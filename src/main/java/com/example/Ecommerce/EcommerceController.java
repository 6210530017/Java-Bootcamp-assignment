package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

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

    @GetMapping("/addProduct/{productId}")
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

    @GetMapping("/checkout")
    public EcommerceResponse checkout() {
        return response;  //อาจารย์สมมติว่าผู้ซื้อเป็นสมาชิกแล้ว มีข้อมูลอยู่แล้ว ซึ่งผมออกแบบให้ข้อมูลที่อยู่ อยู่ในข้อมูล user แล้ว จึงไม่จำเป็นต้องทำอะไร return response ได้เลย
    }

    @GetMapping("/payment")
    public EcommerceResponse choose() {
        return response; // แสดงหน้าให้เลือกว่าจะชำระเงินช่องทางไหน
    }

    // Confirm to order
    @GetMapping("/payment/card/{cardNo}/{CVV}/{year}/{month}")
    public EcommerceResponse chooseCardMethod(@PathVariable String cardNo, @PathVariable String CVV, @PathVariable String year, @PathVariable String month) {
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        Card card = new Card(response.getUser().getName(),cardNo, CVV, YearMonth.of(yearInt,monthInt));
        response.setPaymentMethod(ecommerceService.payment("Credit/Debit Card", card));
        return response;
    }

    // Confirm to order
    @GetMapping("/payment/linepay")
    public EcommerceResponse chooseLinePayMethod() {
        response.setPaymentMethod(ecommerceService.payment("LINE Pay", response));
        return response;
    }

    @GetMapping("/summary")
    public EcommerceResponse purchase() {
        response.setPurchased(ecommerceService.purchase(response));
        response.setBasket(new Basket());
        return response;
    }
}
