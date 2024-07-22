package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

//Rule1:create a separate java class/POM class creation
public class LoginPage extends WebDriverUtility {   
	


	//Rule3:object initialization                 
	WebDriver driver;         
	public LoginPage(WebDriver driver) {        
		this.driver=driver;
		  PageFactory.initElements(driver,this);

	}
	//Rule2:object creation/object identification
	@FindBy(name="user_name")         
	private WebElement usernameEdt;
	

	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	                                

	//Rule4:object Encapsulation(Provide Getters methods)
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

     //Rule5:provide Action/object utilization(business logic)
	public void loginToapp(String url,String username,String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	
}
