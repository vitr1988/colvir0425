package com.colvir.webinar5.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

//    private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/page")
    public String page(Model model) {
        model.addAttribute("title", "Здесь могла быть Ваша реклама");
        return "page";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    @SneakyThrows(ServletException.class)
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//        this.logoutHandler.logout(request, response, authentication);
        request.getSession().invalidate();
        request.logout();
        return "redirect:/";
    }
}
