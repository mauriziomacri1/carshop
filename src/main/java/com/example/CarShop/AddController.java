package com.example.CarShop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AddController {
    CarRepository carrepos = new CarRepository();

    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "AddCarForm";
    }

    @PostMapping("/save")
    public String set(@ModelAttribute Car car, RestTemplate restTemplate) {
        /*if (car.isNew()) */{
            restTemplate.postForObject("http://localhost:8080/cardetails/", car, Car.class);
            carrepos.addCar(car);
        }
        /*else */{
            restTemplate.put("http://localhost:8080/cardetails/" + car.getBrand(), car, Car.class);
            //carrepos.EditCar(car);
        }
        return "redirect:/";
    }
}
