package insta;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Follow {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe") ;
	}
	public static void main(String[] args) throws InterruptedException {
		//credentials
		String username="kuntal7875";
		String password="kuntal123";

		WebDriver driver=new ChromeDriver();	//open browser
		driver.manage().window().maximize();	//maximize window
		
		driver.get("https://www.instagram.com/accounts/login/?hl=en");	//website
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	//wait

		//login
		driver.findElement(By.xpath("//input[@aria-label='Phone number, username, or email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@aria-label='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		//handle save password & notification
		Thread.sleep(3000);
		String title = driver.getTitle();
		for(int i=0;i<2;i++) {
			if(title.equalsIgnoreCase("Instagram")) {
				driver.findElement(By.xpath("//button[.='Not Now']")).click();
				Thread.sleep(3000);
			}
		}
		driver.findElement(By.xpath("//div[.='See All']")).click();
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		
		//scroll down
		Thread.sleep(500);
		for(int i=0;i<4;i++) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(500);
		}

		//scroll up
		js.executeScript("window.scrollTo(0, 0)");
		Thread.sleep(500);
		
		//follow
		Thread.sleep(3000);
		List<WebElement> follow = driver.findElements(By.xpath("//div[@class='                    Igw0E   rBNOH        eGOV_     ybXk5    _4EzTm                                                                                   XfCBB          HVWg4                 ']/div[3]/button[.='Follow']"));
		int x = follow.size();
		System.out.println("You followed "+x+" people.");
		for(WebElement f:follow) {
			f.click();
		}

		//logout
		driver.findElement(By.xpath("//div[@class='MWDvN buoMu nfCOa']/div[1]/a")).click();	//go to homepage
		driver.findElement(By.xpath("//div[@class='MWDvN  nfCOa']/div[3]/div/div[5]//img[@data-testid='user-avatar']")).click();
		driver.findElement(By.xpath("//div[@class='-qQT3']/div[.='Log Out']")).click();
		driver.close();
	}
}
