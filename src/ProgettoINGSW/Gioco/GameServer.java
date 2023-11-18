
package ProgettoINGSW.Gioco;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
La classe GameServer è la classe che utilizziamo per definire la connessione sulla 
porta 1234 della socket e ascoltare le connessioni (dei due client da collegare) sulla
medesima porta.
*/
public class GameServer {

    private  ServerSocket serverSocket;
    private Socket socket;

    //Costruttore della classe in cui definiamo la connessione sulla porta 1234 della Socket del server
    public GameServer() {
        try {
            this.serverSocket = new ServerSocket(1234);
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo per accettare la connessione dei client
    protected void acceptConnections() {
        int i = 0;
        try {
            //Ascolta le connessioni (client da collegare) sulla porta 1234.
            while (!serverSocket.isClosed()) {
                socket = serverSocket.accept();
                
                i += 1;
                System.out.println("Un nuovo cliente si è connesso!");
                ClientHandler ClientHandler = new ClientHandler(socket);
                Thread thread = new Thread(ClientHandler);
                // Il metodo start avvia l'esecuzione di un thread.
                // Quando si chiama start() viene chiamato il metodo run della classe ClientHandler.
                thread.start();

            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }
    
    //Metodo che chiude la socket del server.
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
