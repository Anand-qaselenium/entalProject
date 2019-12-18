package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.entel.pageInterface.IEquipoPage;

public class EquipoPage extends AbstractBasePage implements IEquipoPage {
	
	WebDriver ldriver;
	
	public EquipoPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'jumbotron')]/p")
	@CacheLookup
	WebElement header;
	
	public boolean isEquipoPageDisplayed() {
		boolean flag = false;
		if(header.getText().trim().contains("Mi equipo")) {
			flag = true;
		}
		return flag;
	}

}
