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
	public void navigation(){
		
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 120);

        driver.get(pathHost + pathContext);
        
        
        // Article list
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("hello_world_servlets_title_list")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        // actions
        driver.findElement(By.id("hello_world_servlets_read_more")).click();
        
        
        // Article "Hello World Servlets" page 1
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(1 of 4)", text);
        // actions
        driver.findElement(By.id("page_2")).click();
        
        
        // Article "Hello World Servlets" page 2
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(2 of 4)", text);
        // actions
        driver.findElement(By.id("page_3")).click();
        
        
        // Article "Hello World Servlets" page 3
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(3 of 4)", text);
        // actions
        driver.findElement(By.id("page_4")).click();
        
        
        // Article "Hello World Servlets" page 4
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(4 of 4)", text);
        // actions
        driver.findElement(By.id("first")).click();
		
        
        // Article "Hello World Servlets" page 1
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(1 of 4)", text);
        // actions
        driver.findElement(By.id("next")).click();
        
        
        // Article "Hello World Servlets" page 2
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(2 of 4)", text);
        // actions
        driver.findElement(By.id("next")).click();
        
        
        // Article "Hello World Servlets" page 3
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(3 of 4)", text);
        // actions
        driver.findElement(By.id("next")).click();
        
        
        // Article "Hello World Servlets" page 4
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(4 of 4)", text);
        // actions
        driver.findElement(By.id("previous")).click();
        
        
        // Article "Hello World Servlets" page 3
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(3 of 4)", text);
        // actions
        driver.findElement(By.id("previous")).click();
        
        
        // Article "Hello World Servlets" page 2
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(2 of 4)", text);
        // actions
        driver.findElement(By.id("previous")).click();
        
        
        // Article "Hello World Servlets" page 1
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(1 of 4)", text);
        // actions
        driver.findElement(By.id("last")).click();
        
        
        // Article "Hello World Servlets" page 4
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("articleTitle")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        text = driver.findElement(By.id("articlePages")).getText();
        Assert.assertEquals("(4 of 4)", text);
        // actions
        driver.findElement(By.id("home")).click();
        
     
        // Article list
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog", title);
        text = driver.findElement(By.id("hello_world_servlets_title_list")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        
	}

}
