package POMforTutorial;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POM_tutorialninja {

    WebDriver driver;
    WebDriverWait wait;
    // Fixed: Target the dropdown-toggle link that contains My Account
    By myAccountMenu = By.xpath("//li[@class='dropdown']/a[contains(@class, 'dropdown-toggle') and contains(., 'My Account')]");
    By loginDropdownItem = By.linkText("Login");
    By emailField = By.id("input-email");
    By passwordField = By.id("input-password");
    By loginBtn = By.xpath("//input[@value='Login']");
    
    By searchBox = By.name("search");
    // Fixed: Use first button with cart.add onclick
    By addToCartBtn = By.xpath("//button[contains(@onclick, 'cart.add')]");
    
    By mainCartBtn = By.id("cart-total");
    // Fixed: Target the link with View Cart text
    By viewCartLink = By.xpath("//a[contains(@href, 'checkout/cart')]");
    By removeBtn = By.xpath("//button[@data-original-title='Remove']");
    
    // Fixed: Target logout link by text
    By logoutDropdownItem = By.xpath("//a[contains(text(), 'Logout')]");

    public POM_tutorialninja(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void Login() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginDropdownItem)).click();
    }

    public void Credentials(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void LoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public void search(String product) throws InterruptedException {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(product);
        search.sendKeys(Keys.ENTER);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);"); 
        Thread.sleep(500); 
        
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        Thread.sleep(2000); 
    }

    public void cart() {
        wait.until(ExpectedConditions.elementToBeClickable(mainCartBtn)).click();
        // Wait for dropdown to appear and click the cart link
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(@href, 'checkout/cart')]")
        ));
        cartLink.click();
    }

    public void remove() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
        Thread.sleep(2000); 
    }

    public void Logout() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutDropdownItem)).click();
    }
}
