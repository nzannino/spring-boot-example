package com.nzannino.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(@RequestParam(value="name", defaultValue="World") String name, Model model){
        model.addAttribute("name",name);
        return "home";
    }

}