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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithIndustriesTest {

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
		String orgName = elib.getDataFromExcel("org",4,2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org",4,3);
		String type = elib.getDataFromExcel("org",4,4);


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
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement wbsele1 = driver.findElement(By.name("industry"));
		Select sel1=new Select(wbsele1);
		sel1.selectByVisibleText(industry);

		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select sel2=new Select(wbsele2);
		sel2.selectByVisibleText(type);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		
		//verify the industries and type info
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustries.equals(industry))
		{
			System.out.println(industry+ "information is verified==PASS");
		}
		else {
			System.out.println(industry+ "information is not verified==FAIL");
		}
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type))
		{
			System.out.println(type+ "information is verified==PASS");
		}
		else {
			System.out.println(type+ "information is not verified==FAIL");
		}

		//step5:logout
		driver.quit();
	}



}


