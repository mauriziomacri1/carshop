package com.example.CarShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Controller


public class CarController {
    CarRepository carrepos = new CarRepository();
@Autowired
DataSource dataSource;

    @GetMapping("/")
    public String homePage() {
        return "start";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("search", carrepos.getCars());
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
        int id = -1;
        model.addAttribute("id", id);
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

    @PostMapping("/save/{id}")
    public String set(@ModelAttribute Car car, @PathVariable int id) {
        if (id < 0)
            carrepos.addCar(car);

        else {
            carrepos.replace(id, car);
        }
        return "redirect:/";
    }
}
