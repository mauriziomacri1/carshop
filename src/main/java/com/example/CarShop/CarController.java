package com.example.CarShop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class CarController {
    CarRepository carrepos = new CarRepository();

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

        @GetMapping("/carlist")
    public String carlist(Model model) {
        model.addAttribute("carlist", carrepos.carList);
        return "index";
    }

   /* @GetMapping("/dummy")
    public String carDetails(Model model) {
        Car car = carrepos.getCar(1);
        System.out.println("Car info: " + car.getBrand());
        model.addAttribute("car", car);
        return "cardetails";
    }*/
    @GetMapping("/cardetails/{id}")
    public String carDetails(Model model, @PathVariable int id) {
        Car car = carrepos.getCar(id);
        model.addAttribute("car", car);
        return "cardetails";
    }
}
