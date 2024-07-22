package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	DataBaseUtility dblib=new DataBaseUtility();
	FileUtility flib=new FileUtility();
	WebDriver driver=null;
	
	@BeforeSuite
	public void configBS() throws Throwable {
		System.out.println("=====Connect to DB , Report Config======");
		dblib.getDbconnection();
	}
	@BeforeClass
	public void configBC() throws Throwable {
		System.out.println("===Launch the BROWSER===");
		String BROWSER = flib.getDataFromPropertiesFile("browser");

		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();}

		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();}

		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();}
		else  {
			driver=new ChromeDriver();}
	}
	@BeforeMethod
	public void configBM() throws Throwable {
		System.out.println("==Login==");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(null, null, null);
	}
	
	@AfterMethod
	public void configAM() {
		System.out.println("==Logout==");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	@AfterClass
	public void configAC() {
		System.out.println("===Close the BROWSER===");
		driver.quit();
	}
	@AfterSuite
	public void configAS() throws Throwable {
		System.out.println("=====Close DB , Report BackUp======");
		dblib.closeDbconnection();
	}

}
