package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage extends WebDriverUtility{
	
	WebDriver driver;         
	public HomePage(WebDriver driver) {       
		this.driver=driver;
		  PageFactory.initElements(driver,this);
	}

	
@FindBy(linkText="Organizations")   
private WebElement orgLink;

@FindBy(linkText="Contacts")
private WebElement contactLink;

@FindBy(linkText="Campaigns")
private WebElement campaignLink;

@FindBy(linkText="More")
private WebElement moreLink;

@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
private WebElement adminImg;

@FindBy(linkText="Sign Out")
private WebElement signOutLnk;

public WebElement getOrgLink() {
	return orgLink;
}

public WebElement getContactLink() {
	return contactLink;
}

public WebElement getCampaignLink() {
	return campaignLink;
}

public WebElement getMoreLink() {
	return moreLink;
}

public void navigateToCampaignPage()
{
	Actions act=new Actions(driver);
	act.moveToElement(moreLink).perform();
	campaignLink.click();
}

public void logout()
{
	adminImg.click();
	mousemoveOnElement(driver, signOutLnk);
}





}
