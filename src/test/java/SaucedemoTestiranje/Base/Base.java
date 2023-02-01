package SaucedemoTestiranje.Base;

import SaucedemoTestiranje.Page.CartPage;
import SaucedemoTestiranje.Page.LogInPage;
import SaucedemoTestiranje.Page.InventoryPage;
import common.excel.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class Base {
    public WebDriver driver;
    public WebDriverWait wdwait;
    public ExcelReader excelReader;
    public String homeUrl;
    public LogInPage logInPage;
    public InventoryPage inventoryPage;

    public CartPage cartPage;


    @BeforeClass
    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup();
        excelReader = new ExcelReader("src/test/java/SaucedemoTestiranje/Data.xlsx");
        homeUrl = excelReader.getStringData("URL", 1, 0);
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        logInPage = new LogInPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }

    public boolean isDisplayed(WebElement element){
        boolean webelement = false;
        try {
            webelement = element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webelement;
    }

    public void waitForVisibility(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }


}