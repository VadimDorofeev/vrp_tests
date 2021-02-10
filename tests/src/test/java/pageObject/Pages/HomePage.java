package pageObject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePage {

    WebDriver driver = new FirefoxDriver();

    private By productPage = By.xpath("//a[contains(@href,'catalog')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPage openProductsPage() {
        driver.findElement(productPage).click();
        return new ProductPage(driver);
    }
}
