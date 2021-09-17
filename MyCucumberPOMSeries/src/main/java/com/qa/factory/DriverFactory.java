package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
	
	
	public WebDriver init_driver(String browser)
	{
		System.out.print("Browser value:"+browser);
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver()); // It will create thread local driver
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver()); // It will create thread local driver
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
