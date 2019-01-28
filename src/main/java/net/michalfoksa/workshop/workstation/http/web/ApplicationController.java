package net.michalfoksa.workshop.workstation.http.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.michalfoksa.workshop.workstation.context.RuntimeContext;

@Controller
@RequestMapping(path="/")
public class ApplicationController {

    @Inject
    private RuntimeContext runtimeContext;

    @GetMapping()
    public @ResponseBody String getApplicationInfo() {
        return runtimeContext.getAllFieldsMap().toString();
    }

}
