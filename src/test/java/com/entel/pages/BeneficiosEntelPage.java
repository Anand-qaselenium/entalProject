package com.entel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.entel.pageInterface.IBeneficiosEntelPage;

public class BeneficiosEntelPage extends AbstractBasePage implements IBeneficiosEntelPage{
	
	WebDriver ldriver;
	
	public BeneficiosEntelPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	public boolean isBeneficiousEntelPageDisplayed() {
		return isLinkDislayedInBreadCrumb("Beneficios entel");
	}

}
