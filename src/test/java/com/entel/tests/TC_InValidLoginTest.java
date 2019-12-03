package com.entel.tests;


import java.io.IOException;
import org.testng.annotations.Test;

import com.entel.pages.HomePage;
import com.entel.pages.LoginFlyout;
import com.entel.pages.LoginPage;

public class TC_InValidLoginTest extends BaseClass {
	String phoneNumb = "934056823";
	String rut = "186628403";
	String password= "1236";

	@Test
	public void inValidCredentialsLogintest() throws IOException {
		String tcName = new Throwable().getStackTrace()[0].getMethodName();
		LoginPage lp = new LoginPage(driver);
		LoginFlyout lf = new LoginFlyout(driver);
		HomePage hp = new HomePage(driver);
		
		logger.info("Started Test case:- "+tcName );
		checkCondition(lp.isLoginPageDisplayed(), "Application Launch",tcName);
		lp.clickOnLogIn();
		logger.info("Clicking on Login - SUCCESSFUL");
		checkCondition(lf.isLoginFlyoutDisplayed(), "Login Flyout",tcName);
		
		
		logger.info("Invalid Phone number check");
		lf.setPhoneNumber("934056822");
		lf.setRUTNum(rut);
		lf.setPassword(password);
		logger.info("Populating Login credentials - SUCCESSFUL");
		lf.clickOnLoginButton();
		checkCondition(lf.isInvalidUserErrorMessageDisplayed(),"Invalid Phone number check",tcName);
		
		logger.info("Invalid Password check");
		lf.setPhoneNumber(phoneNumb);
		lf.setRUTNum(rut);
		lf.setPassword("1234");
		logger.info("Populating Login credentials - SUCCESSFUL");
		lf.clickOnLoginButton();
		checkCondition(lf.isInvalidUserErrorMessageDisplayed(),"Invalid password check",tcName);
		
		logger.info("Invalid RUT check");
		lf.setPhoneNumber(phoneNumb);
		lf.setRUTNum("186628402");
		lf.setPassword(password);
		logger.info("Populating Login credentials - SUCCESSFUL");
		lf.clickOnLoginButton();
		checkCondition(lf.isInvalidRUTErrorMessageDisplayed(),"Invalid RUT check",tcName);
	}

}
