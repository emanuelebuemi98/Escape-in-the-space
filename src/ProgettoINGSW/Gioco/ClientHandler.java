package ProgettoINGSW.Gioco;

// 1. Aprimo una socket.
// 2. Apriamo un flusso di input e un flusso di output sul socket.
// 3. Leggiamo e scrivi nel flusso in base al protocollo del server.
// 4. Chiudiamo i flussi.
// 5. Chiudiamo la socket.

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * La classe ClientHandler permette di gestire la comunicazione tra più client.
 * Quando un client si connette, il server genera un thread per gestire il client.
 * In questo modo il server può gestire più client contemporaneamente.
 */

// L'inetrfaccia Runnable è implementato su una classe le cui istanze verranno eseguite da un thread.
public final class ClientHandler implements Runnable {

    // L'Array list serve per eseguire tutti i thread che gestiscono i client in modo che ogni messaggio 
    // possa essere inviato al client che il thread sta gestendo.
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    
    // Definiamo la Socket serve per una connessione, il buffer di lettura e 
    // scrittura che servono rispettivamente per la ricezione e l'invio dei dati.
    private Socket socket;
    public BufferedReader bufferedReader;
    public BufferedWriter bufferedWriter;
    private String clientUsername;
    private String  score;

    // Creiamo il gestore client che passeremo alla classe GameServer
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // Quando un client si connette, viene inviato il suo username.
            this.clientUsername = bufferedReader.readLine();
            this.score = "-1";
            
            System.out.print("IL client si è connesso "+this.clientUsername);
            
            // Viente aggiunto il nuovo gestore client all'array in modo che possano ricevere messaggi da altri.
            clientHandlers.add(this);
            broadcastMessage("SERVER: " + clientUsername + " è entrato nel gioco!");
            // Viene controllato che il numero di partecipanti sia uguale a 2. 
            // Quando i player diventano 2 parte il gioco
            if(clientHandlers.size() == 2){
                broadcastMessage("GO");
            }
            
        } catch (IOException e) {
            // chiudiamo tutto
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /* 
      Tutto in questo metodo viene eseguito su un thread separato. 
      Vogliamo ascoltare i messaggi su un thread separato perché l'ascolto (bufferedReader.readLine()) 
      è un'operazione di blocco.
      Un'operazione di blocco significa che il chiamante attende che il chiamato termini la sua operazione. 
    */
    @Override
    public void run() {
        String messageFromClient;
        String user;
        String classifica = "";
        Boolean send;
        
        // Il bufferedReader continua ad ascoltare i messaggi finchè c'è ancora stabilita una connessione con il client.
        while (socket.isConnected()) {
            send = true;
            try {
                // Prima viene letto cosa ha inviato il client e poi il broadcastMessage lo invia a tutti gli altri client.
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
                System.out.print(messageFromClient);
                
                //Se il messaggio ricevuto è KO viene inviato il meggaggio con il nome utente e il punteggio
                if(messageFromClient.startsWith("KO")){
                    System.out.print("\nho ricevuto KO" );
                    user = messageFromClient.split(";")[1];
                    score = messageFromClient.split(";")[2];
                    System.out.print("\n"+user + score );
                    for (ClientHandler clientHandler : clientHandlers) {
                        System.out.print("\n"+clientHandler.clientUsername );
                        if (clientHandler.clientUsername.equals(user) ){
                            clientHandler.score = score;
                        }
                    }
                    
                    for (ClientHandler clientHandler : clientHandlers) {
                        System.out.print(clientHandler.score);
                        if (clientHandler.score.equals("-1") ){
                            send = false;
                        }
                    }
                    if(send){
                        Collections.sort(clientHandlers, new Comparator<ClientHandler>(){
                            @Override
                            //Confrontiamo i punteggi dei due player
                            public int compare(ClientHandler e1, ClientHandler e2){
                                return e2.score.compareTo(e1.score);
                            }
                        });
                        
                        int i=1;
                        //Stabiliamo il vincitore
                        String winner = "";
                        for (ClientHandler clientHandler : clientHandlers) {
                            if (i == 1){
                                winner = clientHandler.clientUsername;
                            }
                            classifica += i+") "+clientHandler.clientUsername + " " + clientHandler.score + "<br>";
                            i++;
                           
                        }
                        
                        System.out.println(classifica);
                        broadcastMessage("END;"+winner+"/"+classifica);
                        System.exit(0); 
                    }
                }
                
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /* 
    Il metodo permette di inviare un messaggio attraverso ogni thread del clienthandler in modo che 
    tutti ricevano il messaggio.
    Fondamentalmente ogni clienthandler (gestore client) è una connessione a un client. 
    Quindi, per qualsiasi messaggio ricevuto, scorre ogni connessione con il client e lo invia.
    */
    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    /*
      Se il client si disconnette per qualsiasi motivo, viene rimuosso dall'elenco in modo tale che
      il messaggio non venga inviato a una connessione interrotta.
    */
    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUsername + " ha abbandonato il gioco!");
    }

    // Metodo di supporto per chiudere tutto.
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Il client si è disconnesso o si è verificato un errore, quindi 
        // lo rimuoviamo dall'elenco in modo che nessun messaggio venga trasmesso.
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
