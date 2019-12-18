package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entel.pageInterface.IConfiguracionPage;

public class ConfiguracionPage extends AbstractBasePage implements IConfiguracionPage {
	
WebDriver ldriver;
	
	public ConfiguracionPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'jumbotron')]/p")
	@CacheLookup
	WebElement header;
	
	public boolean isConfiguracionPageDisplayed() {
		boolean flag = false;
		if(header.getText().trim().contains("Configuración")) {
			flag = true;
		}
		return flag;
	}

}
