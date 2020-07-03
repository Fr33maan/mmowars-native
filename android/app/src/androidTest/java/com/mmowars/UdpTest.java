package com.mmowars;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UdpTest {
    public void test(UiDevice device) {
        try {

            // Create a new thread to start the UDP server
            ThreadGroup threadGroup = new ThreadGroup("UDP Server");
            Thread udpServerThread = new Thread(threadGroup , new UdpServer());
            udpServerThread.start();

            Thread.sleep(2000);

            System.out.println("opening UDP module");

            // Then navigate to the Udp test module
            UiObject moduleButton = device.findObject(new UiSelector().text("UDP"));
            moduleButton.click();

            // Wait for the client and server to exchange messages - 30 seconds
            Thread.sleep(5000);
            System.out.println("UDP Server - Waited 5000ms, waiting more...");
            Thread.sleep(5000);
            System.out.println("UDP Server - Waited 5000ms, waiting more...");
            Thread.sleep(5000);
            System.out.println("UDP Server - Waited 5000ms, waiting more...");
            Thread.sleep(5000);
            System.out.println("UDP Server - Waited 5000ms, waiting more...");
            Thread.sleep(5000);
            System.out.println("UDP Server - Waited 5000ms, waiting more...");
            Thread.sleep(5000);
            System.out.println("UDP Server - Waited 30s, checking results");

            // Interrupt the thread
            threadGroup.interrupt();
            System.out.println("UDP Server thread interrupted");

            // Get the last incremented message from the View
            String messagesCount = device.findObject(new UiSelector().description("responsesCount")).getText();
            boolean messagesSentGreaterThan60FPS = Integer.parseInt(messagesCount) > 1800;

            // We should have a huge amount of message and 0 messages lost
            assertTrue("Messages count is not over 60FPS - messagesCount: "+ messagesCount, messagesSentGreaterThan60FPS);

            // Leave the testModule
            UiObject backButton = device.findObject(new UiSelector().text("BACK"));
            backButton.click();

        }catch(UiObjectNotFoundException | InterruptedException e) {
            assertNull(e);
        }
    }
}