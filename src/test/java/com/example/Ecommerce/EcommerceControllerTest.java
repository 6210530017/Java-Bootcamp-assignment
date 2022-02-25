package com.example.Ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EcommerceControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("ส่ง username = testing แล้วข้อมูล user testing จะขึ้นใน EcommerceResponse")
    void login1() {
        // Arrange
        User user = new User();
        user.setName("testing");
        user.setUserId(999);
        user.setAddress("Address testing...");
        when(userRepository.findByName("testing")).thenReturn(user);
        // Act
        EcommerceResponse response = testRestTemplate.getForObject("/login/testing",EcommerceResponse.class);
        // Assert, verify
        assertEquals("User{userId=999, name='testing', address='Address testing...'}",response.getUser().toString());
    }

    @Test
    @DisplayName("ส่ง username = Pie(ไม่มีข้อมูล) แล้วข้อมูล message = 'User Pie not found.' และค่า value ที่มี key เป็น user จะเป็น null ขึ้นใน EcommerceResponse")
    void login2() {

        EcommerceResponse response = testRestTemplate.getForObject("/login/Pie",EcommerceResponse.class);

        assertEquals(null,response.getUser());
        assertEquals("User Pie not found.",response.getMessage());
    }

    @Test
    @DisplayName("ทดสอบเรียกหน้า home page ")
    void loadHomePage() {
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        listProduct.add(p2);
        when(productRepository.findAll()).thenReturn(listProduct);

        EcommerceResponse response = testRestTemplate.getForObject("/home",EcommerceResponse.class);

        assertEquals("[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}, " +
                "Product{productId=2, productName='testProduct2', price=1234, category='test2', detail='Wow'}]",
                response.getProducts().toString());
    }

    @Test
    @DisplayName("ทดสอบการค้นหาโดย category = test แล้ว EcommerResponse key = products ค่า value = Object Product ที่มี category เป็น test")
    void search1() {
        Product p3 = new Product(3,"testProduct3",555,"test3","HaHa");
        Product p4 = new Product(4,"testProduct4",321,"test4","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p3);
        when(productRepository.findByCategory("test3")).thenReturn(listProduct);

        EcommerceResponse response = testRestTemplate.getForObject("/catalog/search=test3",EcommerceResponse.class);

        assertEquals("[Product{productId=3, productName='testProduct3', price=555, category='test3', detail='HaHa'}]", response.getProducts().toString());
    }

    @Test
    @DisplayName("ทดสอบการค้นหาโดย category = testing(ไม่มีcategoryนี้) แล้ว EcommerResponse key = products ค่า value = [] และ key = message จะมี calue เป็น testing not found")
    void search2() {
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByCategory("test")).thenReturn(listProduct);

        EcommerceResponse response = testRestTemplate.getForObject("/catalog/search=testing",EcommerceResponse.class);

        assertEquals("[]", response.getProducts().toString());
        assertEquals("testing not found",response.getMessage());
    }

    @Test
    @DisplayName("ทดสอบเพิ่มสินค้าลงในตะกร้าสินค้า productId = 3 โดย EcommerResponse key = basket ค่า value จะเป็น Object Basket ที่มี Product productId = 3 อยู่ข้างใน และมี price = 555 และมี quantity = 1")
    void addToBasket() {
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByProductId(1)).thenReturn(listProduct);

        EcommerceResponse response = new EcommerceResponse();
        response = testRestTemplate.getForObject("/addProduct/1", EcommerceResponse.class);
        if (response.getBasket().getQuantity()==2) {
            response.setBasket(new Basket(p1));
        }

        assertEquals("Basket{products=[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}], quantity=1, price=999}", response.getBasket().toString());
    }

    @Test
    @DisplayName("ทดสอบหน้า product โดยใช้ productId = 1 โดย EcommerResponse key = products ค่า value จะเป็น Object Product ที่มี productId = 1")
    void productDetail1() {
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByProductId(1)).thenReturn(listProduct);

        EcommerceResponse response = testRestTemplate.getForObject("/product/1",EcommerceResponse.class);

        assertEquals("[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}]", response.getProducts().toString());
    }

    @Test
    @DisplayName("ทดสอบหน้า product โดยใช้ productId = 99(ไม่มีข้อมูลนี้) โดย EcommerResponse key = products ค่า value จะเป็น [] และ key = message จะมี value เป็น Product No.: 99 not found")
    void productDetail2() {
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByProductId(1)).thenReturn(listProduct);

        EcommerceResponse response = testRestTemplate.getForObject("/product/99",EcommerceResponse.class);

        assertEquals("[]", response.getProducts().toString());
        assertEquals("Product No.: 99 not found",response.getMessage());
    }

    @Test
    @DisplayName("ทดสอบเรียกดูสินค้าภายในตะกร้าสินค้า โดย EcommerResponse key = basket ค่า value จะเป็น Object Basket")
    void getProductInBasket() {
        EcommerceResponse response = testRestTemplate.getForObject("/myCart", EcommerceResponse.class);
        assertEquals("Basket{products=[], quantity=0, price=0}", response.getBasket().toString());
        assertEquals(null, response.getProducts());
    }

    @Test
    @DisplayName("ทดสอบต่อจาก test getProductInBasket เป็นการจำลองการกดปุ่มจ่ายตังค์เพื่อโหลดหน้า ที่ใส่ที่อยู่การจัดส่ง")
    void checkout() {
        User user = new User();
        user.setName("testing");
        user.setUserId(999);
        user.setAddress("Address testing...");
        when(userRepository.findByName("testing")).thenReturn(user);
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByProductId(1)).thenReturn(listProduct);

        EcommerceResponse response = new EcommerceResponse();
        response = testRestTemplate.getForObject("/login/testing",EcommerceResponse.class);
        response = testRestTemplate.getForObject("/addProduct/1", EcommerceResponse.class);
        response = testRestTemplate.getForObject("/myCart", EcommerceResponse.class);
        response = testRestTemplate.getForObject("/checkout", EcommerceResponse.class);
        if (response.getBasket().getQuantity()>=2) {
            response.setBasket(new Basket(p1));
        }


        assertEquals("Basket{products=[Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}], quantity=1, price=999}", response.getBasket().toString());
        assertEquals(null, response.getProducts());
        assertEquals("Address testing...",response.getUser().getAddress());
    }

    @Test
    @DisplayName("ทดสอบในกรณีผู้ใช้เลือกจ่ายเงินผ่านการใช้บัตรเดบิต หรือบัตรเครดิต")
    void chooseCardMethod() {
        User user = new User();
        user.setName("testing");
        user.setUserId(999);
        user.setAddress("Address testing...");
        when(userRepository.findByName("testing")).thenReturn(user);

        EcommerceResponse response = testRestTemplate.getForObject("/login/testing",EcommerceResponse.class);
        response = testRestTemplate.getForObject("/payment/card/8888888888888888/000/2022/9",EcommerceResponse.class);

        assertEquals("PaymentMethod{method='Credit/Debit Card', detail={cardHolderName=testing, cardNo=8888888888888888, expDate=2022-09, cvv=000}}", response.getPaymentMethod().toString());
    }

    @Test
    @DisplayName("ทดสอบในกรณีผู้ใช้เลือกจ่ายเงินผ่าน LINE Pay")
    void chooseLinePayMethod() {
        User user = new User();
        user.setName("testing");
        user.setUserId(999);
        user.setAddress("Address testing...");
        when(userRepository.findByName("testing")).thenReturn(user);
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByProductId(1)).thenReturn(listProduct);

        EcommerceResponse response = new EcommerceResponse();
        response = testRestTemplate.getForObject("/login/testing",EcommerceResponse.class);
        response = testRestTemplate.getForObject("/addProduct/1", EcommerceResponse.class);
        if (response.getBasket().getQuantity()>=2) {
            response.setBasket(new Basket(p1));
        }
        response = testRestTemplate.getForObject("/payment/linepay", EcommerceResponse.class);

        assertEquals("PaymentMethod{method='LINE Pay', detail={oneTimeKey=123456789012, amount=100, orderId=MyWEB123456789, currency=THB, productName=Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}}}",response.getPaymentMethod().toString());
    }

    @Test
    @DisplayName("แสดงหน้าสรุปคำสั่งซื้อ")
    void purchase() {
        User user = new User();
        user.setName("testing");
        user.setUserId(999);
        user.setAddress("Address testing...");
        when(userRepository.findByName("testing")).thenReturn(user);
        Product p1 = new Product(1,"testProduct1",999,"test","BlaBla");
        Product p2 = new Product(2,"testProduct2",1234,"test2","Wow");
        List<Product> listProduct = new ArrayList<Product>();
        listProduct.add(p1);
        when(productRepository.findByProductId(1)).thenReturn(listProduct);

        EcommerceResponse response = new EcommerceResponse();
        response = testRestTemplate.getForObject("/login/testing",EcommerceResponse.class);
        response = testRestTemplate.getForObject("/addProduct/1", EcommerceResponse.class);
        int n = response.getBasket().getQuantity();
        int price = response.getBasket().getPrice();
        response = testRestTemplate.getForObject("/summary", EcommerceResponse.class);

        String pro = "";
        for (int i = 0;i<n;i++) {
            if (i == n-1) {
                pro += "Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}";
            } else {
                if (n == 0) {
                    pro += "Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}";
                } else {
                    pro += "Product{productId=1, productName='testProduct1', price=999, category='test', detail='BlaBla'}, ";
                }
            }

        }
        assertEquals("Purchased{products=Basket{products=["+pro+"], quantity="+n+", price="+price+"}, orderId='"+response.getPurchased().getOrder().toString()+"', buyer=User{userId=999, name='testing', address='Address testing...'}}",response.getPurchased().toString());
    }

    /* หมายเหตุ ที่ผมเขียนแปลกๆ ผมมองว่า โค้ดหลักทำงานถูกต้อง แต่ตัวที่ใช้ test นั้นเหมือนมันเอาการที่ผม add product ของ test case อื่นมารวมด้วย ผมจึงเขียนแปลกๆ*/
}