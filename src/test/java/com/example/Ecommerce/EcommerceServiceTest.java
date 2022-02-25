package com.example.Ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EcommerceServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("ทดสอบเรียกสินค้าทั้งหมด")
    void getAllProduct() {
        // Arrange
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        listProduct.add(p2);
        when(productRepository.findAll()).thenReturn(listProduct);
        // Act
        EcommerceService ecommerceService = new EcommerceService();
        ecommerceService.setProductRepository(productRepository);
        List<Product> result = ecommerceService.getAllProduct();
        // Assert
        assertEquals("[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}, Product{productId=2, productName='testProduct2', price=1234, category='test2', detail='Wow'}]",result.toString());
    }

    @Test
    @DisplayName("ทดสอบเรียกสินค้าด้วย productId")
    void getProductById() {
        // Arrange
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByProductId(1)).thenReturn(listProduct);
        // Act
        EcommerceService ecommerceService = new EcommerceService();
        ecommerceService.setProductRepository(productRepository);
        List<Product> result = ecommerceService.getProductById("1");
        // Assert
        assertEquals("[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}]",result.toString());
    }

    @Test
    @DisplayName("ทดสอบการ login ของ user")
    void login() {
        // Arrange
        User user = new User();
        user.setName("testing");
        user.setUserId(999);
        user.setAddress("Address testing...");
        when(userRepository.findByName("testing")).thenReturn(user);
        // Act
        EcommerceService ecommerceService = new EcommerceService();
        ecommerceService.setUserRepository(userRepository);
        User result = ecommerceService.login("testing");
        // Assert
        assertEquals("User{userId=999, name='testing', address='Address testing...'}",result.toString());
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มสินค้าลงตะกร้าสินค้า")
    void addToBasket() {
        // Arrange
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByProductId(1)).thenReturn(listProduct);
        Basket basket = new Basket();
        // Act
        EcommerceService ecommerceService = new EcommerceService();
        ecommerceService.setProductRepository(productRepository);
        Basket result = ecommerceService.addToBasket("1",1,basket);
        // Assert
        assertEquals("Basket{products=[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}], quantity=1, price=999}",result.toString());
    }

    @Test
    @DisplayName("ทดสอบการค้นหาสินค้าโดยใช้ชื่อ category")
    void searchProduct() {
        // Arrange
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByCategory("test")).thenReturn(listProduct);
        // Act
        EcommerceService ecommerceService = new EcommerceService();
        ecommerceService.setProductRepository(productRepository);
        List<Product> result = ecommerceService.searchProduct("test");
        // Assert
        assertEquals("[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}]",result.toString());
    }

    @Test
    @DisplayName("ทดสอบการจ่ายเงินด้วย debit/credit card")
    void payment() {
        // Arrange
        Card card = new Card();
        card.setCardHolderName("test");
        card.setCardNo("8888888888888888");
        card.setCVV("888");
        card.setExpDate(8,2022);
        // Act
        EcommerceService ecommerceService = new EcommerceService();
        PaymentMethod result = ecommerceService.payment("credit card", card);
        // Assert
        assertEquals("PaymentMethod{method='credit card', detail=Card{cardHolderName='test', cardNo='8888888888888888', CVV='888', expDate=2022-08}}",result.toString());
    }

    @Test
    @DisplayName("ทดสอบการจ่ายเงินด้วย LINE Pay")
    void testPayment() {
        // Arrange
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        EcommerceResponse ecommerceResponse = new EcommerceResponse();
        Basket basket = new Basket(p1);
        ecommerceResponse.setBasket(basket);
        // Act
        EcommerceService ecommerceService = new EcommerceService();
        PaymentMethod result = ecommerceService.payment("LINE Pay", ecommerceResponse);
        // Assert
        assertEquals("PaymentMethod{method='LINE Pay', detail=Linepaydetail{oneTimeKey='123456789012', amount=100, orderId='MyWEB123456789', currency='THB', productName='Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}'}}",result.toString());
    }

    @Test
    @DisplayName("ทดสอบการสรุปคำสั่งซื้อ")
    void purchase() {
        // Arrange
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        EcommerceResponse ecommerceResponse = new EcommerceResponse();
        Basket basket = new Basket(p1);
        ecommerceResponse.setBasket(basket);
        User user = new User();
        user.setName("testing");
        user.setUserId(999);
        user.setAddress("Address testing...");
        ecommerceResponse.setUser(user);
        // Act
        EcommerceService ecommerceService = new EcommerceService();
        Purchased result = ecommerceService.purchase(ecommerceResponse);
        // Assert
        assertEquals("Purchased{products=Basket{products=[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}], quantity=1, price=999}, orderId='"+result.getOrder()+"', buyer=User{userId=999, name='testing', address='Address testing...'}}",result.toString());
    }
}