package StepDefinition;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features", 
    glue = {"StepDefinition"},
    tags = "@Smoke",
    plugin = {
        "pretty", 
        "html:target/htmlreports/report.html" 
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // No code needed inside the block; the extended class handles execution
}