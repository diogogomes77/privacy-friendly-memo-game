package org.secuso.privacyfriendlymemory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.secuso.privacyfriendlymemory.util.Log;
import org.secuso.privacyfriendlymemory.util.TestUtils;
import org.secuso.privacyfriendlymemory.util.TestUtils.TimeoutWait;

import java.net.MalformedURLException;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class CustomTest {

    private static final String TAG = "CustomTest";

    private MobileDriver mWebDriver;

    @Before
    public void setUp() throws MalformedURLException {
        mWebDriver = TestUtils.setupTest();
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