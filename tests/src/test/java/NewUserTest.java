import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class NewUserTest {

    WebDriver driver = new FirefoxDriver();
    @BeforeTest
    void init() {
        System.setProperty("webdriver.geco.driver", "gecodriver.exe");
        driver.get("http://localhost/litecart/admin");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void test() {
        String name = "test product";

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        driver.findElement(By.xpath("//a[contains(@href,'catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(@href,'edit_product')]")).click();
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys("test product name");

        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys("img_forest.jpg");
        driver.findElement(By.xpath("//button[@name='save']")).click();

        Assert.assertTrue(driver.findElements(By.xpath("//*[text()='" + name + "']")).size() > 0);

    }


    @AfterTest
    void stop() {
        driver.quit();
    }
}
