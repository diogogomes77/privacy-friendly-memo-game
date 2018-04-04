package org.secuso.privacyfriendlymemory.util;

import org.openqa.selenium.WebDriver;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        int VERY_LONG = 5;
    }

    public static void implicitWait(@NonNull WebDriver webDriver, @TimeoutWait int timeoutWait) {
        webDriver.manage().timeouts().implicitlyWait(timeoutWait, TimeUnit.SECONDS);
    }

    public static int getRandomNumber(int minor, int major) {
        Random r = new Random();
        return r.nextInt(major - minor) + minor;
    }
}