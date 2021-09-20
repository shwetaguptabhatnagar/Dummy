package com.qa.factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
	
	
	public WebDriver init_driver(String browser)
	{
		DesiredCapabilities cap=new DesiredCapabilities();
		System.out.print("Browser value:"+browser);
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
		//	tlDriver.set(new ChromeDriver()); // It will create thread local driver
			
			cap.setCapability("browserName","chrome");
			try {
				tlDriver.set(new RemoteWebDriver(new URL("http://15.206.92.185:4444/wd/hub"),cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//tlDriver.set(new FirefoxDriver()); // It will create thread local driver
			
			cap.setCapability("browserName","firefox");
			try {
				tlDriver.set(new RemoteWebDriver(new URL("http://15.206.92.185:4444/wd/hub"),cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(browser.equals("safari"))
		{
			tlDriver.set(new SafariDriver()); // It will create thread local driver
		}
		else
		{
			System.out.print("Please pass correct browser!");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() // In  parallel execution, when multiple threads are accessing this.so it should be synchronised
	{
		return tlDriver.get();
	}
}
