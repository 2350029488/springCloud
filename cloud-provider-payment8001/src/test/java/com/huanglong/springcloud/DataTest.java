package com.huanglong.springcloud;

import com.huanglong.springcloud.entity.Payment;
import com.huanglong.springcloud.service.IPaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DataTest {
    @Autowired
    IPaymentService paymentService;
    @Test
    public void test(){
        List<Payment> list = paymentService.list();
        list.forEach(System.out::println);
    }

    @Test
    public void add(){
        int gggggg = paymentService.create(new Payment(null, "ggggggsss"));
        System.out.println(gggggg);
    }
    @Test
    public void select(){
        Payment paymentById = paymentService.getPaymentById(6l);
        System.out.println(paymentById);
    }
}
