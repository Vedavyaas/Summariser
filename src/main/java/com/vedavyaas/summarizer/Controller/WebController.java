package com.vedavyaas.summarizer.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/input")
    public String input(){
        return "input";
    }
}
