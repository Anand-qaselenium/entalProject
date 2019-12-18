package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entel.pageInterface.IBolsasPage;
import com.entel.pageInterface.IEquipoPage;
import com.entel.pageInterface.IRecargarPage;

public class BolsasPage extends AbstractBasePage implements IBolsasPage {
	
	WebDriver ldriver;
	
	public BolsasPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'jumbotron')]/p")
	@CacheLookup
	WebElement header;
	
	public boolean isBolsasPageDisplayed() {
		boolean flag = false;
		if(header.getText().trim().contains("Bolsas")) {
			flag = true;
		}
		return flag;
	}

}
