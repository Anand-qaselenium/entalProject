package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractBasePage {

	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//nav[@id='modalTopNav']")
	WebElement header;
	
	@FindBy(xpath="//a[@id='loginButton']")
	@CacheLookup
	WebElement loginLink;
	

	public boolean isLoginPageDisplayed() {
		return isElementDiaplayed(header); 
	}
	

	public void clickOnLogIn() {
		clickAnElement(loginLink);
	}

	
}
