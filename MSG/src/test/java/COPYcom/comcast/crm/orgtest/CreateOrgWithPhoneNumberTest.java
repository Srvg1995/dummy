package COPYcom.comcast.crm.orgtest;

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
		  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		  driver.findElement(By.id("submitButton")).click();
		  
		  //step2:navigate to organization module
		  driver.findElement(By.linkText("Organizations")).click();
		  
		  //step3: click on "create organization" Button
		  driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		  
		  //step4:enter all the details & create new organization
		  driver.findElement(By.name("accountname")).sendKeys(orgName);
		  driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //verify Header phone Number info Expected result
		  String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		  if(actPhoneNumber.equals(phoneNumber))
		  {
			  System.out.println(phoneNumber+ " information is verified==PASS");
		  }
		  else {
			  System.out.println(phoneNumber+ "information not verified==FAIL");
		  }
		  
		  //step5:logout
		  driver.quit();
	}
	
		
		
	}


