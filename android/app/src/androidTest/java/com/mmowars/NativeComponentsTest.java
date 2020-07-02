package com.mmowars;

// https://developer.android.com/reference/androidx/packages
// https://developer.android.com/reference/androidx/test/packages
import androidx.test.filters.SmallTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.uiautomator.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
// http://junit.sourceforge.net/javadoc/org/junit/Assert.html#assertThat(T,%20org.hamcrest.Matcher)

/**
 * Test native libraries to see if they are crashing the application
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class NativeComponentsTest {

    private UiDevice device;
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String BASIC_SAMPLE_PACKAGE = "com.mmowars.debug";
    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        device.pressHome();

        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the app
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testGesture() {
        try {
            UiObject moduleButton = device.findObject(new UiSelector().text("GESTURE"));
            moduleButton.click();

            UiObject backButton = device.findObject(new UiSelector().text("BACK"));
            backButton.click();
        }catch(UiObjectNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGradient() {
        try {
            UiObject moduleButton = device.findObject(new UiSelector().text("GRADIENT"));
            moduleButton.click();

            UiObject backButton = device.findObject(new UiSelector().text("BACK"));
            backButton.click();
        }catch(UiObjectNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testShare() {
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

    @Test
    public void testUdp() {
        try {
            UiObject moduleButton = device.findObject(new UiSelector().text("UDP"));
            moduleButton.click();

            UiObject backButton = device.findObject(new UiSelector().text("BACK"));
            backButton.click();
        }catch(UiObjectNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testWebview() {
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