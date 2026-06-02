package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.URL;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setup() {
        System.out.println("Starting the browser from Hooks");
        
        try {
            // Get Selenium Grid URL from environment or use default
            String gridUrl = System.getenv("SELENIUM_HUB_URL");
            if (gridUrl == null || gridUrl.isEmpty()) {
                gridUrl = "http://localhost:4444";
            }
            
            System.out.println("Connecting to Selenium Grid at: " + gridUrl);
            
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            
            driver = new RemoteWebDriver(new URL(gridUrl + "/wd/hub"), options);
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.err.println("Failed to initialize RemoteWebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Could not start Selenium WebDriver", e);
        }
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
