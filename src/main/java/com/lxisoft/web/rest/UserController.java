package com.lxisoft.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = "/getFirmDetails")
    public String getFirmDetails(){
        return "BarberShop";
    }

    @GetMapping(value = "/hairStyle")
    public String hairStyle(){
        return "hairstyle";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }

    @GetMapping(value = "/contact")
    public String contact(){
        return "contact";
    }
}
