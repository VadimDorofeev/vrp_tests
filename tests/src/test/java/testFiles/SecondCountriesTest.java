package testFiles;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SecondCountriesTest {

    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    void init(){
        System.setProperty("webdriver.geco.driver", "gecodriver.exe");
        driver.get("http://localhost/litecart/admin");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void test() {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'geo_zones')]")).click();

        List<WebElement> countries = driver.findElements(By.xpath("//tr[@class='row']"));

        for (int i = 0; i < countries.size(); i++) {
            countries.get(i).findElement(By.xpath("//tr[@class='row']//*[3]//a")).click();
            List <WebElement> subCountries = driver.findElements(By.xpath("//select[contains(@name,'zone_code')]"));
            List<String> namesOfZones = new ArrayList<>();
            for (int j = 0; j < subCountries.size(); j++) {
                String nameOfTheZone = subCountries.get(j).
                        findElement(By.xpath("//select[contains(@name,'zone_code')]//option[@selected='selected']")).getText();
                namesOfZones.add(nameOfTheZone);
            }

            List<String> sortedNamesOfZones = namesOfZones.stream().sorted().collect(Collectors.toList());

            for (int j = 0; j < namesOfZones.size(); j++) {
                Assert.assertTrue(namesOfZones.get(i).equals(sortedNamesOfZones.get(i)));
            }

            driver.findElement(By.xpath("//button[@name='cancel']")).click();
            countries = driver.findElements(By.xpath("//tr[@class='row']"));
        }
    }

    @AfterTest
    void stop() {
        driver.quit();
    }
}
