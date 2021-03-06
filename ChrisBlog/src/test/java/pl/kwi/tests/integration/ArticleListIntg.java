package pl.kwi.tests.integration;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticleListIntg {
	
	private String pathHost;
	private String pathContext;
	
	private WebDriver driver;
	private Wait wait;
	private String text;
	
	@Before
	public void setUp(){
		pathHost = System.getProperty("path.host");
		pathContext = System.getProperty("path.context");
	}
	
	@Test
	public void testArticleListPage(){
		
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 20);

        driver.get(pathHost + pathContext);
        
     // Step 1
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        text = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", text);        
        String title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog - List of Articles", title);
        // actions
//        driver.findElement(By.id("create")).click();
		
	}

}
