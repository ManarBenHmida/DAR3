package server;
import client.Operation;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public static void main(String[] args) {
        new Server().start();
    }

    @Override
    public void run() {
        System.out.println("Je suis un serveur connecté");
        try {
            ServerSocket soket = new ServerSocket(2812);
            System.out.println("J'attends un client");
            int index = 1;

            while (true) {
                Socket Socketclient = soket.accept();
                new ClientProcess(Socketclient, index).start();
                System.out.println("Le client est connecté");
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ClientProcess extends Thread {
    private Socket socket;
    private int index;

    public ClientProcess(Socket s, int indice) {
      socket = s;
       index = indice;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            String Messageclient = br.readLine();
            System.out.println("Le message du client est " + index + ": " + Messageclient);

            // Réponse au client
            String r = "Bienvenue, vous êtes le client " + index + ". Votre @IP: " + socket.getRemoteSocketAddress();
            pw.println(r);

            OutputStream os = socket.getOutputStream(); // Obtient un flux de sortie
            InputStream is = socket.getInputStream(); // Obtient un flux d'entrée
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);

           client.Operation op1 = (Operation) ois.readObject();
            int nb1 = op1.getNb1();
            int nb2 = op1.getNb2();
            String op = op1.getOp();
            double res = 0;

            if (op.equals("+")) {
                res = nb1 + nb2;

            } else if (op.equals("-")) {
                res = nb1 - nb2;

            } else if (op.equals("*")) {
                res = nb1 * nb2;

            } else if (op.equals("/")) {
                if (nb2 != 0) {
                    res = (double) nb1 / nb2;

                } else {
                    System.out.println("Impossible de diviser par zéro!");
                }
            }
            op1.setRes(res);

            // Envoie le résultat au client
            oos.writeObject(op1);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }}