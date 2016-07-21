package com.acenhauer.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by guillemhs on 2015-11-29.
 */
public class MobileBaseTest {
    AppiumDriver driver;

    @Before public void setUp() throws MalformedURLException {
        String device = System.getProperty("device");
        // switch between diffrent browsers, e.g. iOS Safari or Android Chrome
        // let's use the os name to differentiate, because we only use default browser in that os
        if (device != null && device.equalsIgnoreCase("Android")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Android Emulator");
            capabilities.setCapability("platformVersion", "5.0.0");
            capabilities.setCapability("app", "Browser");
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "iPhone 6");
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("platformVersion", "9.3");
            capabilities.setCapability("browserName", "safari");
            driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        }
    }

    @After public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
