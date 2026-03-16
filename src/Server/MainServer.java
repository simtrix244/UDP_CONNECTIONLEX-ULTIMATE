package Server;

import java.io.IOException;
import java.net.*;

public class MainServer {
    public static void main(String[] args) throws SocketException {
        System.out.println("SERVER");

        try {
            DatagramSocket dSocket = new DatagramSocket(3000);


            //ricevo il messaggio
            byte[] bufferIn = new byte[256];
            DatagramPacket dpi = new DatagramPacket(bufferIn, bufferIn.length);
            dSocket.receive(dpi);
            System.out.println("ricezione messaggio effettuata");

            String messaggio = new String(dpi.getData(),dpi.getLength());


            InetAddress clientAdress = dpi.getAddress();
            int port = dpi.getPort();
            DatagramPacket outPacket = new DatagramPacket(messaggio.getBytes(), messaggio.length(),clientAdress,port);
            dSocket.send(outPacket);

            System.out.println("messaggio inviato");

        } catch (BindException e) {

            System.err.println("Errore porta già in uso");
        }
        catch (SocketException e){
            System.err.println("errore socket");
        }
        catch(IOException e){
            System.err.println("Errore di I/O");
        }


    }



}

