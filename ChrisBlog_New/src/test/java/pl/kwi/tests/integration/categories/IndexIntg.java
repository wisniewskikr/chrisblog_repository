package pl.kwi.tests.integration.categories;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexIntg {
	
	@Test
	public void test(){
		
		WebDriver driver;
		Wait wait;
		String text;
		
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 120);

        driver.get("http://localhost:8080/ChrisBlog");
        
     // Step 1
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        text = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", text);        
        String title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        // actions
//        driver.findElement(By.id("create")).click();
		
	}

}
