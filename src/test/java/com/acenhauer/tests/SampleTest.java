package com.acenhauer.tests;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by guillemhs on 2015-11-16.
 */
public class SampleTest extends MobileBaseTest {
    /**
     * please run this test to make sure environment has been setup correctly
     */
    @Test public void firstTest() {
        driver.get("http://google.com");
        Assert.assertEquals("Title should be Google", "Google", driver.getTitle());
    }
}
