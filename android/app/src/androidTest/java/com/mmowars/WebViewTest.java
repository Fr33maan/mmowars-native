
package com.mmowars;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WebViewTest {
    public void test(UiDevice device) {
        try {
            device.findObject(new UiSelector().text("WEBVIEW")).click();

            // Webview content should exists
            UiObject gameTitle = device.findObject(new UiSelector().description("Container"));
            assertThat(gameTitle, notNullValue());

            // Wait for the game to fully load
            // @TODO self host the game and make it fullscreen
            Thread.sleep(8000);

            // Take a screenshot and check the color inside the webview
            // If its not that color it means the webview is unable to display the game
            Bitmap bitmap = InstrumentationRegistry.getInstrumentation().getUiAutomation().takeScreenshot();

            // @TODO once we changed the game for a faster loading version, change where we are looking
            int pixel = bitmap.getPixel(50, 450);
            int redValue = Color.red(pixel);
            int blueValue = Color.blue(pixel);
            int greenValue = Color.green(pixel);

            // Check the actual color - blue background of pixi demo
            // https://pixijs.io/examples/#/demos-basic/container.js
            assertEquals(16, redValue);
            assertEquals(153, greenValue);
            assertEquals(187, blueValue);

            // Go back to main screen
            device.findObject(new UiSelector().text("BACK")).click();
        }catch(UiObjectNotFoundException | InterruptedException e) {
            System.out.println(e);
        }
    }
}

