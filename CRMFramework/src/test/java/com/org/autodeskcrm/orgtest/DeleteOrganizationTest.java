package com.org.autodeskcrm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
import com.autodeskcrm.genericutility.ExcelLib;
import com.autodeskcrm.genericutility.FileLib;
import com.autodeskcrm.genericutility.WebDriverUtils;

/**
 * 
 * @author JEET JD DAS
 *
 */
public class DeleteOrganizationTest {
	
	@Test
	public void deleteOrgTest() throws Throwable {
		
		WebDriverUtils wLib = new WebDriverUtils();
        FileLib fLib = new FileLib();
        ExcelLib excelLib = new ExcelLib();
		

		/* read data from property File */
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		String URL = fLib.getPropertyKeyValue("url");
		String BROWSER = fLib.getPropertyKeyValue("browser");
		
		System.out.println(BROWSER);
		
		/* read test script specific data*/
		String orgName = excelLib.getExcelData("org", 1, 2)+ "_"+ wLib.getRamDomNum();
		String org_Type = excelLib.getExcelData("org", 1, 3);
		String org_industry = excelLib.getExcelData("org", 1, 4);
		
		
		/*step 1 : launch the browser*/
		WebDriver driver = null;
		  
		 if(BROWSER.equals("chrome")) {
		   driver= new ChromeDriver();
		 } else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		 }else if(BROWSER.equals("ie")) {
				driver = new InternetExplorerDriver();
	     }else {
	    	 driver = new ChromeDriver();
	     }
		 		 
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		
		/*step 2 : login*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		/*step 3 : navigate to Organization page*/
		driver.findElement(By.linkText("Organizations")).click();
		
		
		/*step 4 : navigate to create new Organization page*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/*step 5 : create Organization*/
		driver.findElement(By.name("accountname")).sendKeys(orgName);
	
		
		WebElement  swb1 = driver.findElement(By.name("accounttype"));
	    wLib.select(swb1, org_Type);
				
		WebElement  swb2 = driver.findElement(By.name("industry"));
		wLib.select(swb2, org_industry);
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		/*navigate to organization page again*/
		WebElement w2= driver.findElement(By.linkText("Organizations"));
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", w2);
		Thread.sleep(3000);
		/*searching for Organization and type list*/
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		WebElement dd = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
		wLib.select(dd, "Organization Name");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
	
		/*Deleting the Organization*/
		/*selecting the checkbox*/
		driver.findElement(By.xpath("//div[@id='ListViewContents']//table//tr[last()]//td[last()-7]")).click();
		/*click on delete button*/
		driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])[1]")).click();
		wLib.alertOk(driver);
		/*verifying the organization is deleted or not*/
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		WebElement dd1 = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
		wLib.select(dd1, "Organization Name");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		WebElement errorMSg = driver.findElement(By.xpath("//span[contains(text(),'No Organization Found !')]"));
		if (errorMSg.isDisplayed()) {
			System.out.println("No org found with that name" + errorMSg.getText());	
		}
	
		
	}
}
