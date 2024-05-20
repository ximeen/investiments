package org.ximenes.investiments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestimentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestimentsApplication.class, args);
	}

}
