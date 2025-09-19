package com.vedavyaas.summarizer.Controller;

import com.vedavyaas.summarizer.Service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@CrossOrigin("*")
public class AIController {

    @Autowired
    private AIService aiService;

    @GetMapping("/control/api")
    public String api(@RequestParam String messages){
        return aiService.getGroq(messages);
    }
}
