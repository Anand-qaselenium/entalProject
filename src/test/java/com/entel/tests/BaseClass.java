package com.entel.tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.entel.pages.HomePage;
import com.entel.pages.LoginFlyout;
import com.entel.pages.LoginPage;
import com.entel.pages.MiPlanPage;
import com.entel.utilities.ReadConfig;
import com.entel.utilities.XLUtils;


public class BaseClass  {
	
	ReadConfig rg = new ReadConfig();
	String baseURL = rg.getConfigValue("baseURL");
	String chroPath = rg.getConfigValue("chromePath");
	String firefoxPath = rg.getConfigValue("firefoxPath");
	public static Logger logger;
	public static WebDriver driver;
	
		
	@Parameters("browser")
	@BeforeClass
	public void launch(String browser) {
		
		logger = Logger.getLogger("entalProject");
		PropertyConfigurator.configure("log4j.properties");
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",chroPath);
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
		} else {
			System.setProperty("webdriver.gecko.driver",firefoxPath);
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseURL);
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") +"/screenshots/"+ tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String getExcellData(String data) {
		String path = "src//test//java//com//entel//testData//TestData.xlsx";
		String retVal="";
		try {
			System.out.println("Required Data: "+data);
				int rowNum = XLUtils.getRowCount(path,"login");
					for(int i=1;i<=rowNum;i++) {
						String celData = XLUtils.getCellData(path, "login", i, 0);
						System.out.println("Caught Data: "+celData);
						if(data.trim().toLowerCase().equals(celData.trim().toLowerCase())) {
							System.out.println("Checking Data: "+data.trim().toLowerCase().equals(celData.trim().toLowerCase()));
							retVal = XLUtils.getCellData(path, "login", i, 1);
							break;
						}
					}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
		
	}
	
	public String randomestring() {
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	public void checkCondition(boolean flag, String str, String testCaseName) throws IOException {
		sleep(6);
		if(flag==true) {
			logger.info(str+" -SUCCESSFUL");
			Assert.assertTrue(true);
		} else {
			logger.info(str+" -FAILED");
			captureScreen(driver,testCaseName);
			Assert.assertTrue(false);
		}
	}
	
	private void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void compairString(String str1, String str2, String str3,String testCaseName ) throws IOException {
		boolean flag = false;
		if(str1.trim().toLowerCase().equals(str2.trim().toLowerCase())) {
			flag = true;
		}
		
		if(flag==true) {
			logger.info(str3+" -SUCCESSFUL");
			Assert.assertTrue(true);
		} else {
			logger.info(str3+" -FAILED");
			captureScreen(driver,testCaseName);
			Assert.assertTrue(false);
		}

	}
		
}
