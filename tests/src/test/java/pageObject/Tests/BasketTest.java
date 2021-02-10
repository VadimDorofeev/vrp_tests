package pageObject.Tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.Pages.AdminPage;
import pageObject.Pages.HomePage;
import pageObject.Pages.ProductPage;

import java.util.concurrent.TimeUnit;

public class BasketTest {

    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    void start(){
        System.setProperty("webdriver.geco.driver", "gecodriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/login");
    }

    @Test
    void basketTest() {
        AdminPage adminPage = new AdminPage(driver);
        HomePage homePage = adminPage.login();
        ProductPage productsPage = homePage.openProductsPage();
        productsPage.addNewProduct();
        Assert.assertTrue(productsPage.getProducts().contains(""));
    }
    @AfterTest
    void stop() {

    }
}
