import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class RegistrationNewUser {

    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    void init() {
        System.setProperty("webdriver.geco.driver", "gecodriver.exe");
        driver.get("http://localhost/litecart/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void test() {
        String password = "123456";
        String timestamp = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());

        driver.findElement(By.xpath("//div[@class='content']//a[contains(@href, 'create_account')]")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("first name");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("last name");
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("address");
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("111111");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("city");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(timestamp + "@mail.ru");
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("950 777 77 77");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='create_account']")).click();

        driver.findElement(By.xpath("//div[@class='content']//a[contains(@href,'logout')]")).click();

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(timestamp + "@mail.ru");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();

    }

    @AfterTest
    void stop() {
        driver.quit();
    }
}
