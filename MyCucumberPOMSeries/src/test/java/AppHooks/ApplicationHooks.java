package AppHooks;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.PropertiesReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configreader;
	Properties prop;

	@Before(order = 0)
	public void getProperty() {
		configreader = new ConfigReader();
		prop = configreader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {

		/*
		 * java.io.InputStream is =
		 * this.getClass().getResourceAsStream("my.properties"); java.util.Properties p
		 * = new Properties(); try { p.load(is); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } String browsername =
		 * p.getProperty("browser");
		 * System.out.print("browsername in Hooks:"+browsername);
		 */

		// String browsername=System.getProperty("browser");

		/*
		 * PropertiesReader reader = null; try { reader = new
		 * PropertiesReader("properties-from-pom.properties"); } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		
		// String browsername = reader.getProperty("newbrowser");
		 //System.out.print("browsername in Hooks:"+browsername);
		 

		//String browsername = prop.getProperty("browser");
		//System.out.print("browsername in Hooks2:" + browsername);
		 
		 String browsername=System.getProperty("browser"); 
		 System.out.print("browsername in Hooks:"+browsername);
		 
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browsername);
	}

	@After(order = 0) // It will be executed last
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1) // It will be executed First
	public void tearDown(Scenario sc) {
		if (sc.isFailed()) {// take screenshot
			String screenshotname = sc.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			sc.attach(sourcePath, "image/png", screenshotname);
		}
	}
}
