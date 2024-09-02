package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class LoginPage extends BaseClass {
	
	@FindBy(id = "email")
	WebElement emailText;
	
	@FindBy(id = "passwd")
	WebElement passwordText;
	
	@FindBy(id = "SubmitLogin")
	WebElement signInButton;
	
	@FindBy(id = "SubmitCreate")
	WebElement createAccountButton;

	@FindBy(id = "email_create")
	WebElement createAccountEmailText;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	public HomePage login(String uname, String pswd,HomePage homePage) throws Throwable {
		Action.scrollByVisibilityOfElement(getDriver(), emailText);
		Action.type(emailText, uname);
		Action.type(passwordText, pswd);
		Action.JSClick(getDriver(), signInButton);
		Thread.sleep(2000);
		homePage=new HomePage();
		return homePage;
	}
	
	public AddressPage login(String uname, String pswd,AddressPage addressPage) throws Throwable {
		Action.scrollByVisibilityOfElement(getDriver(), emailText);
		Action.type(emailText, uname);
		Action.type(passwordText, pswd);
		Action.click(getDriver(), signInButton);
		Thread.sleep(2000);
		addressPage=new AddressPage();
		return addressPage;
	}
	
	public AccountCreationPage createNewAccount(String newEmail) throws Throwable {
		Action.type(createAccountEmailText, newEmail);
		Action.click(getDriver(), createAccountButton);
		return new AccountCreationPage();
	}
}
