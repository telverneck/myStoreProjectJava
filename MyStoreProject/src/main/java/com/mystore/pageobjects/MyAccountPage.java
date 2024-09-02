package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class MyAccountPage extends BaseClass{
	
	@FindBy(css = "img[alt='My Shop'][src*='logo.jpg']")
	WebElement imageLogo;
	
	public MyAccountPage() {
		PageFactory.initElements(getDriver(), this);
	}
	

}
