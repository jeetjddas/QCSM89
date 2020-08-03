package com.autodeskcrm.genericutility;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	

	public String verifyPage()
	{
		
		return "";
	}
	
	
	public void verifyPage(String actualName)
	{
		
	}
	/**
	 * wait till  all the element to load in DOM document
	 * @param driver
	 */
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * wait for  visibility of specific element in GUI
	 * @param driver
	 */
	public void waitForElemnetVsibility(WebDriver driver, WebElement expElemnet) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(expElemnet));
	}
	
	/**
	 * wait for  page title to be available
	 * @param driver
	 */
	public void waitForPageTitleVsibility(WebDriver driver, String pageTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(pageTitle));
	}
	/**
	 * wait for  element to be clickable
	 * @param driver
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement w) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(w));
	}
	
	/**
	 * wait for  any element
	 * @param driver
	 * @throws InterruptedException 
	 */
	public boolean waitForAnyElement(WebDriver driver, WebElement expElement) throws InterruptedException {
		boolean flag = false;
		int count =0;
		while(count < 30) {
		      try {
		    	  expElement.isDisplayed();
		    	  flag=true;
		    	  break;
		      }catch(Throwable t) {
		    	  count++;
		    	  Thread.sleep(500);
		      }
		}
		return flag;
	}
	
	/**
	 * wait and click an element
	 * @param driver
	 * @throws InterruptedException 
	 */
	public boolean waitAndClickElement(WebDriver driver, WebElement expElement) throws InterruptedException {
		boolean flag = false;
		int count =0;
		while(count < 30) {
		      try {
		    	  expElement.click();
		    	  flag=true;
		    	  break;
		      }catch(Throwable t) {
		    	  count++;
		    	  Thread.sleep(1000);
		      }
		}
		return flag;
	}
	
	/**
	 * select the value from the dropdown list based on visibleText
	 * @param dropDwonElement
	 * @param text
	 */
	public void select(WebElement dropDownElemnet , String text) {
		Select sel = new Select(dropDownElemnet);
		sel.selectByVisibleText(text);
		
	}
	
	/**
	 * select the value from the dropdown list based on index
	 * @param dropDownElemnet
	 * @param text
	 */
	public void select(WebElement dropDownElement , int index) {
		Select sel = new Select(dropDownElement);
		sel.selectByIndex(index);
	}
	
	/**
	 * switch to new Window
	 * @param driver
	 * @param pageTitle
	 */
	
	public void switchToNewTab(WebDriver driver, String pageTitle) {
		 Set<String> set = driver.getWindowHandles();
		 Iterator<String> it = set.iterator();
		 
		 while (it.hasNext()) {
			 String winID = it.next();
			 driver.switchTo().window(winID);
		      String currentPageTitle = driver.getTitle();
		      if(currentPageTitle.contains(pageTitle)) {
		    	  break;
		      }
		}
	}
	
	/**
	 * to handle pop up and click on accept or ok
	 * @param driver
	 */
	public void alertOk(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 * to handle pop up and click on cancel or dismiss
	 * @param driver
	 */
	public void alertCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
		
	}
	/**
	 * for mouse hovering to any element
	 * @param driver
	 * @param element
	 */
     public void moveMouseToElemnet(WebDriver driver , WebElement element) {
    	 Actions act= new Actions(driver);
    	 act.moveToElement(element).perform();
    			 
     }
     /**
      * for double clicking on any element
      * @param driver
      * @param element
      */
     public void doubleClcik(WebDriver driver , WebElement element) {
    	 Actions act= new Actions(driver);
    	 act.doubleClick(element).perform();
    			 
     }
     /**
      * to perform right click action of mouse in any element
      * @param driver
      * @param element
      */
     public void rightClcik(WebDriver driver , WebElement element) {
    	 Actions act= new Actions(driver);
    	 act.contextClick(element).perform();
    			 
     }
     
     /**
      * to get random number in integer form
      * @return
      */
     public int getRamDomNum() {
 		Random ranObj = new Random();
 		int randata =ranObj.nextInt(1000);
		return randata;
     }
     /**
      * switch to frame by string
      * @param drver
      * @param att
      */
     public void switchToFrame(WebDriver driver , String att) {
           driver.switchTo().frame(att);
     }
     /**
      * switch to frame by index
      * @param drver
      * @param att
      */
     public void switchToFrame(WebDriver driver , int index) {
         driver.switchTo().frame(index);
   }
     /**
      * switch to frame by element
      * @param drver
      * @param att
      */
     public void switchToFrame(WebDriver driver , WebElement elemnent) {
         driver.switchTo().frame(elemnent);
   }
     /**
      * for mouse scrolling and to perform action on a disable element
      * @param driver
      * @param javaScript
      */
     public void executeJavaScript(WebDriver driver  , String javaScript) {
    	 JavascriptExecutor js = (JavascriptExecutor)driver;
    	 js.executeScript(javaScript);
     }
}
