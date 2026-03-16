package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

public class MainClient {
    public static void main(String[] args) {
        try {
            InetAddress serverAdress = InetAddress.getLocalHost();
            String messaggio = "ciao";
            int port = 3000;
            //scelta primitiva socket -> operazione di bind implicita
            DatagramSocket dSocket = new DatagramSocket();
            //costruzione del pacchetto di dati
            System.out.println("primitiva socket lato client realizzata");
            DatagramPacket outPacket = new DatagramPacket(messaggio.getBytes(),messaggio.length(), serverAdress, port);
            //invio dei dati
            dSocket.send(outPacket);
            System.out.println("Pacchetto di dati inviato al SERVER");

            byte[] bufferIn = new byte[256];
            DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);
            dSocket.receive(inPacket);
            System.out.println("ricezione messaggio effettuata" + inPacket);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
