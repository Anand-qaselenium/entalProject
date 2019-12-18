package com.entel.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.entel.pageInterface.IHomePage;

public class HomePage extends AbstractBasePage implements IHomePage {
	
	public HomePage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
		
	@FindBy(xpath="//div[@class='perfil-link']")
	WebElement customerProfileLink;
	
	@FindBy(xpath="//div[@id='page_left_value_div']//li")
	List<WebElement> leftNavLinkList;
	
	@FindBy(xpath="//div[@class='perfil-link']/following-sibling::span")
	WebElement expandCollapseIcon;
	
	@FindBy(xpath="//li[@class='dropdown-btn']/button")
	WebElement changeProfileButton;
	
	@FindBy(xpath="//label[not(contains(@for,'assets-radio-phone'))]/ancestor::li")
	List<WebElement> profileLinkList;
	
	@FindBy(xpath="//div[contains(@class,'clearfix')]/div[@class='user-info']/p[@class='user-name']")
	WebElement defaultProfileName;
	
	@FindBy(xpath="//div[contains(@class,'clearfix')]/div[@class='user-info']/p[@class='user-phone']")
	WebElement defaultProfileNumber;
	
	@FindBy(xpath="//div[contains(@class,'user-info-box')]/div[@class='user-info']")
	WebElement userProfile;
	
	@FindBy(xpath="//span[@class='icon-close-overly']/parent::button")
	WebElement closeFlyout;
	
	
	
	
	ILeftNavLinks oLeftNavLinks = new EntelLeftNavLinks();
	IProfile profileObj = new Profile();
	public WebElement currentLeftNavLink;
	public WebElement currentProfile;
	
	
	public boolean isHomePageDisplayed() {
		waitForPageLoad();
		checkElement(isElementDiaplayed(userProfile),"User Profile");
		return isElementDiaplayed(userProfile); 
	}

	@Override
	public int getLeftNavLinkCount() {
		return leftNavLinkList.size();
	}

	@Override
	public ILeftNavLinks getLeftNavLinks(int index) {
		currentLeftNavLink = leftNavLinkList.get(index);
		return oLeftNavLinks;
	}
	
	public class EntelLeftNavLinks implements ILeftNavLinks {

		@Override
		public String getName() {
			return currentLeftNavLink.getText();
		}

		@Override
		public void click() {
			currentLeftNavLink.click();
		}
		
	}

	public void navigateToGivenLeftNavLink(String string) {
		int num = getLeftNavLinkCount();
		for(int i=0;i<num;i++) {
			String linkName = getLeftNavLinks(i).getName();
			if(linkName.trim().toLowerCase().equals(string.trim().toLowerCase())) {
				getLeftNavLinks(i).click();
				sleep(10);
				break;
			}
		}
	}
	
	@Override
	public void expandProfile() {
		if(expandCollapseIcon.getAttribute("aria-expanded").equals("false")) {
			expandCollapseIcon.click();
			sleep(2);
		}
	}

	@Override
	public void collapseProfile() {
		if(expandCollapseIcon.getAttribute("aria-expanded").equals("true")) {
			expandCollapseIcon.click();
			sleep(2);
		}
	}

	@Override
	public void clickOnChangeProfile() {
		if(changeProfileButton.isEnabled()) {
			changeProfileButton.click();
			sleep(5);
		}
	}

	@Override
	public int getProfileCount() {
		expandProfile();
		return profileLinkList.size();
	}

	@Override
	public IProfile getProfile(int index) {
		currentProfile = profileLinkList.get(index);
		return profileObj;
	}

	public class Profile implements IProfile {

		@Override
		public String getName() {
			return currentProfile.findElement(By.xpath(".//p[@class='user-name']")).getText();			
		}

		@Override
		public String getPhoneNumber() {
			return currentProfile.findElement(By.xpath(".//p[@class='user-phone']")).getText();	
		}

		@Override
		public void select() {
			currentProfile.findElement(By.xpath(".//div[@class='radio-con']/label")).click();
			sleep(2);
		}
		
	}

	public void selectProfileWithName(String profName) {
		String curName = null;
		int num = getProfileCount();
		for(int i=0;i<num;i++) {
			curName = getProfile(i).getName();
			if(curName.trim().toLowerCase().equals(profName.trim().toLowerCase())) {
				getProfile(i).select();
				break;
			}
		}
	}
	
	public void selectProfileWithPhoneNumber(String profNumber) {
		String curNumber=null;
		int num = getProfileCount();
		for(int i=0;i<num;i++) {
			curNumber = getProfile(i).getPhoneNumber();
			if(curNumber.trim().toLowerCase().equals(profNumber.trim().toLowerCase())) {
				getProfile(i).select();
				sleep(2);
				clickOnChangeProfile();
				sleep(5);
				collapseProfile();
				sleep(2);
				break;
			}
		}
	}

	public boolean isGivenNumberDefault(String updPhoneNumber) {
		 String actPhoneNumb = defaultProfileNumber.getText();
		 return(compairString(actPhoneNumb, updPhoneNumber));
	}

	public void displayUserAccountNumbers() {
		if(isElementDiaplayed(expandCollapseIcon)) {
			expandProfile();
			int num = getProfileCount();
			for(int i=0;i<num;i++) {
				String phoneNum = getProfile(i).getPhoneNumber();
				System.out.println(phoneNum);
			}
			collapseProfile();
		} else {
			System.out.println("User does not have other profiles");
		}
		
	}

	public String getDefaultPhoneNumber() {	
		return defaultProfileNumber.getText();
	}
	
	public String getUserName() {
		return userProfile.findElement(By.xpath("./p[1]")).getText();
	}

	public boolean validateGivenLeftNavLink(String[] initialArray) {
		boolean flag = true;
		for(int i=0;i<initialArray.length;i++) {
			String str = initialArray[i];
			flag = isLinkDisplayed(str);
			if(flag==false) {
				System.out.println("Found issue with: "+str);
				break;
			}
		}
		
		return flag;
	}

	private boolean isLinkDisplayed(String expString) {
		boolean flag = false;
		int num = getLeftNavLinkCount();
		for(int i=0;i<num;i++) {
			String currentLinkName = getLeftNavLinks(i).getName();
			if(currentLinkName.trim().toLowerCase().equals(expString.trim().toLowerCase())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
}
