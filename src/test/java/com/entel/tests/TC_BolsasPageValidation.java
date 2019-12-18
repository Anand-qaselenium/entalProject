package com.entel.tests;


import java.io.IOException;
import org.testng.annotations.Test;
import com.entel.pages.BolsasPage;
import com.entel.pages.HomePage;
import com.entel.pages.LoginFlyout;
import com.entel.pages.LoginPage;

public class TC_BolsasPageValidation extends BaseClass {

	@Test
	public void bolsasPageValidation() throws IOException {
		String tcName = new Throwable().getStackTrace()[0].getMethodName();
		LoginPage lp = new LoginPage(driver);
		LoginFlyout lf = new LoginFlyout(driver);
		HomePage hp = new HomePage(driver);
		BolsasPage bp = new BolsasPage(driver);
		
		logger.info("Started Test case:- "+tcName );
		checkCondition(lp.isLoginPageDisplayed(), "Application Launch",tcName);
		lp.clickOnLogIn();
		logger.info("Clicking on Login - SUCCESSFUL");
		checkCondition(lf.isLoginFlyoutDisplayed(), "Login Flyout",tcName);
		
		lf.setPhoneNumber(phoneNumb_user);
		lf.setRUTNum(rut_user);
		lf.setPassword(password_user);
		logger.info("Populating Login credentials - SUCCESSFUL");
		lf.clickOnLoginButton();
		
		checkCondition(hp.isHomePageDisplayed(), "User Profile", tcName);
		
		hp.selectProfileWithPhoneNumber("979888082");
		
		hp.clickOnLinkWithText("Bolsas");
		checkCondition(bp.isBolsasPageDisplayed(), "Header", tcName);
		bp.navigateToGivenBreadCrumb("Inicio");
		checkCondition(hp.isHomePageDisplayed(), "User Profile", tcName);
		hp.clickOnLogout();
	}

}
