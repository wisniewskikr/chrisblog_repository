package pl.kwi.test.utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

/**
 * Class with utils for Selenium.
 * 
 * @author Krzysztof Wisniewski
 */
public class SeleniumUtil {
	
	
	/**
	 * Method switches focus on browser`s window/tab with specified
	 * title. 
	 * 
	 * @param driver object WebDriver with list of all active windows in browser
	 * @param windowTitle object String with title of window/tab
	 */
	public static void switchToWindowWithTitle(WebDriver driver, String windowTitle){
		
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowId : windowHandles) {
        	driver.switchTo().window(windowId);
        	if(windowTitle.equals(driver.getTitle())){
        		break;
        	}
		}
        
	}
	

}
