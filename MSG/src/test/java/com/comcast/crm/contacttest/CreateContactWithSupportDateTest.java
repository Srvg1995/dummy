package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws Throwable {
		/* Create Object*/
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();

		// read common data from PROPERTIES FILE
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");


		//read test script data from Excel file
		String lastName = elib.getDataFromExcel("contact",4,2) + jlib.getRandomNumber();

		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();}

		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();}

		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();}
		else  {
			driver=new ChromeDriver();}

		//step1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp("http://localhost:8888/","admin","admin");

		//step2:navigate to contact module
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click(); 

		//step3: click on "create contact" Button
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		//step4:enter all the details & create new contact
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);

		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createContactSD(startDate);
		cncp.createContactED(endDate);
		cncp.createContact(lastName);

		//verify header info expected result
		ContactInfoPage cip=new ContactInfoPage(driver);
		Thread.sleep(3500);
		String actStartDate = cip.getStartDate().getText();
		Thread.sleep(3500);
		if(actStartDate.equals(startDate))
		{
			System.out.println(startDate + " information is verified==PASS");
		}
		else {
			System.out.println(startDate + " information is not verified==FAIL");
		}


		String actEndDate = cip.getEndDate().getText();		
		if(actEndDate.equals(endDate))
		{
			System.out.println(endDate + " information is verified==PASS");
		}
		else {
			System.out.println(endDate + " information is not verified==FAIL");
		}


		//step5:logout
		hp.logout();
		driver.quit();
	}
}