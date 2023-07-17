package UDP_ArtNet_DMX_API;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 
public class UDPsender {
    private String ipAddress;
    private int port;

    public UDPsender(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void sendArray(byte[] bytes) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(packet);
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendString(String message) {
        byte[] bytes = message.getBytes();
        sendArray(bytes);
    }
}
