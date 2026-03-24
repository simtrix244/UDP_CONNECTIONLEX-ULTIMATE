package Client;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            int port = 3000;
            DatagramSocket dSocket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            System.out.println("CLIENT avviato. Scrivi 'stop' per chiudere.");

            while (true) {
                System.out.print("[CLIENT - scrivi]: ");
                String messaggioInvio = scanner.nextLine();
                DatagramPacket outPacket = new DatagramPacket(
                        messaggioInvio.getBytes(), messaggioInvio.length(), serverAddress, port
                );
                dSocket.send(outPacket);

                if (messaggioInvio.equalsIgnoreCase("stop")) {
                    System.out.println("Connessione chiusa dal client.");
                    break;
                }

                byte[] bufferIn = new byte[256];
                DatagramPacket inPacket = new DatagramPacket(bufferIn, bufferIn.length);
                dSocket.receive(inPacket);
                String messaggioRicevuto = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("[SERVER]: " + messaggioRicevuto);

                if (messaggioRicevuto.equalsIgnoreCase("stop")) {
                    System.out.println("Connessione chiusa dal server.");
                    break;
                }
            }

            dSocket.close();
            System.out.println("Socket chiusa.");

        } catch (UnknownHostException e) {
            System.err.println("Errore DNS");
        } catch (SocketException e) {
            System.err.println("Errore Socket");
        } catch (IOException e) {
            System.err.println("Errore di I/O");
        }
    }
}