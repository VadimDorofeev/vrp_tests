import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewPagesTest {

    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    void init() {
        System.setProperty("webdriver.geco.driver", "gecodriver.exe");
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void test() {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();

        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(By.xpath("//i[@class='fa fa-pencil']")).click();

        List<WebElement> links = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
        for (WebElement element: links) {

            element.click();

            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            driver.close();
            driver.switchTo().window(winHandleBefore);
        }
    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
