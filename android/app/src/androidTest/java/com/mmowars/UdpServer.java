package com.mmowars;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

import static org.junit.Assert.assertNull;

class UdpServer implements Runnable {
   public void run() {
      DatagramSocket socket;
      long startTime = new Date().getTime();

      try {
         System.out.println("Starting UDP server");
         socket = new DatagramSocket(4444);
         System.out.println("UDP server started");

         int requestReceived = 0;

         while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1000], 1000);
            socket.receive(request);

            if(requestReceived == 0){
               System.out.println("UDP Server received first message");
            }
            requestReceived++;


            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();

            DatagramPacket response = new DatagramPacket(request.getData(), 1000, clientAddress, clientPort);
            socket.send(response);

            if(Thread.interrupted()) {
               System.out.println("UDP Server interrupted - received an average of " + (requestReceived / ((new Date().getTime() - startTime) / 1000)) + " /s");
               break;
            }
         }

      } catch (IOException e) {
         assertNull(e);
      }


   }
}