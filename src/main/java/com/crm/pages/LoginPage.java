package com.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.Base;

public class LoginPage extends Base {
	
	//Object Respository

	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//font[@color= 'red']")
	WebElement signupBtn;
	
	@FindBy(xpath = "//img[@class='img-responsive' and @alt='free crm logo']")
	WebElement crmLogo;
	
	
	//initializing elements
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public String validateLoginpageTitle() {
		
		return driver.getTitle();
		
	}
	
	public boolean validateCrmLogo() {
		
		return crmLogo.isDisplayed();
	
	}
	
	public HomePage login(String un, String pw) throws InterruptedException {
		username.sendKeys(un);
		password.sendKeys(pw);
		Thread.sleep(3000);
		loginBtn.click();
		
		return new HomePage();
	}
		
}
