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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
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
    private static final String PACKAGE_NAME = "com.mmowars";

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
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testGesture() {
        new GestureTest().test(device);
    }

    @Test
    public void testGradient() {
        new GradientTest().test(device);
    }

    // @TODO must find a way to pre install configured apps on simulators
    @Test
    public void testShare() {
        new ShareTest().test(device);
    }

    @Test
    public void testUdp() {
        new UdpTest().test(device);
    }

    @Test
    public void testWebview() {
        new WebViewTest().test(device);
    }
}