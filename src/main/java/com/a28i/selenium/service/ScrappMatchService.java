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
public class ScrappMatchService {

    private final FirefoxDriver driver;

    @Autowired
    public ScrappMatchService(FirefoxDriver driver) {
        this.driver = driver;
    }

    public String scrapTableMatch(final String url) {
        String tbodies = "";
        try {
            driver.get(url);
            new WebDriverWait(driver, 50).until(ExpectedConditions
            .presenceOfElementLocated(By.xpath("//form[@id='login-form']//input[@type='submit']")));
            // new WebDriverWait(driver, 50).until(ExpectedConditions
            // .presenceOfElementLocated(By.xpath("//form[@id='login-form']//input[@type='submit']")));

            // open a28i homepage ,fill credential and login
            final WebElement form = driver.findElementById("login-form");
            // final WebElement button = driver.findElementByClassName("jss15");
            final WebElement usernameInput = form.findElement(By.id("username"));
            final WebElement passwordInput = form.findElement(By.id("password"));

            usernameInput.sendKeys("hgarun66001");
            passwordInput.sendKeys("abc123");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // button.click();
            form.submit();

            new WebDriverWait(driver, 50).until(ExpectedConditions.urlToBe("https://web.a28i.com/terms"));
            driver.get("https://web.a28i.com/odds");

            new WebDriverWait(driver, 50).until(ExpectedConditions.urlToBe("https://web.a28i.com/odds"));
            LocalTime changeMarketTime = LocalTime.of(11, 0);
            LocalTime now = LocalTime.now();
            System.out.println(now);
            if (now.isAfter(changeMarketTime)) {
                System.out.println("working here...Today Market");
                // wait for FootballEarly href to be clickable
                final WebElement footballToday = new WebDriverWait(driver, 50).until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='firstMenu']//li[1]//a")));
                footballToday.click();
                // driver.navigate().refresh();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // wait for parly href to be clickable
                final WebElement parleyWait = new WebDriverWait(driver, 20).until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='secondMenu']//li[2]//a")));

                parleyWait.click();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("working here...Early Market");
                // wait for FootballEarly href to be clickable
                final WebElement footballEarly = new WebDriverWait(driver, 50).until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='firstMenu']//li[2]//a")));
                footballEarly.click();
                // driver.navigate().refresh();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // wait for parly href to be clickable
                final WebElement parleyWait = new WebDriverWait(driver, 50).until(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='secondMenu']//li[2]//a")));

                parleyWait.click();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            WebElement secondTableDiv = new WebDriverWait(driver, 50)
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.SecondTableGroup")));
            new WebDriverWait(driver, 50)
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table.oddsTable")));
            WebElement tableContents = secondTableDiv.findElement(By.cssSelector("table.oddsTable"));
            // System.out.println(tableContents.getAttribute("innterHTML"));
            tbodies = tableContents.getAttribute("innerHTML");
            // return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tbodies != null)
            System.out.println("Scrap Succeed!");
        return tbodies;
    }

}
