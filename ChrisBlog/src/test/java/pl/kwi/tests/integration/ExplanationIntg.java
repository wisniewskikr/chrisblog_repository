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
import pl.kwi.test.utils.SeleniumUtil;

public class ExplanationIntg {
	
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
	public void testExplanationPage(){
		
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
        text = driver.findElement(By.id("hello_world_servlets_titleList")).getText();
        Assert.assertEquals("Hello World Servlets", text);
        // actions
        driver.findElement(By.linkText("web application")).click();
        

        // Move focus to new tab - with explanation
        SeleniumUtil.switchToWindowWithTitle(driver, "Chris`s Blog - Explanation");
        
        
        // Explanation 'web application'
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("headerTitle"), "Chris`s Blog"));
        // conditions
        header = driver.findElement(By.id("headerTitle")).getText();
        Assert.assertEquals("Chris`s Blog", header);        
        title = driver.getTitle();
        Assert.assertEquals("Chris`s Blog - Explanation", title);
        text = driver.findElement(By.id("explanationTitle")).getText();
        Assert.assertEquals("Web Application", text);
        
	}


}
