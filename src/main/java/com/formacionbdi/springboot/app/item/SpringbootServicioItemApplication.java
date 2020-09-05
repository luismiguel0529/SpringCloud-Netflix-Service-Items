package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@RibbonClient(name = "servicio-productos")
@EnableCircuitBreaker // Control de errores, latencias
@EnableEurekaClient // balanceo de carga
@EnableFeignClients // cliente http
@SpringBootApplication

// la libreria tiene Jpa por ende este debe ser exclido porque este proyecto no maneja persistencia.
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class SpringbootServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemApplication.class, args);
	}

}
