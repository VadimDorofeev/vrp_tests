import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasketTest {

    WebDriver driver = new FirefoxDriver();
    Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

    @BeforeTest
    void setup() {
        System.setProperty("webdriver.geco.driver", "gecodriver.exe");
        driver.get("http://localhost/litecart");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void test() {

        String name = driver.findElement(By.xpath("//div[contains(text(),'Yellow Duck')]")).getText();
        String basketXpath = "//span[@class='quantity']";


        for (int i = 0; i <= 3; i++) {
            driver.findElement(By.xpath("//div[contains(text(),'" + name + "')]")).click();
            Select size = new Select(driver.findElement(By.xpath("//select[@name='options[Size]']")));
            size.selectByValue("Small");
            driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
            WebElement basket = driver.findElement(By.xpath(basketXpath));
            wait.until(ExpectedConditions.attributeToBe(basket, "textContent", String.valueOf(i)));
            driver.findElement(By.xpath("//i[@class='fa fa-home']")).click();
        }

        Assert.assertTrue(driver.findElement(By.xpath(basketXpath)).getAttribute("textContent").equals("3"));
    }

    @AfterTest
    void stop() {

    }
}
