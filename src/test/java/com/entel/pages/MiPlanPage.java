package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entel.pageInterface.ILoginPage;
import com.entel.pageInterface.IMiPlan;

public class MiPlanPage extends AbstractBasePage implements IMiPlan {

	WebDriver ldriver;
	
	public MiPlanPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	@FindBy(xpath="//div[contains(@class,'jumbotron')]/p")
	@CacheLookup
	WebElement header;
	
	@Override
	public boolean isMyPlanPageDisplayed() {
			boolean flag = false;
			if(header.getText().trim().contains("Mi Plan")) {
				flag = true;
			}
			return flag;
	}

	

	
}
