package com.acenhauer.tests;

import com.acenhauer.mobile.WebUtil;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

/**
 * Created by guillemhs on 2015-12-28.
 */
public class CalculatorTest extends MobileAppTest {
    @Test
    public void addShouldWork() throws InterruptedException {
        // select the first number
        WebUtil.click(driver, locNumber1.get(deviceNum));
        // select the operator
        WebUtil.click(driver, locOperAdd.get(deviceNum));
        // select the second number
        WebUtil.click(driver, locNumber5.get(deviceNum));
        // click equals to calculate the result
        WebUtil.click(driver, locEquals.get(deviceNum));
        // verify the result
        WebElement resultElement = WebUtil.waitAndGetVisibleElement(driver, locResult.get(deviceNum));
        Assert.assertEquals("1 + 5 should be 6", 6, Integer.parseInt(resultElement.getText()));
    }
}
