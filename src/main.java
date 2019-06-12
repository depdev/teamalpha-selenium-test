import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
public class main {
	public static void main(String[] args) {
	    addRow();
		editRow();
	    deleteRow();
	   }
public static void addRow() {
    // TODO Auto-generated method stub
    String colValue = "Mradul";
    String cDriver = "C:\\Users\\mradu\\Documents\\chromedriver.exe";
    String appUrl = "ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000";
    //connect to chromedriver
     System.setProperty("webdriver.chrome.driver", cDriver);
     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();

     driver.get(appUrl);
   
    WebElement taskName = driver.findElement(By.name("title"));
   
    taskName.sendKeys(colValue);
    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   
    WebElement taskDesc = driver.findElement(By.name("description"));
   
    taskDesc.sendKeys("added to test selenium script");
   
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    WebElement saveTask = driver.findElement(By.name("save_task"));
   
    saveTask.click();
    
    driver.close();
    
}

public static void editRow() {
    // TODO Auto-generated method stub
	int n = 1;
    String colValue = "Mradul";
    String newValue = "Mrad-Edited";
    String innerText = "";
    String cDriver = "C:\\Users\\mradu\\Documents\\chromedriver.exe";
    String appUrl = "ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000";
    //connect to chromedriver
     System.setProperty("webdriver.chrome.driver", cDriver);
     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();

     driver.get(appUrl);
     
     for (n=1; n<30; n++)
     {
		innerText = driver.findElement(By.xpath("//table/tbody/tr[" + n + "]/td[1]")).getText();
	                                                                       
     if(innerText != null && !innerText.isEmpty())
     {	
     if(innerText.equals(colValue))
     {
     	System.out.println(innerText);
     	driver.findElement(By.xpath("//html/body/main/div/div[2]/table/tbody/tr[" + n + "]/td[4]/a[1]")).click();
     	
        WebElement taskName = driver.findElement(By.name("title"));
        taskName.clear();
        taskName.sendKeys(newValue);
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       
        WebElement taskDesc = driver.findElement(By.name("description"));
        
        taskDesc.clear();
       
        taskDesc.sendKeys("Updated the data entry");
       
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement saveTask = driver.findElement(By.name("update"));
       
        saveTask.click();
        	
     	System.out.println("Edit is successfull");
     	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     	break;
     }
     }
     else
     {
      break;
     }
     }
 
     driver.close();
}   


public static void deleteRow() {
	int n = 1;
    String colValue = "Mrad-Edited";
    String innerText = "";
    String cDriver = "C:\\Users\\mradu\\Documents\\chromedriver.exe";
    String appUrl = "ec2-3-8-115-205.eu-west-2.compute.amazonaws.com:8000";
    //connect to chromedriver
     System.setProperty("webdriver.chrome.driver", cDriver);
     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();

     driver.get(appUrl);
     
     for (n=1; n<30; n++)
     {
		innerText = driver.findElement(By.xpath("//table/tbody/tr[" + n + "]/td[1]")).getText();
	                                                                       
     if(innerText != null && !innerText.isEmpty())
     {	
     if(innerText.equals(colValue))
     {
     	System.out.println(innerText);
     	driver.findElement(By.xpath("//html/body/main/div/div[2]/table/tbody/tr[" + n + "]/td[4]/a[2]")).click();
     	n=1;
     	System.out.println("Deletion is successfull");
     }
     }
     else
     {
     	break;
     }
     }
     driver.close();
}
}
