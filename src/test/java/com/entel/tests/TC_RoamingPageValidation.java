package com.entel.tests;


import java.io.IOException;
import org.testng.annotations.Test;

import com.entel.pages.BeneficiosEntelPage;
import com.entel.pages.ConsumoPage;
import com.entel.pages.EquipoPage;
import com.entel.pages.HomePage;
import com.entel.pages.LoginFlyout;
import com.entel.pages.LoginPage;
import com.entel.pages.MiBoletaPage;
import com.entel.pages.RecargarPage;
import com.entel.pages.RoamingPage;

public class TC_RoamingPageValidation extends BaseClass {

	@Test
	public void roamingPageValidation() throws IOException {
		String tcName = new Throwable().getStackTrace()[0].getMethodName();
		LoginPage lp = new LoginPage(driver);
		LoginFlyout lf = new LoginFlyout(driver);
		HomePage hp = new HomePage(driver);
		RoamingPage rm = new RoamingPage(driver);
		
		logger.info("Started Test case:- "+tcName );
		checkCondition(lp.isLoginPageDisplayed(), "Application Launch",tcName);
		lp.clickOnLogIn();
		logger.info("Clicking on Login - SUCCESSFUL");
		checkCondition(lf.isLoginFlyoutDisplayed(), "Login Flyout",tcName);
		
		lf.setPhoneNumber(phoneNumb);
		lf.setRUTNum(rut);
		lf.setPassword(password);
		logger.info("Populating Login credentials - SUCCESSFUL");
		lf.clickOnLoginButton();
		
		checkCondition(hp.isHomePageDisplayed(), "User Profile", tcName);
		hp.clickOnLinkWithText("Roaming");
		checkCondition(rm.isRoamingPageDisplayed(), "Header", tcName);
		rm.navigateToGivenBreadCrumb("Inicio");
		checkCondition(hp.isHomePageDisplayed(), "User Profile", tcName);
		hp.clickOnLogout();
	}

}
