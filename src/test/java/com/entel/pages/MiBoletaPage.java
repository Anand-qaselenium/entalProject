package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entel.pageInterface.IMiBoletaPage;

public class MiBoletaPage extends AbstractBasePage implements IMiBoletaPage {

	WebDriver ldriver;
	
	public MiBoletaPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'jumbotron')]/p")
	@CacheLookup
	WebElement header;

	public boolean isMiBoletaPageDisplayed() {
		boolean flag = false;
		if(header.getText().trim().contains("Boletas")) {
			flag = true;
		}
		return flag;
	}
	
}
