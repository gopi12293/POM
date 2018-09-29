package com.crm.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.base.Base;
import com.crm.pages.ContactsPage;
import com.crm.pages.DealsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.util.Util;

public class ContactsPageTest extends Base{
	public LoginPage loginPage;
	public HomePage homePage;
	public DealsPage dealsPage;
	ContactsPage contactsPage;
	public Util util;
	String sheetName = "contacts";
	
	public ContactsPageTest()  {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		util = new Util();
		homePage = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
		Thread.sleep(3000);
		util.switchToFrame();
		homePage.clickContactslink();
	}
	
	@Test
	public void verifyContactslabelTest() {
		Boolean label= contactsPage.verifyContactsLabel();
		Assert.assertTrue(label);
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		Object data[][] = util.getExceldata(sheetName);
		return data;
	}
	
	@Test(dependsOnMethods="verifyContactslabelTest", dataProvider="getData")
	public void validateCreateNewcontact(String title, String firstName, String lastName, String company) {
		homePage.clickOnNewcontact();
		contactsPage.createContact(title, firstName, lastName, company);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
