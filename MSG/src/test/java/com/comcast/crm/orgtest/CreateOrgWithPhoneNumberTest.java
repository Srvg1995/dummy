package com.comcast.crm.orgtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
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
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWithPhoneNumberTest {

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
		String orgName = elib.getDataFromExcel("org",7,2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("org",7,3);


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

		//step2:navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click(); 


		//step3: click on "create organization" Button
		OrganizationsPage cnp=new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		//step4:enter all the details & create new organization
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrgPhone(orgName, phoneNumber);

		//verify Header phone Number info Expected result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actPhoneNumber = oip.getHeaderMsg3().getText();
		if(actPhoneNumber.equals(phoneNumber))
		{
			System.out.println(phoneNumber+ " information is verified==PASS");
		}
		else {
			System.out.println(phoneNumber+ "information not verified==FAIL");
		}

		//step5:logout
		hp.logout();
		driver.quit();
	}



}


