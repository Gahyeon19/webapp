package com.example.jwbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan		//같은 레벨에 있는 파일들 Scan 하기
@SpringBootApplication
public class JwbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwbookApplication.class, args);
	}

}
