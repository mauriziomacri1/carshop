package com.example.CarShop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest

public class CarShopApplicationTests {


	@Test
	void isFIATCarInStore() {
		CarRepository carRepository = new CarRepository();
		boolean result = carRepository.carInStore("FIAT");
		Assertions.assertEquals(false, result);
	}

	@Test
	void isSAABCarInStore() {
		CarRepository carRepository = new CarRepository();
		boolean result = carRepository.carInStore("SAAB");
		Assertions.assertEquals(true, result);
	}
}
