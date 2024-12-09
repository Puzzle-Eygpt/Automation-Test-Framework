package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"StepDefinitions","hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@smoke"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}