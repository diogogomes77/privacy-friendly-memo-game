package org.secuso.privacyfriendlymemory;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.secuso.privacyfriendlymemory.util.TestUtils;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

public class ServerTest {

    private WebDriver mWebDriver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "XT1562");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
        capabilities.setCapability(CapabilityType.VERSION, "9");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "org.secuso.privacyfriendlymemory");
        capabilities.setCapability("appActivity", "org.secuso.privacyfriendlymemory.ui.MainActivity");

        mWebDriver =  new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void testToFail() {
        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.MEDIUM);
        mWebDriver.findElement(By.id("button1")).click();
        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.MEDIUM);
    }
}
