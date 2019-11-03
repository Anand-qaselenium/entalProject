package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractBasePage {
	
	WebDriver ldriver;
	
	public HomePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
		
	
	@FindBy(xpath="//div[@class='perfil-link']")
	WebElement customerProfileLink;
	
	public boolean isHomePageDisplayed() {
		sleep(1);
		return isElementDiaplayed(customerProfileLink); 
	}

}
