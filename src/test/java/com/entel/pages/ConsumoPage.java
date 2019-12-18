package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entel.pageInterface.IConsumoPage;

public class ConsumoPage extends AbstractBasePage implements IConsumoPage {

	WebDriver ldriver;
	
	public ConsumoPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'jumbotron')]/p")
	@CacheLookup
	WebElement header;
	
	public boolean isConsumoPageDisplayed() {
		boolean flag = false;
		if(header.getText().trim().contains("Consumo")) {
			flag = true;
		}
		return flag;
	}
}
