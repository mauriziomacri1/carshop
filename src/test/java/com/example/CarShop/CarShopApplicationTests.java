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
		CarDBRepository carRepository = new CarDBRepository();
		boolean result = carRepository.carInStore("FIAT");
		Assertions.assertEquals(true, result);
	}

	@Test
	void isSAABCarInStore() {
		CarDBRepository carRepository = new CarDBRepository();
		boolean result = carRepository.carInStore("SAAB");
		Assertions.assertEquals(true, result);
	}
}
