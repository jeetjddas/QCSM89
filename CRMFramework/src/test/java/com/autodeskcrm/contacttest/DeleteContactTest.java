package com.autodeskcrm.contacttest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.autodeskcrm.genericutility.ExcelLib;
import com.autodeskcrm.genericutility.FileLib;
import com.autodeskcrm.genericutility.WebDriverUtils;

public class DeleteContactTest {
	
	public class deleteContact {
		@Test
		public void createContactWithOrgtest() throws Throwable {
			

			WebDriverUtils wLib = new WebDriverUtils();
	        FileLib fLib = new FileLib();
	        ExcelLib excelLib = new ExcelLib();
			

			/* read data from property File */
			String USERNAME = fLib.getPropertyKeyValue("username");
			String PASSWORD = fLib.getPropertyKeyValue("password");
			String URL = fLib.getPropertyKeyValue("url");
			String BROWSER = fLib.getPropertyKeyValue("browser");
			
			/* read test script specific data*/
			String orgName = excelLib.getExcelData("contact", 1, 2)+ "_"+ wLib.getRamDomNum();
			String org_Type = excelLib.getExcelData("contact", 1, 3);
			String org_industry = excelLib.getExcelData("contact", 1, 4);
			String contactName = excelLib.getExcelData("contact", 1, 5);
			
			/*step 1 : launch the browser*/
			WebDriver driver = null;
			  
			 if(BROWSER.equals("chrome")) {
			   driver= new ChromeDriver();
			 } else if(BROWSER.equals("firefox")) {
				driver = new FirefoxDriver();
			 }else if(BROWSER.equals("ie")) {
					driver = new InternetExplorerDriver();
		     }else {
		    	 driver = new FirefoxDriver();
		     }
			 
			 
			wLib.waitForPageToLoad(driver);
			driver.get(URL);
			
			/*step 2 : login*/
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();
			
			/*step 3 : navigate to Org page*/
			driver.findElement(By.linkText("Organizations")).click();
			
			
			/*step 4 : navigate to create new Org page*/
			driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
			
			/*step 5 : create Org*/
			driver.findElement(By.name("accountname")).sendKeys(orgName);
		
			
			WebElement  swb1 = driver.findElement(By.name("accounttype"));
	         wLib.select(swb1, org_Type);
					
			WebElement  swb2 = driver.findElement(By.name("industry"));
	        wLib.select(swb2, org_industry);
					
	        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			/*step 6 : verify the Org*/
			String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

			Assert.assertTrue(actOrgName.contains(orgName));
			
			
			/*step 7 : navigate to Contact page*/
			driver.findElement(By.linkText("Contacts")).click();
			
			/*step 8 : navigate to create new Contact page*/
			driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
			
			/*step 9 : creat new Contact page*/
			driver.findElement(By.name("lastname")).sendKeys(contactName);
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			
			   //open new tab
		      wLib.switchToNewTab(driver, "specific_contact_account_address");
			
			driver.findElement(By.name("search_text")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.linkText(orgName)).click();
			
			//come back to parent Window
			wLib.switchToNewTab(driver, "Administrator - Contacts");
			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			/*step  10: verify the Org*/
			String actconatct = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			Assert.assertTrue(actconatct.contains(contactName));
			
			/*click on contact again*/
			driver.findElement(By.linkText("Contacts")).click();
			Thread.sleep(3000);
			/*search for contact to be deleted*/
			driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(contactName);
			WebElement dd = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
			wLib.select(dd, "Last Name");
			driver.findElement(By.xpath("//input[@name='submit']")).click();
			
			
			Thread.sleep(3000);
			/*click on check box*/
			driver.findElement(By.xpath("(//input[@onclick='check_object(this)'])[1]")).click();
			/*click on delete*/
			driver.findElement(By.xpath("(//a[text()='del'])[1]")).click();
			wLib.alertOk(driver);
			/*verifying the contact is deleted*/
			String errMsg=driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
			Assert.assertTrue(errMsg.contentEquals("No Contact Found !"));
			/*again click on organization*/
			driver.findElement(By.linkText("Organizations")).click();
			/*deleting organization wrt to contact name*/
			
			/*searching for Organization and type list*/
			driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
			WebElement dd1 = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
			wLib.select(dd1, "Organization Name");
			driver.findElement(By.xpath("//input[@name='submit']")).click();
		
			/*Deleting the Organization*/
			/*selecting the checkbox*/
			driver.findElement(By.xpath("//div[@id='ListViewContents']//table//tr[last()]//td[last()-7]")).click();
			/*click on delete button*/
			driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])[1]")).click();
			wLib.alertOk(driver);
			/*verifying the organization is deleted or not*/
			driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
			WebElement dd2 = driver.findElement(By.xpath("(//select[@name='search_field'])[1]"));
			wLib.select(dd2, "Organization Name");
			driver.findElement(By.xpath("//input[@name='submit']")).click();
			WebElement errorMSg = driver.findElement(By.xpath("//span[contains(text(),'No Organization Found !')]"));
			if (errorMSg.isDisplayed()) {
				System.out.println("No org found with that name" + errorMSg.getText());	
			}
		}
	}
}
