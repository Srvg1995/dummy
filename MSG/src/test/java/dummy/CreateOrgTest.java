package dummy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CreateOrgTest {
	
	@BeforeSuite
	public void configBS() {
		System.out.println("=====execute BS======");
	}
	@AfterSuite
	public void configAS() {
		System.out.println("=====execute AS======");
	}
	@BeforeClass
	public void configBC() {
		System.out.println("execute BC");
	}
	@BeforeMethod
	public void configBM() {
		System.out.println("execute BM");
	}
	@Test
	public void createOrgTest() {
		System.out.println("execute createOrgTest");
	}
	
	@Test
	public void createOrgWithIndustries() {
		System.out.println("execute createOrgWithIndustries");
	}
	
	@AfterMethod
	public void configAM() {
		System.out.println("execute AM");
	}
	@AfterClass
	public void configAC() {
		System.out.println("execute AC");
	}
	
	
	
	
	
	

}
