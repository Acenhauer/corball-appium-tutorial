package com.acenhauer.tests;

import com.acenhauer.mobile.WebUtil;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

/**
 * Created by guillemhs on 2015-11-29.
 */
public class GmailTest extends MobileBaseTest {
    /**
     * please run this test to make sure environment has been setup correctly
     */
    final String password = "tecnocomappium";

    @Test public void gmailLoginTest() {
        driver.get("http://gmail.com");

        // this is because in Android, sometimes this page will display
        try {
            WebUtil.click(driver, By.linkText("Sign in"));
        } catch (TimeoutException tme) {
            System.out.println("ignore Sign in linkage not visible");
        }

        WebUtil.inputKeysIntoElement(driver, By.id("Email"), "appiumtecnocom@gmail.com");

        WebUtil.click(driver, By.id("next"));

        WebUtil.inputKeysIntoElement(driver, By.cssSelector("#Passwd"), password);

        WebUtil.click(driver, By.cssSelector("#signIn"));

        try {
            WebUtil.click(driver, By.linkText("sitio web de Gmail"));
        } catch (NoSuchElementException nse) {
            System.out.println("no mobile Gmail site linkage found");
        } catch (TimeoutException toe) {
            System.out.println("wait for linkage 'mobile Gmail site' timed out");
        }

        WebElement mobileComposeButton =
            WebUtil.waitAndGetVisibleElement(driver, By.cssSelector("div[aria-label='Compose']"));

        Assert.assertTrue(mobileComposeButton.isDisplayed());
    }
}
