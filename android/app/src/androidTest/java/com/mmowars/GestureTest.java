package com.mmowars;

import android.graphics.Bitmap;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GestureTest {
    int offset = 50;

    public void test(UiDevice device) {
        try {
            device.findObject(new UiSelector().text("GESTURE")).click();

            int height = device.getDisplayHeight();
            int width = device.getDisplayWidth();

            // See the JS code - 50 is half of the joystick size which is always in the left bottom corner
            device.drag(offset, height - offset, width / 2, height / 2, 10);

            // Take a screenshot and check the color at the place the joystick should be and the place the joystick was
            // We can check that the joystick has been moved
            Bitmap bitmap = InstrumentationRegistry.getInstrumentation().getUiAutomation().takeScreenshot();

            int red = -65536;
            int green = -16711936;

            // In the destination zone, we should see the joystick which is red
            int destinationPixel = bitmap.getPixel(width / 2, height / 2);
            assertEquals(red, destinationPixel);

            // In the init zone, we should see the green background
            int initialPixel = bitmap.getPixel(offset, height - offset);
            assertEquals(green, initialPixel);

            // NativeDriver should be correctly linked to JS callbacks
            String translateXText = device.findObject(new UiSelector().description("translateX")).getText();
            boolean translateXOver0 = Float.parseFloat(translateXText) > 0;
            assertTrue("translateX is not over 0 - translateX value: " + translateXText, translateXOver0);

            String translateYText = device.findObject(new UiSelector().description("translateY")).getText();
            boolean translateYUnder0 = Float.parseFloat(translateYText) < 0;
            assertTrue("translateY is not under 0 - translateY value: " + translateYText, translateYUnder0);

            // Finish event should have been triggered
            String finishedText = device.findObject(new UiSelector().description("translateFinished")).getText();
            assertEquals("TRANSLATE FINISHED", finishedText);

            // Go back on the menu
            device.findObject(new UiSelector().text("BACK")).click();

        }catch(UiObjectNotFoundException e) {
            assertNull(e);
        }
    }
}