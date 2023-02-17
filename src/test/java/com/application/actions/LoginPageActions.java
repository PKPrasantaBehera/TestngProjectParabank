package com.application.actions;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.application.elements.LoginPageElements;
import com.framework.webdriver.WebDriverClass;

public class LoginPageActions extends LoginPageElements {

	// launch application
	public void launchApplication() {
		launchAppliction(prop.getProperty("url"));
		Assert.assertEquals(getPageTitle(), prop.getProperty("title"));
		log("pass", "Application is launched successfully");
	}

	// Verify logo and caption of the application
	public void verifyLogoAndCaption() {
		Assert.assertTrue(isElementDisplayed(logo));
		log("pass", "Application logo is displayed successfully");
		Assert.assertEquals(getElementText(caption), prop.getProperty("caption"));
		log("pass", "Application caption is displayed as expected");
	}

	// Verify login page header
	public void verifyLoginPageHeader() {
		Assert.assertEquals(getElementText(loginPageHeader), prop.getProperty("loginHeader"));
		log("pass", "Application login page header is displayed as expected");
	}

	// Enter username and password
	public void enterUsernameAndPassword(String username, String password) {
		enterInfo(usernameTxtb, username);
		enterInfo(passwordTxtb, password);
		log("info", "Username and Password is updated successfully");
	}

	// Click on sign in button
	public void clickOnLoginButton() {
		click(loginBtn);
		log("info", "Clicked on the login button");
	}

	// Verify login is successful
	public void verifyLoginIsSuccessful() {
		waitForElement(accountOverviewPageHeader, 10);
		log("pass", "Application login is successful");
	}

	// Click on forgot password link
	public void clickOnForgotPasswordLink() {
		click(forgotLoginLink);
		log("info", "Clicked on the forgot password link");
	}

	// Verify forgot password page is launched successfully
	public void verifyForgotPasswordPageIsLaunchedSuccessfully() {
		waitForElement(forgotLoginPageHeader, 10);
		log("pass", "Application forgot password page is launched successfully");
	}

	// Click on register link
	public void clickOnRegistrationLink() {
		click(registrationLink);
		log("info", "Clicked on the register link");
	}

	// Verify registration page is launched successfully
	public void verifyRegistrationPageIsLaunchedSuccessfully() {
		waitForElement(registrationPageHeader, 10);
		log("pass", "Application registration page is launched successfully");
	}
	
	public static LoginPageActions getLoginPage() {
		return PageFactory.initElements(new WebDriverClass().getDriver(), LoginPageActions.class);
	}
}
