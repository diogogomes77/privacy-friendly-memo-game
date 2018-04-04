package org.secuso.privacyfriendlymemory.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;

public class TestUtils {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TimeoutWait.SMALL,
            TimeoutWait.SMALL,
            TimeoutWait.MEDIUM,
            TimeoutWait.LONG})
    public @interface TimeoutWait {
        int SMALL = 1;
        int MEDIUM = 2;
        int LONG = 3;
    }

    public static void implicitWait(@NonNull WebDriver webDriver, @TimeoutWait int timeoutWait) {
        webDriver.manage().timeouts().implicitlyWait(timeoutWait, TimeUnit.SECONDS);
    }

    @SuppressWarnings("SameParameterValue")
    public static int getRandomNumber(int minor, int major) {
        Random r = new Random();
        return r.nextInt(major - minor) + minor;
    }

    @NonNull
    public static MobileDriver setupTest() throws MalformedURLException {
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
        return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}