package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.ietf.jgss.Oid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.mystore.actiondriver.Action;
//import com.mystore.utility.ExtentManager;
import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * @author Hitendra: BaseClass is used to load the config file and Initialize 
 * WebDriver
 *  
 */
public class BaseClass {
	public static Properties prop;

	// Declare ThreadLocal Driver
	
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
//	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		return driver.get();
	}

	//loadConfig method is to load the configuration
	@BeforeSuite(groups = {"Smoke","Sanity","Regression"})
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void launchApp(String browserName) {
//		 String browserName = prop.getProperty("browser");
		 Integer timeout = Integer.valueOf(prop.getProperty("timeout"));
		 Integer long_timeout = Integer.valueOf(prop.getProperty("longtimeout"));
		 
		 switch (browserName.toLowerCase().trim()) {
		    case "chrome":
		    	WebDriverManager.chromedriver().setup();
		    	driver.set(new ChromeDriver());
//		    	driver = new ChromeDriver();
		        break;

		    case "firefox":
		        WebDriverManager.firefoxdriver().setup();
		        driver.set(new FirefoxDriver());
//		        driver = new FirefoxDriver();
		        break;

		    case "edge":
		        WebDriverManager.edgedriver().setup();
		        driver.set(new EdgeDriver());
//		        driver = new InternetExplorerDriver();
		        break;

		    default:
		        throw new IllegalArgumentException("Invalid browser name: " + browserName);
		}
		 
		 getDriver().get(prop.getProperty("url"));
		 getDriver().manage().window().maximize();
		 getDriver().manage().deleteAllCookies();
		Action.implicitWait(getDriver(), timeout);
		Action.pageLoadTimeOut(getDriver(), long_timeout);
	}

	@AfterSuite(groups = { "Smoke", "Regression","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
}