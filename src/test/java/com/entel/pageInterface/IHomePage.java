package com.entel.pageInterface;

public interface IHomePage {
	
	public int getLeftNavLinkCount();
	
	public ILeftNavLinks getLeftNavLinks(int index);
	
	public interface ILeftNavLinks {
		
		public String getName();
		
		public void click();
		
	}
	
	public int getProfileCount();
	
	public IProfile getProfile(int index);
	
	public interface IProfile{
		
		public String getName();
		
		public String getPhoneNumber();
		
		public void select();
	}

	public void expandProfile();
	
	public void collapseProfile();
	
	public void clickOnChangeProfile();
	
}
