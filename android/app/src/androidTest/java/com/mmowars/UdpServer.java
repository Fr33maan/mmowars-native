package com.mmowars;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UdpServer implements Runnable {
   public void run() {
      DatagramSocket socket;
      try {
         socket = new DatagramSocket(4444);

         while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1000], 1000);
            socket.receive(request);

            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();

            DatagramPacket response = new DatagramPacket(request.getData(), 1000, clientAddress, clientPort);
            socket.send(response);
         }

      } catch (IOException e) {
         e.printStackTrace();
      }


   }
}