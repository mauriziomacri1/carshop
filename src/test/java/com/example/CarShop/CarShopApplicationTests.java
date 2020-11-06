package com.example.CarShop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CarShopApplicationTests {

	@Test
	void TestListLength() {
		CarController carController = new CarController();
		List<Car>  aList = new ArrayList<>();
		aList = carController.carrepos.getCars();
		//asserTrue(aList	.length() > 5);
	}

}
