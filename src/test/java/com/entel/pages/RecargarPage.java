package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.entel.pageInterface.IEquipoPage;
import com.entel.pageInterface.IRecargarPage;

public class RecargarPage extends AbstractBasePage implements IRecargarPage {
	
	WebDriver ldriver;
	
	public RecargarPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'jumbotron')]/p")
	@CacheLookup
	WebElement header;
	
	public boolean isRecargarPageDisplayed() {
		boolean flag = false;
		if(header.getText().trim().contains("Recarga")) {
			flag = true;
		}
		return flag;
	}

}
