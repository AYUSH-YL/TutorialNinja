package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setup() {
        System.out.println("Starting the browser from Hooks");
        
        ChromeOptions options = new ChromeOptions();
        
        // 1. Stable container execution flags
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        
        // 2. CRUCIAL: Force Chrome to start in full desktop resolution
        // This prevents the website from collapsing into mobile layout
        options.addArguments("--window-size=1920,1080");
        
        // 3. Setup Grid URL integration
        String gridUrl = System.getenv("SELENIUM_HUB_URL");
        if (gridUrl == null || gridUrl.isEmpty()) {
            gridUrl = "http://localhost:4444";
        }
        
        System.out.println("Connecting to Selenium Grid at: " + gridUrl);
        
        try {
            driver = new RemoteWebDriver(new URL(gridUrl + "/wd/hub"), options);
        } catch (MalformedURLException e) {
            System.err.println("Invalid Selenium Grid URL: " + e.getMessage());
            throw new RuntimeException(e);
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
