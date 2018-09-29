package com.crm.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.base.Base;
import com.crm.pages.ContactsPage;
import com.crm.pages.DealsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.util.Util;


public class HomePageTest extends Base{

	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;
	public DealsPage dealsPage;
	public Util util;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		util = new Util();
		homePage = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
		Thread.sleep(3000);
	}
	
	@Test
	public void validateHomepageTitleTest() {
		String title = homePage.homepageTitle();
		Assert.assertEquals(title, "CRMPRO");
	}
	
	@Test( dependsOnMethods = "validateHomepageTitleTest" )
	public void validateLoggedinUserTest() {
		util.switchToFrame();
		boolean loggedinUsername = homePage.loggedinUserName();
		Assert.assertTrue(loggedinUsername);
	}
	
	@Test( dependsOnMethods = "validateLoggedinUserTest" )
	public void verifyContactslinkTest() {
		util.switchToFrame();
		contactsPage = homePage.clickContactslink();
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
