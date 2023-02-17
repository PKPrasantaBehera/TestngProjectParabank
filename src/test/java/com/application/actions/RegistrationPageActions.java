package com.application.actions;

import org.openqa.selenium.support.PageFactory;

import com.application.elements.RegistrationPageElements;
import com.framework.webdriver.WebDriverClass;

public class RegistrationPageActions extends RegistrationPageElements{
	
	//Update details to register for new account
	public void updateRegistrationDetailsForNewAccount(String fName, String lName,String addrs,String cty, String st, String zip, String ph, String SSN, String user, String pass) {
		enterInfo(firstName, fName);
		enterInfo(lastName, lName);
		enterInfo(address, addrs);
		enterInfo(city, cty);
		enterInfo(state, st);
		enterInfo(zipCode, zip);
		enterInfo(phoneNumber, ph);
		enterInfo(ssn, SSN);
		enterInfo(username, user);
		enterInfo(password, pass);
		enterInfo(confirmPassword, pass);
		log("info","Registration details updated successfully");
	}
	
	//Click on register button
	public void clickOnRegisterButton() {
		click(registerButton);
		log("info","Clicked on registration button");
	}
	
	//Verify registration is successful
	public void verifyRegistrationIsSuccessful() {
		waitForElement(registrationSuccess, 5);
		log("pass","Registratiopn is successful");
	}
	
	public static RegistrationPageActions getRegPage() {
		return PageFactory.initElements(new WebDriverClass().getDriver(), RegistrationPageActions.class);
	}
	
}
