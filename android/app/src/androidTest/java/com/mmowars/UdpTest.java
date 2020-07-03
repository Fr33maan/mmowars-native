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

            // Then navigate to the Udp test module
            UiObject moduleButton = device.findObject(new UiSelector().text("UDP"));
            moduleButton.click();

            // Wait for the client and server to exchange messages
            Thread.sleep(3000);

            // Get the last incremented message from the View
            UiObject messagesCount = device.findObject(new UiSelector().description("responsesCount"));
            boolean messagesSentGreaterThan1500 = Integer.parseInt(messagesCount.getText()) > 1500;

            // We should have a huge amount of message and 0 messages lost
            assertTrue("Messages count is not over 1500 - messagesCount: "+ messagesCount, messagesSentGreaterThan1500);

            // Leave the testModule
            UiObject backButton = device.findObject(new UiSelector().text("BACK"));
            backButton.click();

            // Interrupt the thread
            threadGroup.interrupt();

        }catch(UiObjectNotFoundException | InterruptedException e) {
            assertNull(e);
        }
    }
}