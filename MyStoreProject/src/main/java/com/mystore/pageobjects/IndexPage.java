package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class IndexPage extends BaseClass {
	
	@FindBy(xpath = "//a[@class='login']")
	WebElement signInButton;

	@FindBy(id = "search_query_top")
	WebElement searchBar;
	
	@FindBy(name = "submit_search")
	WebElement searchButton;

	@FindBy(css = "img[alt='My Shop'][src*='logo.jpg']")
	WebElement imageLogo;
	
	public IndexPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	public SearchResultPage searchFor(String product) throws Throwable {
		Action.type(searchBar, product );
		Action.click(getDriver(), searchButton );
		return new SearchResultPage();
	}
	
	public LoginPage goToSignInPage() throws Throwable {
		Action.click(getDriver(), signInButton );
		return new LoginPage();
		
	}

	public IndexPage validateLogo() throws Throwable {
		Action.click(getDriver(), imageLogo );
		return new IndexPage();
		
	}
	
	public String validateTitle() {
		String myStoreTitle = Action.getTitle(getDriver());
		return myStoreTitle;
	}

}
