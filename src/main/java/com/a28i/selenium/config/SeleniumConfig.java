package com.a28i.selenium.config;
import javax.annotation.PostConstruct;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {

    @Value("${webdriver.gecko.driver}")
    private String driverPath;
    
    @PostConstruct
    void postConstruct() {
        System.setProperty("webdriver.gecko.driver", driverPath);
    }

    @Bean
    public FirefoxDriver driver() {
        return new FirefoxDriver();
    }

}
