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

public class CountriesTest {

    WebDriver driver = new FirefoxDriver();

    @BeforeTest
    void init() {
        System.setProperty("webdriver.geco.driver", "gecodriver.exe");
        driver.get("http://localhost/litecart/admin");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void test() {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name='login']")).click();
        driver.findElement(By.xpath("//a[contains(@href,'countries')]")).click();

        List<WebElement> countries = driver.findElements(By.className("row"));
        List<String> namesOfTheCountries = new ArrayList<>();

        for (WebElement country : countries) {
            String nameOfTheCountry = country.findElement(By.xpath("./*[5]//a")).getAttribute("text");
            namesOfTheCountries.add(nameOfTheCountry);
            String zones = country.findElement(By.xpath("./td[6]")).getText();

            if (!zones.equals("0")) {

                country.findElement(By.xpath("./*[5]//a")).click();
                List <WebElement> zonesList = driver.findElements(By.xpath("//input[contains(@type,'hidden') and contains(@name,'name')]"));
                List <String> namesOfTheZones = new ArrayList<>();

                for (WebElement zone : zonesList) {
                    String nameOfTheZone = zone.findElement(By.xpath("//input[contains(@type,'hidden') and contains(@name,'name')]")).
                            getAttribute("innerText");
                    namesOfTheZones.add(nameOfTheZone);
                }
                List <String> sortedNamesOfTheZones = namesOfTheZones.stream().sorted().collect(Collectors.toList());

                for (int i = 0; i < namesOfTheZones.size(); i++) {
                    Assert.assertTrue(sortedNamesOfTheZones.get(i).equals(namesOfTheZones.get(i)));
                }

                driver.findElement(By.xpath("//button[@name='cancel']")).click();

                for (String printZones: namesOfTheZones) {
                    System.out.println(printZones);
                }

                System.out.println(namesOfTheZones.size());
                System.out.println(sortedNamesOfTheZones.size());
            }
        }

        List<String> sortedNamesOfElements = namesOfTheCountries.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < sortedNamesOfElements.size(); i++) {
            Assert.assertTrue(sortedNamesOfElements.get(i).equals(namesOfTheCountries.get(i)));
        }

        for (String print : namesOfTheCountries) {
            System.out.println(print);
        }

        System.out.println(namesOfTheCountries.size());
        System.out.println(sortedNamesOfElements.size());
    }

    @AfterTest
    void after() {
        driver.quit();
    }
}
