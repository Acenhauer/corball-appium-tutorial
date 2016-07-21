package com.acenhauer.tests;

import com.perfectomobile.selenium.MobileDriver;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.acenhauer.mobile.WebUtil;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by guillem on 01/02/16.
 */
public class PerfectoExampleIOSTest {
    RemoteWebDriver driver;
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


    int deviceNum;

    @Before public void setUp() throws MalformedURLException {
        String host = "https://appytestcloud.bdigital.org/nexperience";
        MobileDriver dl = new MobileDriver(host, "juan.appytest@gmail.com", "Appytest");
        File app = new File("/Users/guillem/Desktop/Calculator.ipa");
        String REPOSITORY_KEY = "PUBLIC:Calculator.ipa";
        dl.uploadMedia(REPOSITORY_KEY, app);
        IMobileDevice device = dl.getDevice("572C512F4D50B736D46743F9E4E52B2D281451B6");
        device.installApplication(REPOSITORY_KEY);

        DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
        capabilities.setCapability("user", "juan.appytest@gmail.com");
        capabilities.setCapability("password", "Appytest");
        capabilities.setCapability("deviceName", "572C512F4D50B736D46743F9E4E52B2D281451B6");
        capabilities.setCapability("autoLaunch", true);
        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new IOSDriver(
            new URL("https://appytestcloud.bdigital.org/nexperience/perfectomobile/wd/hub"),
            capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
    }

    @Test public void perfectoTest() {
        // select the first number
        WebUtil.click(driver, locNumber1.get(deviceNum));
        // select the operator
        WebUtil.click(driver, locOperAdd.get(deviceNum));
        // select the second number
        WebUtil.click(driver, locNumber5.get(deviceNum));
        // click equals to calculate the result
        WebUtil.click(driver, locEquals.get(deviceNum));
        // verify the result
        WebElement resultElement =
            WebUtil.waitAndGetVisibleElement(driver, locResult.get(deviceNum));
        Assert.assertEquals("1 + 5 should be 6", 6, Integer.parseInt(resultElement.getText()));
    }


    @After public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
