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

public class ArticleIntg {
	
	private String pathHost;
	private String pathContext;
	
	private WebDriver driver;
	private Wait wait;
	
	private String header;
	private String title;
	private String text;
	
	@Before
	public void setUp(){
		pathHost = System.getProperty("path.host");
		pathContext = System.getProperty("path.context");
	}
	
	@Test
	public void articleHelloWorldServletsNavigation(){
		
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 20);

        driver.get(pathHost + pathContext);
        
        
        // Article list
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog - List of Articles", title);
        text = driver.findElement(By.id("hello-world-servlets_titleList")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        // actions
        driver.findElement(By.id("hello-world-servlets_readMore")).click();
        
        
        // Article "Hello World Servlets" page 1
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog - Article", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
               
	}

}
