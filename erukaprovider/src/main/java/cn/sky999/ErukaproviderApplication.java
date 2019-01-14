package cn.sky999;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.sky999.mapper")
public class ErukaproviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErukaproviderApplication.class, args);
	}

}

