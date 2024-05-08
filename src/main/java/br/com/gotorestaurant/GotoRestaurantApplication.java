package br.com.gotorestaurant;

import br.com.gotorestaurant.infra.security.TokenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GotoRestaurantApplication {

	public static void main(String[] args) {

		SpringApplication.run(GotoRestaurantApplication.class, args);

	}
}
