package test;
import java.awt.List;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class EnvisionMainPage {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException{
		DesiredCapabilities dc = new DesiredCapabilities();
		
		String appDir = "C:\\apkfiles\\";
        String appName = "envision-debug-off-sys.apk";
		File app = new File (appDir, appName);
		dc.setCapability("appium:platformName", "Android");
        dc.setCapability("appium:platformVersion", "14.0");
        dc.setCapability("avd","Pixel_3a_API_34_extension_level_7_x86_64");
        dc.setCapability("appium:deviceName", "Android Emulator");
        dc.setCapability("appium:app", app.getAbsolutePath());
        dc.setCapability("appium:fullContextList", "true");
        dc.setCapability("appium:automationName", "uiautomator2");
        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
   		dc.setCapability("appPackage", "ca.firstwestcu.envision.mobileapp.offsys");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(url,dc);
		Thread.sleep(10000);
		
		Set<String> contextSet = driver.getContextHandles();
		 
  		for(String contextName: contextSet) {
  			System.out.println("context Name " + contextName);
  			driver.context(contextName); //Changing the context
  		} 
		
		
		WebElement home = driver.findElementByXPath(".//span[@class='right-buttons']//button[text()='Log In']");
		home.click();
		Thread.sleep(6000);
		
		String pageTitle = driver.getTitle();
		System.out.println("Current Context : " + driver.getContext());
		System.out.println("Current Handles : " + driver.getContextHandles());
		
		Set<String> handles = driver.getContextHandles();
		String webContext = new ArrayList<String>(handles).get(1);
		System.out.println("Fetch WebContext" + webContext);
		driver.context(webContext);
		System.out.println("Current Context : " + webContext);
		
		System.out.println(pageTitle);
		
		
	    for (String handle : driver.getWindowHandles()) {
            System.out.println(handle);
            driver.switchTo().window(handle);
            System.out.println(driver.getCurrentUrl());
            if(driver.getCurrentUrl().contains("login")) {
            	break;
            }
         }
        
		Thread.sleep(2000);
		
		driver.findElementByXPath("//*[@id=\"branchSelect\"]/option[4]").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//*[@id=\"memberNum\"]").sendKeys("enterusername");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//input[contains(@name,'pac') or contains(@name,'password')]").sendKeys("enterpassword");
		Thread.sleep(2000);
		
		driver.findElementByXPath("//button[@value='Log In' or @id='loginSubmit']").click();
		Thread.sleep(5000);
		
		
		for (String handle : driver.getWindowHandles()) {
            System.out.println(handle);
            driver.switchTo().window(handle);
            System.out.println(driver.getCurrentUrl());
            if(driver.getCurrentUrl().contains("home")) {
            	break;
            }
		} 
		
		driver.findElementByXPath(".//span[@class='right-buttons']//button[text()='Log Out']").click();
		Thread.sleep(5000);
		
		String webContext1 = new ArrayList<String>(handles).get(0);
		System.out.println("Fetch WebContext" + webContext1);
		driver.context(webContext1);
		System.out.println("Current Context : " + webContext1);
		Thread.sleep(3000);
		driver.findElementById("android:id/button1").click();
		Thread.sleep(3000);
		
		driver.quit();
		
	
	}

}
