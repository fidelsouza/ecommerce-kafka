package com.job.ecommerce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.job.ecommerce.Order;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	
	@Autowired
	private OrderSender orderSender;
	
	@Override
    public void run(String... strings) throws Exception {
		
        Order foo = new Order("Spring Kafka", Double.valueOf(489));
        System.out.println("Sending data: "+foo.getValue());
        orderSender.send(foo);
    }

}
