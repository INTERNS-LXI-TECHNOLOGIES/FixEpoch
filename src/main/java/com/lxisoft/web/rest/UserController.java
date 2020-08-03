package com.lxisoft.web.rest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }
    @GetMapping(value = "/contact1")
    public String contact1(){
        return "contact1";
    }

    @GetMapping(value = "/barbour-shop-firms")
    public String barbourShopFirms(){
        return "barbour-shop-firms";
    }

    @GetMapping(value = "/getFirmDetails")
    public String getFirmDetails(){
        return "BarberShop";
    }

    @GetMapping(value = "/hairStyle")
    public String hairStyle(){
        return "hairstyle";
    }

    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/shopindex")
    public String shopindex(){
        return "shopindex";
    }

    @GetMapping(value = "/about1")
    public String about1(){
        return "about1";
    }

    @GetMapping(value = "/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping(value = "/authentication")
    public Boolean authentication(){
       if(SecurityContextHolder.getContext() == null){
           return true;
       }
       else {
           return false;
       }
    }

    @GetMapping(value = "/userAlreadyLoggedIn")
    public boolean userAlreadyLoggedIn(){
        return true;
    }

    @GetMapping(value = "/loginUser")
    public String loginUser(){
        return "redirect:/home";
    }

    @GetMapping(value = "/login")
    public Boolean login(){
        return false;
    }
}
