package Client;

import java.io.IOException;
import java.net.*;

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
            DatagramPacket outPacket = new DatagramPacket(messaggio.getBytes(), messaggio.length(), serverAdress, port);
            //invio dei dati
            dSocket.send(outPacket);
            System.out.println("Pacchetto di dati inviato al SERVER");

            byte[] bufferIn = new byte[256];
            DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);
            dSocket.receive(inPacket);
            System.out.println("ricezione messaggio effettuata" + inPacket);

            dSocket.close();
            System.out.println("comunicazione chiusa!");

        } catch (UnknownHostException e) {
            System.err.println("Errore DNS");
        } catch (SocketException e) {
            System.err.println("Errore Socket");
        } catch (IOException e) {
            System.err.println("Errore di I/0");


        }


    }
}
