package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.sukgu.Shadow;

public class SalesForce {

	public static void main(String[] args) throws InterruptedException, IOException
	{
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("--disable-notifications");
	ChromeDriver driver=new ChromeDriver(opt);
	driver.get("https://login.salesforce.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	WebElement id=driver.findElement(By.id("username"));
	id.sendKeys("hari.radhakrishnan@qeagle.com");
	
	WebElement pwd=driver.findElement(By.id("password"));
	pwd.sendKeys("Leaf@1234");
	
	WebElement login=driver.findElement(By.id("Login"));
	login.click();
	WebElement mobile=driver.findElement(By.xpath("//span[text()='Learn More']"));
	Actions act=new Actions(driver);
	act.click(mobile).perform();

     Set <String> windowhandle=driver.getWindowHandles();
     List<String> winhand=new ArrayList<>(windowhandle);
     driver.switchTo().window(winhand.get(1));
	Thread.sleep(1000);
     WebElement confirm=driver.findElement(By.xpath("//button[text()='Confirm']"));
	act.click(confirm).perform();

	Shadow dom=new Shadow(driver);
    WebElement learning=	dom.findElementByXPath(("//span[text()='Learning']"));
    act.click(learning).perform();
   WebElement trailhead= dom.findElementByXPath("//span[text()='Learning on Trailhead']");
   act.moveToElement(trailhead).perform();
   WebElement salesforce= dom.findElementByXPath("//a[text()='Salesforce Certification']");
   act.click(salesforce).perform();
   System.out.println("Administrative page "+driver.getCurrentUrl());
   List<WebElement> certificate=driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
   
for(WebElement element:certificate)
{
	String name=element.getText();

 System.out.println("Certificate:"+name);
}
WebElement locate=driver.findElement(By.xpath("//div[@class='credentials-card ']"));
//act.scrollToElement(locate).click().perform();
File screen=locate.getScreenshotAs(OutputType.FILE);
File destn=new File("./snap1/certificate.png");
FileUtils.copyFile(screen, destn);
}
	

}
