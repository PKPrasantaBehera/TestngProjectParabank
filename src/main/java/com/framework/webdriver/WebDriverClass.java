package com.framework.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.framework.reports.ReportsClass;

public class WebDriverClass extends ReportsClass{

	private static WebDriver driver = null;

	@BeforeMethod(alwaysRun=true)
	@Parameters(value="browser")
	public void setupBrowser(String browser) {
		if (browser.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardownBrowser() {
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
