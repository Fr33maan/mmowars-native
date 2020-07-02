package com.mmowars;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

public class ShareTest {
    public void test(UiDevice device) {
        try {
            device.findObject(new UiSelector().text("SHARE")).click(); // Share button on home

            // Test WhatsApp share
            device.findObject(new UiSelector().text("SHARE")).click(); // Share button on module page
            device.findObject(new UiSelector().text("WhatsApp")).click();
            device.pressBack();

            // Test Gmail share
            device.findObject(new UiSelector().text("SHARE")).click(); // Share button on module page
            device.findObject(new UiSelector().text("Gmail")).click();
            device.pressDPadDown(); // Close the keyboard which open automatically on gmail share

            // Not sure why need to press back twice but once wont go back
            device.pressBack();
            device.pressBack();

            // Finally go back to the page
            device.findObject(new UiSelector().text("BACK")).click();
        }catch(UiObjectNotFoundException e) {
            System.out.println(e);
        }
    }
}