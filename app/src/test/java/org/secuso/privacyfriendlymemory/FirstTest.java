package org.secuso.privacyfriendlymemory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.secuso.privacyfriendlymemory.util.Log;
import org.secuso.privacyfriendlymemory.util.TestUtils;
import org.secuso.privacyfriendlymemory.util.TestUtils.TimeoutWait;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class FirstTest {

    private static final String TAG = "FirstTest";

    private MobileDriver mWebDriver;

    @Before
    public void setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability("deviceName", "XT1562");

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
        capabilities.setCapability("appActivity", "org.secuso.privacyfriendlymemory.ui.SplashActivity");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in android device.
        mWebDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        TestUtils.implicitWait(mWebDriver, TimeoutWait.VERY_LONG);
    }

    @Test
    public void testFirstCalculator() {
        TestUtils.implicitWait(mWebDriver, TimeoutWait.MEDIUM);
        mWebDriver.findElement(By.id("button1")).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);
        mWebDriver.findElements(By.xpath("//android.widget.Button")).get(0).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);

        playGame();
    }

    @Test
    public void playCustomGame() {
        TestUtils.implicitWait(mWebDriver, TimeoutWait.MEDIUM);
        mWebDriver.findElement(By.id("button1")).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.MEDIUM);

        // Click on menu
        mWebDriver.findElements(By.id("Open navigation drawer")).get(0).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.MEDIUM);

        // Deck choice
        mWebDriver.findElements(By.xpath("//android.widget.CheckedTextView")).get(3).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);

        // Define image
        mWebDriver.findElements(By.className("android.widget.LinearLayout")).get(14).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.SMALL);

        // Click OK button
        mWebDriver.findElement(By.id("button1")).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.SMALL);

        // Click OK button
        mWebDriver.findElement(By.id("icon")).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);

        // Long press gallery image
        WebElement galleryImage = mWebDriver.findElement(By.xpath("//android.widget.ImageView"));
        TouchAction action = new TouchAction(mWebDriver);
        action.longPress(galleryImage).release().perform();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.SMALL);

        mWebDriver.findElements(By.id("More options")).get(0).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.SMALL);

        mWebDriver.findElements(By.id("title")).get(0).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.SMALL);

        mWebDriver.findElement(By.id("Open")).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.SMALL);

        mWebDriver.findElements(By.xpath("//android.widget.CheckBox")).get(2).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);

        ((AndroidDriver) mWebDriver).pressKeyCode(AndroidKeyCode.BACK);
        TestUtils.implicitWait(mWebDriver, TimeoutWait.MEDIUM);

        WebElement ratingBar = mWebDriver.findElement(By.id("difficultyBar"));

        // Get start point
        int startX = ratingBar.getLocation().getX();

        // Get end point
        int endX = ratingBar.getSize().getWidth();

        // Get vertical location
        int posY = ratingBar.getLocation().getY();

        Log.d(TAG, "rating bar: start=" + startX + ", end=" + endX + ", posY=" + posY );

        TouchAction act = new TouchAction(mWebDriver);

        // Set rating bar to max
        act.press(endX, posY).release().perform();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.SMALL);

        // Click new game

        mWebDriver.findElement(By.id("playButton")).click();
        playGameUntilEnd();
    }

    @After
    public void End() {
        if (mWebDriver != null) {
            mWebDriver.quit();
        }
    }

    private void playGame() {
        mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(5).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);
        mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(6).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);
        mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(7).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);
        mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(8).click();
        TestUtils.implicitWait(mWebDriver, TimeoutWait.LONG);
    }

    private void playGameUntilEnd() {
        while (true) {
            WebElement gridView = mWebDriver.findElement(By.id("gridview"));
            if ("FINISHED".equals(gridView.getAttribute("name"))) {
                return;
            }

            int randomPos = TestUtils.getRandomNumber(0, 35);
            mWebDriver.findElements(By.xpath("//android.widget.ImageView")).get(randomPos).click();
        }
    }
}