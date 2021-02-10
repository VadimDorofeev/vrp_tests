package pageObject.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.List;

public class ProductPage {

    WebDriver driver = new FirefoxDriver();

    private By addNewProductButton = By.xpath("//a[contains(@href,'edit_product')]");
    private By image = By.xpath("//input[@name='new_images[]']");
    private By addButton = By.xpath("//button[@name='save']");
    private By products = By.xpath("");
    private By productName = By.xpath("//input[@name='name[en]']");

    String timestamp = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
    String productNameString = "test product " + timestamp;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addNewProduct() {
        driver.findElement(addNewProductButton).click();
        driver.findElement(productName).sendKeys(productNameString);
        driver.findElement(image).sendKeys("C:\\Users\\Vdorofeev\\Desktop\\vrp_tests\\vrp_tests\\tests\\img.jpg");
        driver.findElement(addButton).click();
    }

//    public List<String> getProducts() {
//        driver.findElements()
//    }
}
