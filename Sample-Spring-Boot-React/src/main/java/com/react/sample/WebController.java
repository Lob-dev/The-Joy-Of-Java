package com.react.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @GetMapping("/greeting")
    @ResponseBody
    public String greeting() {
        return "Greeting Sample";
    }

    @GetMapping("/error")
    public String routeError() {
        return "/index.html";
    }

    @ExceptionHandler(value = RuntimeException.class)
    public String getErrorPath(RuntimeException exception) {
        return "/error";
    }
}
