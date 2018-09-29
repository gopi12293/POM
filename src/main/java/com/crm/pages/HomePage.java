package com.crm.pages;

import java.rmi.activation.ActivationSystem;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.Base;

public class HomePage extends Base{
	
	
	@FindBy(xpath = "//td[contains(text(), 'User: Naveen K')]")
	WebElement loggedinUsername;
	
	@FindBy(xpath = "//a[contains(text(), 'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[contains(text(), 'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[@title='New Contact']")
	WebElement newContactLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String homepageTitle() {
		return driver.getTitle();
	}
	
	public boolean loggedinUserName() {
		return loggedinUsername.isDisplayed();
	}
	
	public ContactsPage clickContactslink() {
		contactsLink.click();
		return new ContactsPage();
	}
	
	public void clickOnNewcontact() {
		Actions actions = new Actions(driver);
		actions.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
		
	public DealsPage clickDealslink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	
	
	
	
	
}
