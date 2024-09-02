package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.IndexPage;
import com.mystore.utility.Log;

public class IndexPageTest extends BaseClass {
	IndexPage indexpage;
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(@Optional("Chrome")String browser) {
		launchApp(browser);
	}
		
	
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	@Test(groups = {"Smoke","Sanity"})
	public void verifyLogo() throws Throwable {
		Log.startTestCase("verifyLogo");
		indexpage = new IndexPage();
		indexpage.validateLogo();
		Log.endTestCase("verifyLogo");
	}
	
	@Test(groups = "Smoke")
	public void verifyTitle() throws Throwable {
		indexpage = new IndexPage();
		Log.startTestCase("verifyTitle");
		String getTitle = indexpage.validateTitle();
		Log.info("checking Title");
		Assert.assertEquals(getTitle, "My Shop");
		Log.endTestCase("verifyTitle");
	}
}
