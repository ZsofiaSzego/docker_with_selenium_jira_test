package util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class UtilDriver {
        private WebDriver driver;
        private String baseUrl;

        public UtilDriver() {
            String Node = "https://selenium:validpassword.@seleniumhub.codecool.metastage.net/wd/hub/";
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName("firefox");
            cap.setPlatform(Platform.LINUX);
            try {
                driver = new RemoteWebDriver(new URL(Node), cap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            this.baseUrl = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
            driver.get(baseUrl);
            new WebDriverWait(driver, 40).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);
        }

        public WebDriver getDriver() {
            return driver;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void close(){
            driver.quit();
        }

        public void navigationToCertainProject (String name)  {
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 10000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='header-details-user-fullname']/span/span/img"))));
            driver.get("https://jira-auto.codecool.metastage.net/projects/" + name + "/summary");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        public void navigationToCertainProjectGlassProject (String key) {
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 10000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='header-details-user-fullname']/span/span/img"))));
            driver.get("https://jira-auto.codecool.metastage.net/projects/" + key + "?selectedItem=com.codecanvas.glass:glass");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

    public void navigationToCertainProjectReleasePage (String key) {
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='header-details-user-fullname']/span/span/img"))));
        driver.get("https://jira-auto.codecool.metastage.net/projects/" + key + "?selectedItem=com.atlassian.jira.jira-projects-plugin%3Arelease-page&status=no-filter");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
