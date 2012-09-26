package pl.kwi.tests.integration;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

public class ArticleListWithTagIntg {
	
	private String pathHost;
	private String pathContext;
	
	private WebDriver driver;
	private Wait wait;
	
	private String header;
	private String title;
	private String text;
	private String url;
	
	@Before
	public void setUp(){
		pathHost = System.getProperty("path.host");
		pathContext = System.getProperty("path.context");
	}
	
	@Test
	public void testArticleListWithTag(){
		
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 120);

        driver.get(pathHost + pathContext);
        
        
        // Article list
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog - List of Articles", title);
        text = driver.findElement(By.id("hello_world_servlets_titleList")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        url = driver.getCurrentUrl();
        Assert.assertEquals(pathHost + pathContext, url);
        // actions
        driver.findElement(By.linkText("css (1)")).click();
        
        
        // Article list marked by tag 'css'
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog - List of Articles marked by Tag", title);
        text = driver.findElement(By.id("hello_world_servlets_titleList")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        url = driver.getCurrentUrl();
        Assert.assertEquals(pathHost + pathContext + "tag/css", url);
        
	}

}
