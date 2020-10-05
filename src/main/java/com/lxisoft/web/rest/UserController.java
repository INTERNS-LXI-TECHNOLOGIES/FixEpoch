package com.lxisoft.web.rest;


import com.lxisoft.config.ImageUtil;
import com.lxisoft.domain.*;
import com.lxisoft.model.AppointmentModel;
import com.lxisoft.model.RegistrationModel;
import com.lxisoft.service.dto.*;
import com.lxisoft.service.impl.*;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private FirmServiceImpl firmService;

    @Autowired
    private ProvidedServiceServiceImpl providedServiceService;

    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private StateServiceImpl stateService;

    @Autowired
    private  PostelCodeServiceImpl postelCodeService;


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

    @GetMapping(value = "/getFirmDetails/{id}")
    public ModelAndView getFirmDetails(ModelAndView modelAndView,@PathVariable("id") Long id) {
        AppointmentModel appointmentModel = new AppointmentModel();
        FirmDTO firmDTO = firmService.findOne(id).get();
        List<ProvidedService> providedServices = providedServiceService.findAllByFirmId(id);
        List<Employee> employees =  employeeService.findAllEmployeeByFirmId(id);
        Set<TimeSlot> timeSlotSet = firmService.findAllTimeSlotsByFirmId(id);
        CustomerDTO customerDTO = customerService.findOne(firmDTO.getCustomerId()).get();
        AddressDTO addressDTO = addressService.findOne(firmDTO.getAddressId()).get();
        modelAndView.addObject("appointment", appointmentModel);
        modelAndView.addObject("timeSlotSet",timeSlotSet);
        modelAndView.addObject("employee",employees);
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

    @GetMapping(value = "/firm")
    public String firm(){
        return "firm";
    }

    @GetMapping(value = "/firmAdmin")
    public String firmAdmin(){
        return "firmAdmin";
    }

    @GetMapping(value = "/register")
    public String register(){
        return "register";
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


    @GetMapping(value="/registerFirm")
    public ModelAndView registerFirm()
    {
        RegistrationModel registrationModel = new RegistrationModel();
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categoryList = categoryService.findAllCategory();
//        for (Category category:categoryList) {
//            System.out.println(category);
//        }
        modelAndView.addObject("registrationModel",registrationModel);
        modelAndView.addObject("categories",categoryList);
        modelAndView.setViewName("firmRegister");
        return modelAndView;
    }

    @PostMapping(value = "/firmRegister")
    public ModelAndView register(@ModelAttribute("registrationModel") RegistrationModel regModel,
                                 @RequestParam(name = "firmImage") MultipartFile file,
                                 RedirectAttributes redirect)
    {
        ModelAndView modelAndView = new ModelAndView();

        Firm firm  = new Firm();
        firm.setName(regModel.getFirmName());

        if(file.isEmpty())
        {
            redirect.addFlashAttribute("message","Select A File To Upload");
        }
        try
        {
            byte[] bytes = file.getBytes();
            firm.setImage(bytes);
            firm.setImageContentType(file.getContentType());
            System.out.println(file.getBytes()+""+file.getContentType());
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Address address = new Address();
        address.setLocationAddressLineOne(regModel.getAddress().getLocationAddressLineOne());
        address.setLocationAddressLineTwo(regModel.getAddress().getLocationAddressLineTwo());

        City city = new City();
        city.setDistrict(regModel.getCity().getDistrict());

        State state = new State();
        state.setState(regModel.getState().getState());

        PostelCode postelCode = new PostelCode();
        postelCode.setPostelCode(regModel.getPin().getPostelCode());


        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postelCode);



        Category category = new Category();
        category = regModel.getCategory();



        Customer customer = new Customer();
        customer = customerService.getCustomer(11l);

        cityService.saveCity(city);
        stateService.saveState(state);
        postelCodeService.savePostelCode(postelCode);
        addressService.saveAddress(address);
        categoryService.saveCategory(category);
        firm.setCategory(category);
        firm.setAddress(address);
        firm.setCustomer(customer);
        firmService.saveFirm(firm);
        return modelAndView;
    }

    @GetMapping(value = "/test")
    public String testTemplate(){
        return "TestTemplate";
    }

    @GetMapping(value = "/makeAnAppointment")
    public ModelAndView  makeAnAppointment(@ModelAttribute("appointment") AppointmentModel appointmentModel){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("appointment",appointmentModel);
        modelAndView.setViewName("TestTemplate");
        return modelAndView;
    }


}
