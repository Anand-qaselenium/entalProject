package com.entel.tests;


import java.io.IOException;
import org.testng.annotations.Test;
import com.entel.pages.HomePage;
import com.entel.pages.LoginFlyout;
import com.entel.pages.LoginPage;

public class TC_UserSpecificLinksValidation extends BaseClass {

	@Test
	public void userSpecificLinks() throws IOException {
		String tcName = new Throwable().getStackTrace()[0].getMethodName();
		LoginPage lp = new LoginPage(driver);
		LoginFlyout lf = new LoginFlyout(driver);
		HomePage hp = new HomePage(driver);
		
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
		
		String[] initialArray = {"Plan","Mi Boleta","Consumo","Beneficios entel","Equipo","Recargar","Roaming","Configuración"};
		checkCondition(hp.validateGivenLeftNavLink(initialArray), "Left Nav Links",tcName);
		logger.info("Checking SS user links - SUCCESSFUL");
		
		hp.selectProfileWithPhoneNumber("979888082");
		
		String[] updatedArray = {"Plan","Mi Boleta","Consumo","Beneficios entel","Equipo","Recargar","Roaming","Configuración","Bolsas"};
		checkCondition(hp.validateGivenLeftNavLink(updatedArray), "Left Nav Links",tcName);
		logger.info("Checking CC user links - SUCCESSFUL");
		
		hp.clickOnLogout();
	}

}
