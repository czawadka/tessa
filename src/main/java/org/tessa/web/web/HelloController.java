package org.tessa.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    @Layout(value = "none")
    public String login() {
        return "login";
    }
}
