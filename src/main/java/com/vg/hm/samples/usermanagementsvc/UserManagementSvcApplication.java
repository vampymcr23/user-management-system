package com.vg.hm.samples.usermanagementsvc;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableHystrix
@SpringBootApplication
public class UserManagementSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementSvcApplication.class, args);
	}
}
