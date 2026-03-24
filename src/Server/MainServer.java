package Server;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("SERVER avviato sulla porta 3000");
        try {
            DatagramSocket dSocket = new DatagramSocket(3000);
            Scanner scanner = new Scanner(System.in);
            byte[] bufferIn = new byte[256];

            while (true) {
                DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);
                dSocket.receive(inPacket);
                String messaggioRicevuto = new String(inPacket.getData(), 0, inPacket.getLength());
                InetAddress clientAddress = inPacket.getAddress();
                int clientPort = inPacket.getPort();
                System.out.println("[CLIENT]: " + messaggioRicevuto);

                if (messaggioRicevuto.equalsIgnoreCase("stop")) {
                    System.out.println("Connessione chiusa dal client.");
                    break;
                }

                System.out.print("[SERVER - scrivi]: ");
                String messaggioInvio = scanner.nextLine();
                DatagramPacket outPacket = new DatagramPacket(
                        messaggioInvio.getBytes(), messaggioInvio.length(), clientAddress, clientPort
                );
                dSocket.send(outPacket);

                if (messaggioInvio.equalsIgnoreCase("stop")) {
                    System.out.println("Connessione chiusa dal server.");
                    break;
                }
            }

            dSocket.close();
            System.out.println("Socket chiusa.");

        } catch (BindException e) {
            System.err.println("Errore: porta già in uso");
        } catch (SocketException e) {
            System.err.println("Errore socket");
        } catch (IOException e) {
            System.err.println("Errore di I/O");
        }
    }
}