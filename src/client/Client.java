package client;
import client.Operation;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2812);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            OutputStream os = socket.getOutputStream();
            // Obtient un flux d'entrée à partir de la socket pour recevoir des données
            InputStream is = socket.getInputStream();
            // Envoyer un message au serveur
            pw.println("Je suis un client dans ce serveur");
            // Lire la réponse du serveur
            String serverResponse = br.readLine();
            System.out.println(serverResponse);
            int nb1;
            int nb2;
            String op;
            double res = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Donnez le premier nombre");
            nb1 = scanner.nextInt();
            System.out.print("Donnez le deuxieme nombre");
            nb2 = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Saisir un opérateur");
            op = scanner.nextLine();
            client.Operation op1 = new Operation(nb1, nb2, op);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);
            oos.writeObject(op1);
            Operation op2= (Operation) ois.readObject();
            System.out.println("La résultat est " + op2.getRes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
