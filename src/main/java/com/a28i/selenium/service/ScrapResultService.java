package com.a28i.selenium.service;

import java.time.LocalTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrapResultService {
    private final FirefoxDriver driver;

    @Autowired
    public ScrapResultService(FirefoxDriver driver) {
        this.driver = driver;
    }

    public String scrapTableResult(final String url) {
        driver.get(url);

        // open a28i homepage ,fill credential and login
        final WebElement form = driver.findElementById("login-form");
        final WebElement button = driver.findElementByClassName("jss15");
        final WebElement usernameInput = form.findElement(By.id("username"));
        final WebElement passwordInput = form.findElement(By.id("password"));

        usernameInput.sendKeys("hgarun66002");
        passwordInput.sendKeys("123abc");

        button.click();
        // open odds page and click Football(Early)
        new WebDriverWait(driver, 50).until(ExpectedConditions.urlToBe("https://web.a28i.com/terms"));
        driver.get("https://web.a28i.com/results");

        LocalTime changeMarketTime = LocalTime.of(14, 0);
        LocalTime now = LocalTime.now();

        // wait 50secs for btn yesterday load
        WebElement formResult = new WebDriverWait(driver, 100)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form#form")));
        if (now.isBefore(changeMarketTime)) {
            WebElement yesterdayBtn = formResult.findElement(By.xpath("//form[@id='form']//button[2]"));
            yesterdayBtn.click();
        } else {
            WebElement todayBtn = formResult.findElement(By.xpath("//form[@id='form']//button[1]"));
            todayBtn.click();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement tableContent = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
        System.out.println(tableContent.getAttribute("innerHTML"));

        return tableContent.getAttribute("innerHTML");

    }

}
