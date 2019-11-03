package com.entel.pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractBasePage {
	
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

	
}
