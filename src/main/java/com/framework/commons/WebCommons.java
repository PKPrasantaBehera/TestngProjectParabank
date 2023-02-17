package com.framework.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.reports.ReportsClass;
import com.framework.utilities.ReadProp;
import com.framework.webdriver.WebDriverClass;

public class WebCommons {

	public WebDriver driver = new WebDriverClass().getDriver();
	public Properties prop= ReadProp.readData("Config.properties");

	// scroll to element
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
	}

	// Click on element
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}

	// Click on hidden elements
	public void jsClick(WebElement element) {
		scrollToElement(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
	}

	// Enter text in text-box
	public void enterInfo(WebElement element, String value) {
		scrollToElement(element);
		element.clear();
		element.sendKeys(value);
	}

	// Select Check-box
	public void selectCheckbox(WebElement element) {
		scrollToElement(element);
		if (!element.isSelected())
			element.click();
	}

	// select the option from dropdown
	public void selectOption(WebElement element, String method, String option) {
		Select s = new Select(element);
		if (method.equalsIgnoreCase("Index"))
			s.selectByIndex(Integer.parseInt(option));
		else if (method.equalsIgnoreCase("value"))
			s.selectByValue(option);
		else if (method.equalsIgnoreCase("VisibleText"))
			s.selectByVisibleText(option);
	}

	// java wait
	public void wait(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// implicit wait
	public void implicitWait(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}

	// explicit wait
	public void waitForElement(By locator, int sec) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}

	// generate uniqueId
	public String uniqueId() {
		String uniqueId = new SimpleDateFormat("MMddyyhhmmss").format(Calendar.getInstance().getTime());
		return uniqueId;
	}

	// take screenshot of window and get the path to attach in reports
	public String takeScreenshotOfWindow(String name) throws IOException {
		String path = System.getProperty("user.dir") + "\\Screenshots\\" + name + uniqueId() + ".png";
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(path));
		return path;
	}

	// take screenshot of element and get the path to attach in reports
	public String takeScreenshotOfElement(WebElement element, String name) throws IOException {
		String path = System.getProperty("user.dir") + "\\Screenshots\\" + name + uniqueId() + ".png";
		File file = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(path));
		return path;
	}

	// Print message in report
	public void log(String status, String message) {
		if (status.equalsIgnoreCase("pass"))
			ReportsClass.logger.pass(message);
		else if (status.equalsIgnoreCase("fail"))
			ReportsClass.logger.fail(message);
		else if (status.equalsIgnoreCase("warning"))
			ReportsClass.logger.warning(message);
		else if (status.equalsIgnoreCase("info"))
			ReportsClass.logger.info(message);
	}

	// Attach screenshot in report
	public void attachScreenshot(String name) {
		try {
			ReportsClass.logger.addScreenCaptureFromPath(takeScreenshotOfWindow(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Browser actions
	public void browserAction(String action) {
		if (action.equalsIgnoreCase("refresh")) {
			driver.navigate().refresh();
		} else if (action.equalsIgnoreCase("back")) {
			driver.navigate().back();
		} else if (action.equalsIgnoreCase("forward")) {
			driver.navigate().forward();
		}
	}

	// get the page Title
	public String getPageTitle() {
		return driver.getTitle();
	}

	// get the text from element
	public String getElementText(WebElement element) {
		return element.getText();
	}

	// get the attribute value from element
	public String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	// Get window handle id
	public String getWindowHandleId() {
		return driver.getWindowHandle();
	}

	// Switch to Alert
	public Alert getAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	// Perform double click'
	public void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	// perform right click
	public void rightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	// perform mouse hover action
	public void mouseHover(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	// Launch url
	public void launchAppliction(String url) {
		driver.get(url);
	}

	// Element is displayed
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}
}
