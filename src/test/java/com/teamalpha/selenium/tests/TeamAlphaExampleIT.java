package com.teamalpha.selenium.tests;

import com.teamalpha.selenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import com.teamalpha.selenium.utils.*;

public class TeamAlphaExampleIT extends DriverBase {
	
	@Test (priority=1)	
	public void teamAlphaInitialScreen() throws Exception {
		
		WebDriver driver = getDriver();
        driver.get("http://ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000/");
        
        ScreenshotListener takescreenshot = new ScreenshotListener();
        takescreenshot.getScreenshot("BeforeTesting");
	}

    @Test (priority=2)
    public void teamAlphaInsert() throws Exception {
    	System.out.println("Enter: Insert Data");
    	
    	String taskTitle = "This will Insert Data";
    	String taskDescription = "Added using automated test script";
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit teamAlpha Web App
        driver.get("http://ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000/");

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Input task title
        WebElement taskName = driver.findElement(By.name("title"));
        taskName.sendKeys(taskTitle);
        
        // Input task description
        WebElement taskDesc = driver.findElement(By.name("description"));
        taskDesc.sendKeys(taskDescription);
        
        //Save task
        WebElement saveTask = driver.findElement(By.name("save_task"));
        // Wait for the page to load, timeout after 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        saveTask.click();
        
        ScreenshotListener takescreenshot = new ScreenshotListener();
        takescreenshot.getScreenshot("AfterInsert");
        
        System.out.println("Exit: Insert Data");
    }
    
    @Test (priority=3)	
    public void teamAlphaEdit() throws Exception {
    	System.out.println("Enter: Edit Data");
    	
    	int n = 1;
    	String taskTitle = "This will Insert Data";
    	String editedTaskTitle = "Edited: This will Insert Data";
    	String editedtaskDescription = "Edited: Added using automated test script";
    	String innerText = "";
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();
        driver.get("http://ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000/");
        
        for (n=1; n<30; n++)
        {
		   try {
			   innerText = driver.findElement(By.xpath("//table/tbody/tr[" + n + "]/td[1]")).getText();
		   } catch (Exception e) {
				   // TODO Auto-generated catch block
				   System.out.println("Reached end of Row");
			   break;
		   }
                                                                          
	        if(innerText != null && !innerText.isEmpty()) { 
	        	if(innerText.equals(taskTitle)) {
	        		driver.findElement(By.xpath("//html/body/main/div/div[2]/table/tbody/tr[" + n + "]/td[4]/a[1]")).click();
	         
	        		WebElement taskName = driver.findElement(By.name("title"));
	        		taskName.clear();
	        		taskName.sendKeys(editedTaskTitle);
	           
	        		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		          
	        		WebElement taskDesc = driver.findElement(By.name("description"));
	        		taskDesc.clear();
	        		taskDesc.sendKeys(editedtaskDescription);
		          
	        		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	        		WebElement saveTask = driver.findElement(By.name("update"));
	        		saveTask.click();
	
	        	}
	        }
	        else
	        {
	        	break;
	        }
        }
        ScreenshotListener takescreenshot = new ScreenshotListener();
        takescreenshot.getScreenshot("AfterEdit");
        
        System.out.println("Exit: Edit Data");
    }
    
    @Test (priority=4)
    public static void deleteRow() throws Exception {
    	System.out.println("Enter: Delete Data");
    	
    	int n = 1;
    	String TaskTitle = "Edited: This will Insert Data";
    	String innerText = "";

        WebDriver driver = getDriver();
        driver.get("http://ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000/");
    	     
    	for (n=1; n<30; n++) {
	    	try {
	    		innerText = driver.findElement(By.xpath("//table/tbody/tr[" + n + "]/td[1]")).getText();
	    	} catch (Exception e) {
		    	// TODO Auto-generated catch block
		    	System.out.println("End Of Row Reached");
		    	break;
	    	}
    	                                                                       
    	    if(innerText != null && !innerText.isEmpty()) { 
    	    	if(innerText.equals(TaskTitle)) {
    	    		System.out.println(innerText);
    	    		driver.findElement(By.xpath("//html/body/main/div/div[2]/table/tbody/tr[" + n + "]/td[4]/a[2]")).click();
    	    		
   	        	 ScreenshotListener takescreenshot = new ScreenshotListener();
	             takescreenshot.getScreenshot("AfterDelete");
	             
    	        System.out.println("Deletion is successfull");
    	    	}
    	    } else {
    	    	break;
    	    }
    	}
    	System.out.println("Exit: Delete Data");
    }
}