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
        return "start";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("search", carrepos.carList);
        return "search";
        }

        @GetMapping("/carlist")
    public String carlist(Model model) {
        model.addAttribute("cars", carrepos.getCars());//carlist
        return "index";
    }

    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "AddCarForm";
    }

    @GetMapping("/cardetails/{id}")
    public String carDetails(Model model, @PathVariable int id) {
        Car car = carrepos.getCar(id);
        model.addAttribute("car", car);
        model.addAttribute("carindex", id);
        return "cardetails";
    }

    @GetMapping("/editcar/{id}")
    public String caredit(Model model, @PathVariable int id) {
        Car car = carrepos.getCar(id);
        model.addAttribute("car", car);
        return "AddCarForm";
    }
    @GetMapping("/buycar/{id}")
    public String buyCar(Model model, @PathVariable int id) {
        Car car = carrepos.getCar(id);
        model.addAttribute("car", car);
        model.addAttribute("id", id);
        return "buycar";
    }
    @PostMapping("/finalizepurchase/{id}")
    public String finalizePurchase(@PathVariable int id) {
        Car car = carrepos.getCar(id);
        car.setSold(true);
        System.out.println("Car sold!" + car.getSlug());
        return "redirect:/carlist";
    }

    @PostMapping("/save")
    public String set(@ModelAttribute Car car, RestTemplate restTemplate) {
        if (true)
            carrepos.addCar(car);

        else {
            //carrepos.EditCar(car);
        }
        return "redirect:/";
    }



}
