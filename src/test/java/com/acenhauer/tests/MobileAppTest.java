package com.acenhauer.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by guillemhs on 2015-11-29.
 */
public class MobileAppTest {
    AppiumDriver driver;
    final String[] number1 = {"net.tecnotopia.SimpleCalculator:id/button1", "1"};
    final String[] operAdd = {"net.tecnotopia.SimpleCalculator:id/buttonAdd", "+"};
    final String[] number5 = {"net.tecnotopia.SimpleCalculator:id/button5", "5"};
    final String[] equals = {"net.tecnotopia.SimpleCalculator:id/buttonEquals", "="};
    final String[] result = {"net.tecnotopia.SimpleCalculator:id/display",
            "//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"};

    final List<By> locNumber1 = new ArrayList<>();
    final List<By> locOperAdd = new ArrayList<>();
    final List<By> locNumber5 = new ArrayList<>();
    final List<By> locEquals = new ArrayList<>();
    final List<By> locResult = new ArrayList<>();

    String device;
    int deviceNum;

    @Before
    public void setUp() throws MalformedURLException {
        // by default, start android app
        device = "ios";
        deviceNum = 0;
        // try to set according to user requirement
        if (System.getProperty("device") != null) {
            device = System.getProperty("device");
        } else {
            System.out.println("device not defined, will use default = Android");
        }
        deviceNum = device.equalsIgnoreCase("Android") ? 0 : 1;

        locNumber1.add(By.id(number1[0]));
        locNumber1.add(By.name(number1[1]));
        locOperAdd.add(By.id(operAdd[0]));
        locOperAdd.add(By.name(operAdd[1]));
        locNumber5.add(By.id(number5[0]));
        locNumber5.add(By.name(number5[1]));
        locEquals.add(By.id(equals[0]));
        locEquals.add(By.name(equals[1]));
        locResult.add(By.id(result[0]));
        locResult.add(By.xpath(result[1]));

        // switch between diffrent browsers, e.g. iOS Safari or Android Chrome
        // let's use the os name to differentiate, because we only use default browser in that os
        if (device != null && device.equalsIgnoreCase("Android")) {
            File appDir = new File("src/test/resources");
            File app = new File(appDir, "SimpleCalculator.apk");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName","Android Emulator");
            capabilities.setCapability("deviceType","phone");
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("browserName", "");
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("app", app.getAbsolutePath());
            capabilities.setCapability("platformVersion", "6.0");
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } else {
            File appDir = new File("src/test/resources");
            File app = new File(appDir, "Calculator.app");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appiumVersion", "1.5.3");
            capabilities.setCapability("deviceName", "iPhone 6");
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("platformVersion", "9.3");
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("browserName", "");
            capabilities.setCapability("app", app.getAbsolutePath());
            driver = new IOSDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
