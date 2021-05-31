package com.a28i.selenium.controller;

import java.util.HashMap;
import java.util.Map;
import com.a28i.selenium.service.ScrapResultService;
import com.a28i.selenium.service.ScrappMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ScrapController {

    private final String url = "https://web.a28i.com/";

    private ScrappMatchService scrapMatchService;
    private ScrapResultService scrapResultService;

    @Autowired
    public ScrapController(ScrappMatchService scrapMatchService, ScrapResultService scrapResultService) {
        this.scrapMatchService = scrapMatchService;
        this.scrapResultService = scrapResultService;
    }

    @GetMapping("/")
    public String viewIndex() {
        return "Hello";
    }

    @GetMapping("/result")
    public Map<String, String> viewTableResultPage() {
        Map<String, String> result = new HashMap<>();
        result.put("results", scrapResultService.scrapTableResult(url));

        return result;
    }

    @GetMapping("/match")
    public Map<String, String> viewTableMatch() {
        Map<String, String> match = new HashMap<>();
        match.put("matchs", scrapMatchService.scrapTableMatch(url));

        return match;
    }

}
