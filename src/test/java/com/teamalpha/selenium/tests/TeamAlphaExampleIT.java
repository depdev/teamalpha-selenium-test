package com.teamalpha.selenium.tests;

import com.teamalpha.selenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import com.teamalpha.selenium.utils.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamAlphaExampleIT extends DriverBase {
	
	private static final Logger logger = Logger.getLogger(TeamAlphaExampleIT.class.getName());
	public String browserName = System.getProperty("browser");
	
		@Test (priority=1)	
		public void teamAlphaInitialScreen() throws Exception {
			
			WebDriver driver = getDriver();
	        driver.get("http://ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000/");
	        
	        System.out.println(" ");
	        System.out.println("**********************************************************************************************");
	        System.out.println("*******************************---------BEGIN: TESTING---------*******************************");
	        System.out.println("**********************************************************************************************");
	        ScreenshotListener takescreenshot = new ScreenshotListener();
	        takescreenshot.getScreenshot("Begin-Execution");
		}

    @Test (priority=2)
    public void teamAlphaInsert() throws Exception {
    	System.out.println(" ");
    	System.out.println("***************Enter: UI Insert Data***************");
    	
    	String taskTitle = "This will Insert Data";
    	String taskDescription = "Added using automated test script";
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit teamAlpha Web App
        driver.get("http://ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000/");

        // Check the title of the page
        System.out.println("-----Page title is: " + driver.getTitle() + "-----");
        
        // Input task title
        WebElement taskName = driver.findElement(By.name("title"));
        taskName.sendKeys(browserName + " : " + taskTitle);
        
        // Input task description
        WebElement taskDesc = driver.findElement(By.name("description"));
        taskDesc.sendKeys(browserName + " : " + taskDescription);
        
        //Save task
        WebElement saveTask = driver.findElement(By.name("save_task"));
        // Wait for the page to load, timeout after 10 seconds
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        saveTask.click();
        
        ScreenshotListener takescreenshot = new ScreenshotListener();
        takescreenshot.getScreenshot("UI-AfterInsert");
        
        System.out.println("***************Exit: UI Insert Data***************");
        System.out.println(" ");
    }
    
    @Test (priority=3)	
    public void teamAlphaEdit() throws Exception {
    	System.out.println("***************Enter: UI Edit Data***************");
    	
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
			   break;
		   }
                                                                          
	        if(innerText != null && !innerText.isEmpty()) { 
	        	if(innerText.equals(browserName + " : " + taskTitle)) {
	        		driver.findElement(By.xpath("//html/body/main/div/div[2]/table/tbody/tr[" + n + "]/td[4]/a[1]")).click();
	         
	        		WebElement taskName = driver.findElement(By.name("title"));
	        		taskName.clear();
	        		taskName.sendKeys(browserName + " : " + editedTaskTitle);
	           
	        		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		          
	        		WebElement taskDesc = driver.findElement(By.name("description"));
	        		taskDesc.clear();
	        		taskDesc.sendKeys(browserName + " : " + editedtaskDescription);
		
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
        takescreenshot.getScreenshot("UI-AfterEdit");
        
        System.out.println("***************Exit: UI Edit Data***************");
        System.out.println(" ");
    }
    
    @Test (priority=4)
    public void deleteRow() throws Exception {
    	System.out.println("***************Enter: UI Delete Data***************");
    	
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
		    	break;
	    	}
    	                                                                       
    	    if(innerText != null && !innerText.isEmpty()) { 
    	    	if(innerText.equals(browserName + " : " + TaskTitle)) {
    	    		driver.findElement(By.xpath("//html/body/main/div/div[2]/table/tbody/tr[" + n + "]/td[4]/a[2]")).click();
    	    		
   	        	 ScreenshotListener takescreenshot = new ScreenshotListener();
	             takescreenshot.getScreenshot("UI-AfterDelete");
	             
    	        System.out.println("Deletion is successfull");
    	    	}
    	    } else {
    	    	break;
    	    }
    	}
    	System.out.println("***************Exit: UI Delete Data***************");
    }
    
    @Test (priority=5)
    public void dbValidationSelect() throws SQLException {
    	System.out.println(" ");
    	System.out.println("------------------------------------------------------------------------------");
    	System.out.println("************************DB Validation: Select All Data************************");
    	System.out.println("------------------------------------------------------------------------------");
    	System.out.println("----------------------------Team Alpha Table----------------------------------");
    	Connection connection = null;
    	PreparedStatement statement = null;
    	
    	try{
    		connection = DatabaseConnector.getDBConnection();
    		connection.setAutoCommit(false);
    		String query = "select * from task";
    		statement = connection.prepareStatement(query);
    		// Get the contents of task table from DB
    		ResultSet res = statement.executeQuery(query);
    		// Print the result until all the records are printed
    		// res.next() returns true if there is any next record else returns false
    		while (res.next())
    		{
    			System.out.print("--  " + res.getString(1));
    			System.out.print("\t" + res.getString(2));
		    	System.out.print("\t" + res.getString(3));
		    	System.out.println("\t" + res.getString(4));
	       }
    	}
    	catch (SQLException exception) {
    		logger.log(Level.SEVERE, exception.getMessage());
    	}
    	finally {
			if (null != statement) {
				statement.close();
			}

			if (null != connection) {
				connection.close();
			}
		}
    	System.out.println("------------------------------End Table Row------------------------------------");
    }
    	
    @Test (priority=6)
    public void dbValidationInsert() throws SQLException {
    	
    	System.out.println(" ");	
    	System.out.println("------------------------------------------------------------------------------");
    	System.out.println("-***********************DB Validation: Insert Test Data**********************-");
    	System.out.println("------------------------------------------------------------------------------");
    	System.out.println("----------------------------Team Alpha Table----------------------------------");	
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        String titleNm = "Test Data: Inserted from DB";
        String decriptionNm = "The data is from test script";	
    	
    	try{
    		connection = DatabaseConnector.getDBConnection();
    		connection.setAutoCommit(false);
    		
    		String query = "insert into task values (1001,'" + browserName + " : " + titleNm + "','" + decriptionNm + "',NOW())";
    		statement = connection.prepareStatement(query);
    		statement.executeUpdate();
    		connection.commit();

    		String query2 = "select * from task";
    		statement2 = connection.prepareStatement(query2);
    		// Get the contents of task table from DB
    		ResultSet res2 = statement2.executeQuery(query2);
    		// Print the result until all the records are printed
    		// res.next() returns true if there is any next record else returns false
    		while (res2.next())
    		{
    			System.out.print("--  " + res2.getString(1));
    			System.out.print("\t" + res2.getString(2));
		    	System.out.print("\t" + res2.getString(3));
		    	System.out.println("\t" + res2.getString(4));
	       }
    	}
    	catch (SQLException exception) {
    		logger.log(Level.SEVERE, exception.getMessage());
    		if (null != connection) {
    			connection.rollback();
    		}
    	} finally {
    		if (null != resultSet) {
    			resultSet.close();
    		}

    		if (null != statement) {
    			statement.close();
    		}

    		if (null != connection) {
    			connection.close();
    		}
    	}
    	System.out.println("------------------------------End Table Row------------------------------------");
    	System.out.println(" ");
    	
    }
    
	@Test (priority=7)	
	public void teamAlphaDBInsertScreen() throws Exception {
		
		WebDriver driver = getDriver();
        driver.get("http://ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000/");
        
        ScreenshotListener takescreenshot = new ScreenshotListener();
        takescreenshot.getScreenshot("DB-AfterTestDataInsert");
	}
	
    @Test (priority=8)
    public void dbValidationDelete() throws SQLException {
    	System.out.println(" ");	
    	System.out.println("------------------------------------------------------------------------------");
    	System.out.println("-***********************DB Validation: Delete Test Data**********************-");
    	System.out.println("------------------------------------------------------------------------------");
        	
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String titleNm = "Test Data: Inserted from DB";
        String decriptionNm = "The data is from test script";	
        
    	try{
    		connection = DatabaseConnector.getDBConnection();
    		connection.setAutoCommit(false);
    		
    		String query = "delete from task where title = '" + browserName + " : " + titleNm + "' and description = '" + decriptionNm + "'";
    		statement = connection.prepareStatement(query);
    		statement.execute(query);
    		connection.commit();
    		// Get the contents of task table from DB
    		//ResultSet res = stmnt.executeQuery(query);
    		// Print the result until all the records are printed
    		// res.next() returns true if there is any next record else returns false
    	}
    	catch (SQLException exception) {
    		logger.log(Level.SEVERE, exception.getMessage());
    		if (null != connection) {
    			connection.rollback();
    		}
    	} finally {
    		if (null != resultSet) {
    			resultSet.close();
    		}

    		if (null != statement) {
    			statement.close();
    		}

    		if (null != connection) {
    			connection.close();
    		}
    	}
    }
    
	@Test (priority=9)	
	public void teamAlphaDBFinalScreen() throws Exception {
		
		WebDriver driver = getDriver();
        driver.get("http://ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000/");
        
        
        ScreenshotListener takescreenshot = new ScreenshotListener();
        takescreenshot.getScreenshot("Final-EndOfExecution");
        
        System.out.println(" ");
        System.out.println("**********************************************************************************************");
        System.out.println("*******************************---------END: TESTING---------*********************************");
        System.out.println("**********************************************************************************************");
        System.out.println(" ");
	}
    
}