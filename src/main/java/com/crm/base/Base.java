package com.crm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.util.WenEventListener;

import bsh.util.Util;


public class Base {
	public static WebDriver driver;
	public static Properties properties;	
	public static EventFiringWebDriver e_driver;
	public static WenEventListener eventListener;
	
	public Base() {
			
		try {
			properties = new Properties();
			FileInputStream fi = new FileInputStream("C:\\Users\\Gopi Srinivas Putta\\eclipse-workspace\\Maven\\src\\main\\java\\com\\crm\\config\\config.properties");
			properties.load(fi);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		
		String browserName = properties.getProperty("browser");
		
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\chromedriver.exe");
			driver = new ChromeDriver();			
		}else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Eclipse\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WenEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(com.crm.util.Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(com.crm.util.Util.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(properties.getProperty("url"));		
		
	}

}
