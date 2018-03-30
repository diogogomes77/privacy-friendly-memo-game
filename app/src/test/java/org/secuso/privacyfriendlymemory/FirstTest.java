package org.secuso.privacyfriendlymemory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private WebDriver mWebDriver;

    @Before
    public void setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "4_WVGA_Nexus_S_API_23");

        // Set BROWSER_NAME desired capability. It's Android in our case here.
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability("platformName", "Android");

        // Set android appPackage desired capability. It is
        // com.android.calculator2 for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appPackage", "org.secuso.privacyfriendlymemory");

        // Set android appActivity desired capability. It is
        // com.android.calculator2.Calculator for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "org.secuso.privacyfriendlymemory.ui.MainActivity");

      //  capabilities.setCapability("autoAcceptAlerts",true);
        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in android device.
        mWebDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        mWebDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


    }

    @Test
    public void testFirstSplashActivity() {


        // Click on DELETE/CLR button to clear result text box before running test.
       // mWebDriver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        mWebDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        mWebDriver.findElement(By.id("button1")).click();
        mWebDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        mWebDriver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        mWebDriver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(5).click();
        mWebDriver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(6).click();
        mWebDriver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(7).click();
        mWebDriver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(8).click();
        mWebDriver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        // Click on number 2 button.
        //mWebDriver.findElement(By.name("playButton")).click();
        //mWebDriver.findElement(By.id("playButton")).click();

        mWebDriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    @After
    public void End() {
        if (mWebDriver != null) {
            mWebDriver.quit();
        }
    }
}

