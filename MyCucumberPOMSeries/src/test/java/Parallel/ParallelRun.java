package Parallel;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/"
				}, 
		monochrome = true,
		glue = { "Parallel" },
		features = { "src/test/resources/Parallel" }
)

public class ParallelRun extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {  
		// way to run parallel mode in  TestNG
		//all the scenarios will be executed in parallel mode with the help of data provider
		// According to the documentation, same package name for step & feature file should be there
		return super.scenarios();
	}
}