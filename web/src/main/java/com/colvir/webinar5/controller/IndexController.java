package com.colvir.webinar5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/page")
    public String page(Model model) {
        model.addAttribute("title", "Здесь могла быть Ваша реклама");
        return "page";
    }
}
