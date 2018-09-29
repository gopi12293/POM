package com.crm.pages;


import java.util.List;

import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.base.Base;

public class ContactsPage extends Base{

	
	@FindBy(xpath = "//td[contains(text(), 'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(xpath = "//legend[contains(text(), 'Contact Information')]")
	WebElement contactInformationLabel;
	
	@FindBy(xpath = "//select[@name='title']")
	WebElement titleDropdown;
	
	@FindBy(xpath = "//input[@name='first_name']")
	WebElement firstName;
	
	@FindBy(xpath = "//input[@name = 'surname']")
	WebElement lastName;
	
	@FindBy(xpath = "//input[@name = 'client_lookup']")
	WebElement companyName;
	
	@FindBy(xpath = "//input[@type = 'submit' and @value = 'Save']")
	WebElement saveBtn;
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void createContact(String title, String fName, String lName, String cName) {
		Select select = new Select(titleDropdown);
		select.selectByVisibleText(title);
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		companyName.sendKeys(cName);
		saveBtn.click();
	}
}
