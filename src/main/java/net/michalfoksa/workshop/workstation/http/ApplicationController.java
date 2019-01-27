package net.michalfoksa.workshop.workstation.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/")
public class ApplicationController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public @ResponseBody String newWork () {

        return "Application: " + appName;
    }

}