package br.com.consulting_delivery.javer_api_client;

import br.com.consulting_delivery.javer_api_client.dto.DadosCadastroCliente;
import br.com.consulting_delivery.javer_api_client.feign.DataManagerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class JaverApiClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaverApiClientApplication.class, args);
	}

}
