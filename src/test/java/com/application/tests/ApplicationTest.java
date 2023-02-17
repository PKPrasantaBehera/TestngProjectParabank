package com.application.tests;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.application.actions.ForgotLoginPageActions;
import com.application.actions.LoginPageActions;
import com.application.actions.RegistrationPageActions;
import com.framework.utilities.ReadExcel;
import com.framework.webdriver.WebDriverClass;

public class ApplicationTest extends WebDriverClass {
	
	LoginPageActions loginPage;
	ForgotLoginPageActions forgotPage;
	RegistrationPageActions regPage;

	
	@Test (priority=1, groups= {"Smoke","Sanity"})
	public void verifyApplicationIsLaunchedSuccessfully() {
		loginPage = LoginPageActions.getLoginPage();
		loginPage.launchApplication();
	}
	
	@Test (priority=2, groups= {"Sanity"})
	public void verifyApplicationLogoAndCaption() {
		loginPage = LoginPageActions.getLoginPage();
		loginPage.launchApplication();
		loginPage.verifyLogoAndCaption();
	}
	
	@Test (priority=3, groups= {"Sanity"})
	public void verifyApplicationLoginPageHeader() {
		loginPage = LoginPageActions.getLoginPage();
		loginPage.launchApplication();
		loginPage.verifyLoginPageHeader();
	}
	
	@Test (priority=8, groups= {"Sanity","Regression"},dataProvider="testData")
	public void verifyApplicationLoginFeature(String user, String pass) {
		loginPage = LoginPageActions.getLoginPage();
		loginPage.launchApplication();
		loginPage.verifyLoginPageHeader();
		loginPage.enterUsernameAndPassword(user, pass);
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginIsSuccessful();
	}
	
	@Test (priority=5, groups= {"Sanity"})
	public void verifyRegistrationPage() {
		loginPage = LoginPageActions.getLoginPage();
		loginPage.launchApplication();
		loginPage.clickOnRegistrationLink();
		loginPage.verifyRegistrationPageIsLaunchedSuccessfully();
	}
	
	@Test (priority=6, groups= {"Sanity"})
	public void verifyForgotPasswordPage() {
		loginPage = LoginPageActions.getLoginPage();
		loginPage.launchApplication();
		loginPage.clickOnForgotPasswordLink();
		loginPage.verifyForgotPasswordPageIsLaunchedSuccessfully();
	}
	
	@Test (priority=7, groups= {"Regression"},dataProvider="testData")
	public void verifyRegistrationFeature(String fName, String lName,String address,String city, String state, String zip, String ph, String ssn, String username, String password) {
		loginPage = LoginPageActions.getLoginPage();
		regPage= RegistrationPageActions.getRegPage();
		loginPage.launchApplication();
		loginPage.clickOnRegistrationLink();
		loginPage.verifyRegistrationPageIsLaunchedSuccessfully();
		regPage.updateRegistrationDetailsForNewAccount(fName, lName, address, city, state, zip, ph, ssn, username, password);
		regPage.clickOnRegisterButton();
		regPage.verifyRegistrationIsSuccessful();
	}
	
	
	@DataProvider(name="testData")
	public String [][] testData(Method method){
		String [][] data = ReadExcel.readData("TestData.xlsx", method.getName());
		return data;
	}
}
