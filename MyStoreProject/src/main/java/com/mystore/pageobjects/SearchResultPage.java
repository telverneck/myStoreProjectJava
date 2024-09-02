package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class SearchResultPage extends BaseClass{

	@FindBy(css = "img[alt='My Shop'][src*='logo.jpg']")
	WebElement imageLogo;
	
	public SearchResultPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
}
