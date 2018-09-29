package com.crm.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.base.Base;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

public class LoginPageTest extends Base{

	public LoginPage loginPage;
	public HomePage homePage;
	
	public LoginPageTest() {
		// TODO Auto-generated constructor stub
		super(); //Super keyword calls Base constructor which has setup
		
	}
	
	@BeforeMethod
	public void setUp() {
		
		initialization();
		loginPage = new LoginPage();
	
	}
	
	@Test
	public void titleTest() {
		String title = loginPage.validateLoginpageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");
	}
	
	
	@Test( dependsOnMethods = "titleTest" )
	public void crmLogoTest() {
		boolean flag = loginPage.validateCrmLogo();
		Assert.assertTrue(flag);	
	
	}
	
	@Test( dependsOnMethods = "crmLogoTest" )
	public void loginTest() throws InterruptedException{
		Thread.sleep(10000);
		homePage = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
}
