package com.senla.courses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class StartController {

    /*@Autowired
    UserService userService;*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testPage() {
        return "index";
    }

}
