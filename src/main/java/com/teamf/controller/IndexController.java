package com.teamf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for simple route mappings to pages not requiring logic.
 */
@Controller
public class IndexController {

    /**
     * Direct route to the results page (not used in production).
     */
    @GetMapping("/results")
    public String results() {
        return "results";
    }

    /**
     * Direct route to booking page (not used in production).
     */
    @GetMapping("/book")
    public String book() {
        return "book";
    }

    /**
     * Direct route to confirmation page (used after booking).
     */
    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }
}
