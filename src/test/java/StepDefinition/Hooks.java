package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setup() {
        System.out.println("Starting the browser from Hooks");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void teardown() throws InterruptedException {
        System.out.println("Closing the browser from Hooks");
        Thread.sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }
}