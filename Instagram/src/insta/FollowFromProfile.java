package insta;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FollowFromProfile {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe") ;
	}
	public static void main(String[] args) throws InterruptedException {
		//credentials
		String username="kuntal7875";
		String password="kuntal123";
		String NameOfProfile="arianagrande";
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.instagram.com/accounts/login/?hl=en");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//login
		driver.findElement(By.xpath("//input[@aria-label='Phone number, username, or email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@aria-label='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(3000);
		String title = driver.getTitle();
		for(int i=0;i<2;i++) {
			if(title.equalsIgnoreCase("Instagram")) {
				driver.findElement(By.xpath("//button[.='Not Now']")).click();
				Thread.sleep(3000);
			}
		}

		//search bar
		driver.findElement(By.xpath("//div[@class='MWDvN  nfCOa']/div[2]/input")).sendKeys(NameOfProfile);
		
		//logout
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//div[@class='MWDvN buoMu nfCOa']/div[3]//img[@data-testid='user-avatar']")).click();
//		driver.findElement(By.xpath("//div[@class='-qQT3']/div[.='Log Out']")).click();
//		driver.close();
	}
}
