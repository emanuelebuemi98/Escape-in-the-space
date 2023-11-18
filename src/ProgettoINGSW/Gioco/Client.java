package ProgettoINGSW.Gioco;

import java.io.*;
import java.net.Socket;

/*
Un client invia messaggi al server, il server genera un thread per comunicare con il client.
Ogni comunicazione con un client viene aggiunta a un array list in modo che qualsiasi messaggio inviato 
venga inviato a tutti gli altri client scorrendo.
*/
public class Client {

    // Un client utilizza una socket per connettersi al server, un bufferreader e un bufferwriter 
    // rispettivamente per ricevere e inviare messaggi.
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private PlayerClient pc;

    public Client(PlayerClient pc, Socket socket, String username) {
        try {
            this.pc = pc;
            this.socket = socket;
            this.username = username;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            // chiudiamo tutto
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    // L'invio di un messaggio non si blocca e può essere eseguito senza generare un thread, a differenza dell'attesa di un messaggio.
    public void sendMessage(String msg) {
        try {
                // Inizialmente invia lo username del client.
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
        } catch (IOException e) {
            // chiudiamo tutto
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    //Il primo player una volta inserito il suo nickname rinarrà in attesa del messaggio di go
    public void waitStart(int i) {
        String msg = "";
        try {
            // Inizialmente invia lo username del client.
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("In attesa di go");
            //una volta che il player 2 avrà inserito il suo nickname inizierà la partita
            while(true){ 
                msg = bufferedReader.readLine();
                System.out.println("Ho letto :"+ msg);
                System.out.println(msg);
                if("GO".equals(msg)){
                    return;
                }
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    // Metodo per leggere il messaggio di END che determinerà la fine della partita
    public String waitEnd() {
        String msg = "";
        try {    
            System.out.println("In attesa di END");
            while(true){ 
                 msg = bufferedReader.readLine();
                 System.out.println("Ho letto :"+ msg);
                 System.out.println(msg);
                 if(msg.startsWith("END")){
                     return (msg.split(";")[1]);
                 }
             } 

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        return "";
    }

    // Metodo di supporto per chiudere tutto (i due baffer e la socket)
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
