package com.teamf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/results")
    public String results() {
        return "results";
    }

    @GetMapping("/book")
    public String book() {
        return "book";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }
}
