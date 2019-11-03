package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginFlyout extends AbstractBasePage {

	WebDriver ldriver;
	
	public LoginFlyout(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(xpath="//span[@class='icon-close-overly']/parent::button")
	WebElement closeFlyout;
	
	@FindBy(xpath="//input[@name='username']")
	WebElement phoneNumberText;
	
	@FindBy(xpath="//input[@name='rutt']")
	WebElement rutNum;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
		
	@FindBy(xpath="//button[@id='action-PROFILE_LOGIN']")
	WebElement loginButton;
	
	//@FindBy(xpath="//div[contains(@class,'global-error')]")
	@FindBy(xpath="//p[@data-reactid='.6.1.1.0.0.1.0.0.3.1']")
	WebElement globalErrorWarning;
	
	@FindBy(xpath="//p[@data-reactid='.6.1.1.0.0.1.0.0.4.2.0.0.0.0.1']")
	WebElement rutErrorWarning;
	
	
	
	public boolean isLoginFlyoutDisplayed() {
		sleep(2);
		return isElementDiaplayed(closeFlyout);
	}
	
	public void setPhoneNumber(String usrName) {
		enterText(phoneNumberText, usrName);
	}
	
	public void setPassword(String pwd) {
		enterText(password, pwd);
	}
	
	public void setRUTNum(String rutNumber) {
		enterText(rutNum, rutNumber);
	}
	
	public void clickOnLoginButton() {
		sleep(2);
		clickAnElement(loginButton);
	}

	public boolean isInvalidUserErrorMessageDisplayed() {
		sleep(2);
		return isElementDiaplayed(globalErrorWarning);
	}

	public boolean isInvalidRUTErrorMessageDisplayed() {
		sleep(2);
		return isElementDiaplayed(rutErrorWarning);
	}
		
}
