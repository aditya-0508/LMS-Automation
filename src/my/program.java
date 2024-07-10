package my;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class program {
	WebDriver driver;
	
	@BeforeMethod
    public void setup() throws InterruptedException {
    	
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://lms.nmit.ac.in/moodle/login/index.php");
       
    }
	@Test(dataProvider="passdata1")
	public void verifytitle(String username1,String password1) throws Exception {
		
		WebElement usernametb=driver.findElement(By.name("username"));
        usernametb.sendKeys(username1);
        WebElement passtb=driver.findElement(By.name("password"));
        passtb.sendKeys(password1);
        WebElement nextButton=driver.findElement(By.id("loginbtn"));
        nextButton.click();
        System.out.println(username1); 
        String title=driver.getTitle();
        if(driver.getTitle().contains("Dashboard")) {
        Set<Cookie> cookies = driver.manage().getCookies();

        // Adding a new cookie
        Cookie newCookie = new Cookie("cookieName", "cookieValue");
        driver.manage().addCookie(newCookie);
        System.out.println("All Cookies:");
        for (Cookie cookie : cookies) {
            System.out.println("Name: " + cookie.getName());
            System.out.println("Value: " + cookie.getValue());
            System.out.println("Domain: " + cookie.getDomain());
            System.out.println("Path: " + cookie.getPath());
            System.out.println("Expiry: " + cookie.getExpiry());
            System.out.println("Secure: " + cookie.isSecure());
            System.out.println("HttpOnly: " + cookie.isHttpOnly());
            System.out.println("----------------------------------");
        }
        // Deleting a cookie
        driver.manage().deleteCookie(newCookie);
        Thread.sleep(2000);  
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 1000)");
        jsExecutor.executeScript("window.scrollBy(0, 500)");
        Thread.sleep(3000);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the destination file path
        String destinationFilePath = "C:\\Users\\Aditya\\Pictures\\Selenium\\screenshot.png";

        try {
            java.nio.file.Files.copy(screenshotFile.toPath(), new File(destinationFilePath).toPath());
            System.out.println("Screenshot saved to: " + "C:\\Users\\Aditya\\Pictures\\Selenium");
        } catch (IOException e) {
            System.err.println("Error while saving the screenshot: " + e.getMessage());
        }
        }
       
		
	}
	@DataProvider(name="passdata1")
	public Object[][] passData() 
	{
		Object[][] data=new Object[3][2];
		data[0][0]="";
		data[0][1]="";
		data[1][0]="admin1";
		data[1][1]="pass1";
		data[2][0]="";
		data[2][1]="pass";
		return data;
	}
	@Test
	public void verifytitle1() throws Exception {
		
		WebElement usernametb=driver.findElement(By.name("username"));
        usernametb.sendKeys("Pro");
        System.out.println("Pro");
        Thread.sleep(3000);
		driver.quit();
	}
    @AfterMethod
    public void tearDown() {
    	driver.quit();
    }
    
}
