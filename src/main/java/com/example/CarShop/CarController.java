package com.example.CarShop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "AddCarForm";
    }

    @PostMapping("/save")
    public String set(@ModelAttribute Car car, RestTemplate restTemplate) {
        boolean newCar = true;
        if (newCar) {
            //if (car.isNew()) {
            restTemplate.postForObject("http://localhost:8080/cardetails/", car, Car.class);
            carrepos.addCar(car);
        }
        else {
            restTemplate.put("http://localhost:8080/cardetails/" + car.getBrand(), car, Car.class);
            //carrepos.EditCar(car);
        }
        return "redirect:/";
    }
}
