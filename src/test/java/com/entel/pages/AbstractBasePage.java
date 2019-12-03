package com.entel.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractBasePage {
	
	WebDriver ldriver;
	
	@FindBy(xpath="//div[@id='entel_loading_box']")
	WebElement pageLoad;
	
	@FindBy(xpath="//ol/li")
	List<WebElement> breadCrumbList;
	
//	public void waitTillPageLoads() {
//		boolean flag = false;
//		while(flag) {
//			String str = pageLoad.getAttribute("style");
//			if(str.contains("none")) {
//				sleep(2);
//				flag = true;
//			}
//		}
//	}
	
	public boolean isElementDiaplayed(WebElement ele) {
		try {
			pageLoadFinished();
			return ele.isDisplayed();
		} catch(Exception e) {
			return false;
		}
	}
	
	
	public void clickAnElement(WebElement ele) {
		try {
			pageLoadFinished();
			ele.click();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	
	}
	
	public void sleep(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void enterText(WebElement ele,String textToSend) {
		try {
			pageLoadFinished();
			ele.clear();
			ele.sendKeys(textToSend);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public String getText(WebElement ele) {
		String eleVal="";
		try {
			pageLoadFinished();
			eleVal = ele.getText();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return eleVal;
	}
	
	
	public void selectFromDropdown(WebElement ele,String selectionVal) {
		pageLoadFinished();
			Select sel = new Select(ele);
			List<WebElement> lst = sel.getOptions();
			for (int i = 0;i<lst.size();i++) {
				try {
					String txt = lst.get(i).getText();
					if (txt.toLowerCase().contains(selectionVal.toLowerCase())) {
						sel.selectByVisibleText(selectionVal);
						break;
					}
				} catch (Exception e) {
					sel.selectByIndex(i);
					System.out.println(e.getMessage());
				}
			}
	}
	
	public String getAttributeValue(WebElement ele,String attribute) {
		String eleVal="";
		try {
			pageLoadFinished();
			eleVal = ele.getAttribute(attribute);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return eleVal;
	}
	
	
	
	 public static ExpectedCondition<Boolean> pageLoadFinished() {
         return new ExpectedCondition<Boolean>() {
             @Override
             public Boolean apply(WebDriver driver) {
                 if (driver instanceof JavascriptExecutor){
                     JavascriptExecutor je = (JavascriptExecutor) driver;
                     je.executeScript("window.alert = function(){};");
                     je.executeScript("window.confirm = function(){return true;};");
                 }
                 return Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
             }
         };
	 }
	 
	 public void waitForPageLoad() {
		boolean flag;
		 do {
			 flag = (pageLoad.getAttribute("style")).contains("none");
			 sleep(1);
		 }while(!(flag));
		 sleep(1);
	 }
		
	 
	 public void clickOnLinkWithText(String str) {
		 pageLoadFinished();
		 ldriver.findElement(By.linkText(str)).click();
	 }
	 
	 public void moveToLinkWithText(String str) {
		 pageLoadFinished();
		 Actions act = new Actions(ldriver);
		 act.moveByOffset(100, 100).build().perform();
		 act.moveToElement(ldriver.findElement(By.linkText(str)));
		 sleep(1); 
	 }

	 public void navigateToGivenBreadCrumb(String string) {
		 String currentBreadCrumbName=null;
		int num = breadCrumbList.size();
		for(int i=0;i<num;i++) {
			currentBreadCrumbName = breadCrumbList.get(i).getText();
			if(currentBreadCrumbName.trim().toLowerCase().equals(string.trim().toLowerCase())) {
				breadCrumbList.get(i).click();
				waitForPageLoad();
				sleep(1);
				break;
			}
		}
	 }
	 
	 public boolean isLinkDislayedInBreadCrumb(String string) {
		boolean flag = false;
		 String currentBreadCrumbName=null;
		int num = breadCrumbList.size();
		for(int i=0;i<num;i++) {
			currentBreadCrumbName = breadCrumbList.get(i).getText();
			if(currentBreadCrumbName.trim().toLowerCase().contains(string.trim().toLowerCase())) {
				flag = true;
				break;
			}
		}
		return flag;
	 }
	 
	public boolean compairString(String str1, String str2) {
		boolean flag = false;
		if(str1.trim().toLowerCase().equals(str2.trim().toLowerCase())) {
			flag = true;
		}
		return flag;
	}
}
