package com.entel.tests;


import java.io.IOException;
import org.testng.annotations.Test;
import com.entel.pages.HomePage;
import com.entel.pages.LoginFlyout;
import com.entel.pages.LoginPage;
import com.entel.pages.MiPlanPage;

public class TC_MyPlanValidation extends BaseClass {
	String phoneNumb = "987295964";
	String rut = "126452276";
	String password= "7371";

	@Test
	public void validCredentialsLogintest() throws IOException {
		String tcName = new Throwable().getStackTrace()[0].getMethodName();
		LoginPage lp = new LoginPage(driver);
		LoginFlyout lf = new LoginFlyout(driver);
		HomePage hp = new HomePage(driver);
		MiPlanPage mp = new MiPlanPage(driver);

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
		
		hp.navigateToGivenLeftNavLink("Plan");
		checkCondition(mp.isMyPlanPageDisplayed(), "Mi Plan", tcName);
		
		mp.navigateToGivenBreadCrumb("Inicio");
		checkCondition(hp.isHomePageDisplayed(), "User Profile", tcName);
	}

}
