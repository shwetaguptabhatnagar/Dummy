package Parallel;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configreader;
	Properties prop;
	
	@Before(order=0)
	public void getProperty()
	{
		configreader=new ConfigReader(); 
		prop=configreader.init_prop();
	}
	
	@Before(order=1)
	public void launchBrowser()
	{
		String browsername=prop.getProperty("browser");
		driverFactory=new DriverFactory();
		driver=driverFactory.init_driver(browsername);
	}
	
	@After(order=0) // It will be executed last
	public void quitBrowser()
	{
		driver.quit();
	}
	@After(order=1) // It will be executed First
	public void tearDown(Scenario sc)
	{
		if(sc.isFailed())
		{//take screenshot
		String screenshotname=sc.getName().replaceAll(" ", "_");
		byte[] sourcePath=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		sc.attach(sourcePath, "image/png", screenshotname);
		}
	}
}
