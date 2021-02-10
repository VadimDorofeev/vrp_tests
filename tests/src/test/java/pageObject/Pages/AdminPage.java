package pageObject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AdminPage {

    private By name = By.xpath("//input[@name='username']");
    private By password = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[@name='login']");

    WebDriver driver = new FirefoxDriver();

    public AdminPage(WebDriver driver){
        this.driver = driver;
    }

    public HomePage login() {
        driver.findElement(name).sendKeys("admin");
        driver.findElement(password).sendKeys("admin");
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }
}
