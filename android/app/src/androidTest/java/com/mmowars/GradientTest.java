package com.mmowars;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import static org.junit.Assert.assertNull;

public class GradientTest {
    public void test(UiDevice device) {
        try {
            UiObject moduleButton = device.findObject(new UiSelector().text("GRADIENT"));
            moduleButton.click();

            UiObject backButton = device.findObject(new UiSelector().text("BACK"));
            backButton.click();
        }catch(UiObjectNotFoundException e) {
            assertNull(e);
        }
    }
}