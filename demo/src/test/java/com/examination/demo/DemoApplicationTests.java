package com.examination.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.examination.demo.dao.AdminDAO;



@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private AdminDAO adminDAO;

	@Test
	void contextLoads() {
	}

	
}
