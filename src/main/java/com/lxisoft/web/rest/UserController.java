package com.lxisoft.web.rest;


import com.lxisoft.config.ImageUtil;
import com.lxisoft.domain.*;
import com.lxisoft.service.dto.*;
import com.lxisoft.service.impl.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    FirmServiceImpl firmService;

    @Autowired
    ProvidedServiceServiceImpl providedServiceService;

    @Autowired
    AddressServiceImpl addressService;

    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping(value = "/home")
    public ModelAndView home()
    {
        ModelAndView modelAndView = new ModelAndView();
        Optional<CategoryDTO>  categoryDTO = categoryService.findOne((long)11);
        CategoryDTO categoryDTO1 = categoryDTO.get();
        modelAndView.addObject("categoryDetail",categoryDTO1);
        modelAndView.addObject("imgUtil",new ImageUtil());
        modelAndView.setViewName("home");
        return modelAndView;
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
    public ModelAndView getFirmDetails(ModelAndView modelAndView) {
        FirmDTO firmDTO = firmService.findOne(43l).get();
        List<ProvidedService> providedServices = providedServiceService.findAllByFirmId(43l);
        CustomerDTO customerDTO = customerService.findOne(firmDTO.getCustomerId()).get();
        AddressDTO addressDTO = addressService.findOne(firmDTO.getAddressId()).get();
        modelAndView.addObject("customer",customerDTO);
        modelAndView.addObject("address",addressDTO);
        modelAndView.addObject("providedServices",providedServices);
        modelAndView.addObject("firm_Detail",firmDTO);
        modelAndView.addObject("imgUtil",new ImageUtil());
        modelAndView.setViewName("BarberShop");
        return modelAndView;
    }

    @GetMapping(value = "/hairStyle")
    public String hairStyle(){
        return "hairstyle";
    }

    @GetMapping(value = "/index/{id}")
    public ModelAndView index(@PathVariable("id") Long id){
        List<Firm> firmList = firmService.findFirmByCategory(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("firms",firmList);
        modelAndView.addObject("imgUtil",new ImageUtil());
        modelAndView.setViewName("index");
        return modelAndView;
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
    @ResponseBody
    public ResponseEntity<String> authentication(){
    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		boolean hasRole = authentication.getAuthorities().stream()
			          .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));

    if(hasRole) {
    	return new ResponseEntity<String>(HttpStatus.OK);
    }
       else {

           return new ResponseEntity<String>(HttpStatus.FORBIDDEN);

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

    @GetMapping(value = "/testLogin")
    public String testLogin(){
        return "BarberShop";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "BarberShop";
    }

    @GetMapping(value = "/getFirm/{id}")
    public ModelAndView getFirm(@PathVariable("id") Long id , ModelAndView modelAndView){
        Optional<FirmDTO> firmDTO = firmService.findOne(id);
        FirmDTO firmDTO1 = firmDTO.get();
        modelAndView.addObject("firm_Detail",firmDTO1);
        modelAndView.setViewName("BarberShop");
        return modelAndView;
    }

    @GetMapping(value = "/test")
    public String testTemplate(){
        return "TestTemplate";
    }

}
