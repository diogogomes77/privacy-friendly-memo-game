package org.secuso.privacyfriendlymemory;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.secuso.privacyfriendlymemory.util.TestUtils;

import java.net.MalformedURLException;

import io.appium.java_client.MobileDriver;

import static org.junit.Assert.assertEquals;

public class FailingTest {

    private MobileDriver mWebDriver;

    @Before
    public void setUp() throws MalformedURLException {
        mWebDriver = TestUtils.setupTest();
    }

    @Test
    public void testToFail() {
        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.MEDIUM);
        mWebDriver.findElement(By.id("button1")).click();
        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.MEDIUM);

        mWebDriver.findElement(By.id("invalidID")).click();
    }

    @Test
    public void playSimpleGame1() {
        // Test with explicit wait

        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.MEDIUM);
        mWebDriver.findElement(By.id("button1")).click();

        WebDriverWait wait = new WebDriverWait(mWebDriver, 10);
        WebElement playButton = wait.until( ExpectedConditions.presenceOfElementLocated(By.id("playButton")) );

        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.MEDIUM);
        playButton.click();
        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.LONG);
    }

    @Test
    public void playSimpleGame2() {
        // Test with jUnit annotation

        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.MEDIUM);
        mWebDriver.findElement(By.id("button1")).click();
        WebElement playButton = mWebDriver.findElement(By.id("playButton"));
        assertEquals("Novo jogo", playButton.getText());

        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.MEDIUM);
        playButton.click();
        TestUtils.implicitWait(mWebDriver, TestUtils.TimeoutWait.LONG);
    }
}