package br.com.consulting_delivery.javer_api_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JaverApiClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaverApiClientApplication.class, args);
	}

}
