package com.example.CarShop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
