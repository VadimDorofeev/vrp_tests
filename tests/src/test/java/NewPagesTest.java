import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NewPagesTest {

    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    void init() {
        System.setProperty("webdriver.geco.driver", "gecodriver.exe");
        driver.get("http://localhost/litecart/admin");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void test() {

    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
