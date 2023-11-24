package marathan2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TataCliq {
	
	public static void main(String[] args) throws InterruptedException, IOException {
			
			ChromeDriver driver=new ChromeDriver();
			driver.get(" https://www.tatacliq.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.findElement(By.xpath("//button[text()='Ask me later']")).click();
			
			Actions action = new Actions(driver);
			WebElement brands = driver.findElement(By.xpath("(//div[@class='DesktopHeader__categoryAndBrand'])[2]"));
			action.moveToElement(brands).perform();
			Thread.sleep(5000);
			
			WebElement watches = driver.findElement(By.xpath("//div[text()='Watches & Accessories']"));
			action.moveToElement(watches).perform();
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//div[text()='Casio']")).click();
			WebElement sortDD = driver.findElement(By.className("SelectBoxDesktop__hideSelect"));
			Select state = new Select(sortDD);
			state.selectByVisibleText("New Arrivals");
			
			//driver.findElement(By.xpath("//div[text()='Men']")).click();
			
			driver.findElement(By.xpath("(//div[@class='CheckBox__base'])[1]")).click();
			// print all price of watches
			List<WebElement> price = driver.findElements(By.xpath("//div[@class='ProductDescription__priceHolder']"));
			for (int i = 0; i < price.size(); i++) {
				System.out.println(price.get(i).getText());
			}
			
			driver.findElement(By.xpath("(//div[@class='ProductModule__dummyDiv'])[1]")).click();
			Set<String> parentWindow = driver.getWindowHandles();
			List<String> windowChild = new ArrayList<String>(parentWindow);
			
			String confirmPrice = driver.switchTo().window(windowChild.get(1)).findElement(By.xpath("//div[@class='ProductDetailsMainCard__price']/h3")).getText();
			
			//String confirmPrice = driver.findElement(By.xpath("//div[@class='ProductDetailsMainCard__price']/h3")).getText();
			System.out.println("==============================");
			System.out.println("confirmPrice: "+ confirmPrice);
			
			
			driver.switchTo().window(windowChild.get(1)).findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
			driver.switchTo().window(windowChild.get(1)).findElement(By.xpath("//span[text()='GO TO BAG']")).click();
			
			String SubTotal = driver.findElement(By.xpath("(//div[@class='DesktopCheckout__info DesktopCheckout__label'])[2]")).getText();
			System.out.println("SubTotal: "+SubTotal);
			
			if(SubTotal.contains(confirmPrice)) {
				System.out.println("Prices are both Same.");
				
			}else {
				System.out.println("Different.");
			}
			
			File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
			File target = new File("./Snaps/TataClick.png");
			FileUtils.copyFile(screenshotAs, target);
			
			
			
	}

}
