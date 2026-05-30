package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.testng.asserts.SoftAssert; 

import POMforTutorial.POM_tutorialninja;

public class Tutorial {
    POM_tutorialninja pom;

    @Given("User is on the Tutorial Ninja application")
    public void tutorial_ninja() {
        Hooks.driver.get("https://tutorialsninja.com/demo/");
        pom = new POM_tutorialninja(Hooks.driver); 
    }

    @When("User clicks on My Account and selects Login")
    public void myaccount() {
        pom.Login();
    }
    
    @And("^User enters email \"(.*)\" and password \"(.*)\"$")
    public void email(String email, String password) {
        pom.Credentials(email, password);
    }

    @And("User clicks the Login button")
    public void userlogin() {
        pom.LoginButton();
    }

    @Then("User is logged in successfully")
    public void login() {
        SoftAssert softAssert = new SoftAssert();
        String currentTitle = Hooks.driver.getTitle();
        softAssert.assertTrue(currentTitle.contains("My Account"), "Login Failed: Not on the account page!");       
        softAssert.assertAll(); 
    }
    
    @When("^User searches for \"(.*)\" and adds it to the cart$")
    public void search(String product) throws InterruptedException {
        pom.search(product);
    }
    
    @And("User opens the cart and clicks View Cart")
    public void view_cart() {
        pom.cart();
    }

    @And("User removes the item from the cart")
    public void remove_item() throws InterruptedException {
        pom.remove();
    }

    @Then("User securely logs out")
    public void logout() throws InterruptedException {
        pom.Logout();
    }
}