package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	WebDriver driver;         
	public ContactsPage(WebDriver driver) {       
		this.driver=driver;
		  PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	

	@FindBy(name="search_field")
	private WebElement searchDD;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	@FindBy(xpath= "//img[@alt='Create Contact...']")
	private WebElement createNewContactBtn;
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}


	public WebElement getSearchEdt() {
		return searchEdt;
	}


	public WebElement getSearchDD() {
		return searchDD;
	}


	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
	}

	
	
	
	
	

}
