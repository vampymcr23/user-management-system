package com.vg.hm.samples.usermanagementsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class UserManagementSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementSvcApplication.class, args);
	}
}
