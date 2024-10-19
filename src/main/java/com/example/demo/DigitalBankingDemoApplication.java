package com.example.demo;

import com.example.demo.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigitalBankingDemoApplication {
	@Autowired
	private static Dao dao;
	public static void main(String[] args)
	{
		SpringApplication.run(DigitalBankingDemoApplication.class, args);
		dao.initDao();
	}

}
