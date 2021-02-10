package testFiles;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class StickerTest {
    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    void init() {
        System.setProperty("webdriver.geco.driver","gecodriver.exe");
        driver.get("http://localhost/litecart/");
    }

    @Test
    void test() {
        driver.findElement(By.className("category-1")).click();
        List<WebElement> goods = driver.findElements(By.className("product column shadow hover-light"));
        for (WebElement oneGood: goods) {
            String sticker = oneGood.findElement(By.xpath("./contains([@class,'sticker'")).getText();
            Assert.assertTrue(sticker.equals("New") || sticker.equals("Sale"));
        }
    }

    @AfterTest
    void stop() {
        driver.quit();
    }
}
