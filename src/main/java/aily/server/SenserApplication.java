package aily.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableScheduling
public class SenserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenserApplication.class, args);
	}

}