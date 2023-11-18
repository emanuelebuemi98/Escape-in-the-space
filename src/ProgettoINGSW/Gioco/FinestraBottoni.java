
package ProgettoINGSW.Gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class FinestraBottoni {
    

    final JFrame finestraGioco;
    
    private final int width=700;
    private final int higth=450;
    private URL url;
    private final JLabel labLogoNomeGioco;
    private final JLabel labUser;
    private final JLabel labUsername;
    private final JLabel labPunti;
    private final JButton btnStart;  
    private final JButton btnIstruzioni; 
    private final JButton btnAudio; 
    private boolean audioAttivo = false;  
    private RiproduciSuono audioPlayer;
    private final JButton btnClassifica; 
    private final JButton btnLogout;
    private final JTextField edtCommenti;
    private final JButton btnAggiungi;
    private final String us;
    private JTable tbRecord;
    
    public FinestraBottoni(String username){

        // Creiamo la finestra di gioco contenete i bottoni 
        finestraGioco = new JFrame();
        
        finestraGioco.setSize(700,450); // Impostiamo la dimensione del frame        
        finestraGioco.setTitle("Escape in the space"); // Definiamo il nome del gioco            
        us=username;
        
        // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try {
            url = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            finestraGioco.setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
        }
        
        //Definiamo l'immagine di sfondo
        finestraGioco.setLayout(new BorderLayout());
        finestraGioco.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\sfondoRazzoSpazio.gif")));
   
        // Imponiamo il gestore di layout uguale a null per poter posizionare le componenti in modo manuale con il setBounds
        finestraGioco.setLayout(null); 
        
        // Etichetta per il titolo del gioco con l'immagine  
        labLogoNomeGioco = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\nomeG.png"));
        labLogoNomeGioco.setBounds(150, 7, 400, 100);
        finestraGioco.add(labLogoNomeGioco);
        
        //Etichette che definiscono l'username dell'utente e il suo punteggio massimo raggiunto
        labUser = new JLabel();
        labUser.setText("Utente: ");
        labUser.setFont(new java.awt.Font("Tahoma", 1, 13));
        labUser.setForeground(Color.ORANGE);
        labUser.setBounds(10, 10, 100, 30);
        finestraGioco.add(labUser);
        
        labUsername = new JLabel();
        labUsername.setText(username);
        labUsername.setFont(new java.awt.Font("Tahoma", 1, 13));
        labUsername.setForeground(Color.ORANGE);
        labUsername.setBounds(80, 10, 100, 30);
        finestraGioco.add(labUsername);
        
        labPunti = new JLabel();
        labPunti.setText("Record punteggio = " + visualizzaTuoPunteggioMax());
        labPunti.setFont(new java.awt.Font("Tahoma", 1, 13));
        labPunti.setForeground(Color.ORANGE);
        labPunti.setBounds(200, 10, 200, 30);
        finestraGioco.add(labPunti);
        
        // Definiamo i 5 bottoni 
        btnStart = new JButton("PARTITA SINGOLA"); 
        btnStart.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnStart.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnStart.setForeground(Color.BLACK);
        btnStart.setBounds(220, 90, 170, 30);
        btnStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStart.setBackground(Color.CYAN);
        finestraGioco.add(btnStart);        
        
        btnIstruzioni = new JButton("ISTRUZIONI");
        btnIstruzioni.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnIstruzioni.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnIstruzioni.setForeground(Color.BLACK);
        btnIstruzioni.setBounds(220, 140, 170, 30);
        btnIstruzioni.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIstruzioni.setBackground(Color.CYAN);
        finestraGioco.add(btnIstruzioni);
        
        btnAudio = new JButton("AUDIO OFF");
        btnAudio.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnAudio.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnAudio.setForeground(Color.BLACK);
        btnAudio.setBounds(220, 190, 170, 30);
        btnAudio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAudio.setBackground(Color.CYAN);
        finestraGioco.add(btnAudio);
        
        btnClassifica = new JButton("CLASSIFICA");
        btnClassifica.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnClassifica.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnClassifica.setForeground(Color.BLACK);
        btnClassifica.setBounds(220, 240, 170, 30);
        btnClassifica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClassifica.setBackground(Color.CYAN);
        finestraGioco.add(btnClassifica);
        
        btnLogout = new JButton("ESCI"); 
        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnLogout.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnLogout.setForeground(Color.BLACK);
        btnLogout.setBounds(220, 290, 170, 30);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.setBackground(Color.CYAN);
        finestraGioco.add(btnLogout);
        
        //Definiamo la TextField e il bottone per l'inserimento del commento
        edtCommenti = new JTextField();
        edtCommenti.setBounds(210, 360, 330, 30);
        finestraGioco.add(edtCommenti);
       
        btnAggiungi = new JButton("AGGIUNGI COMMENTO");
        btnAggiungi.setFont(new java.awt.Font("Tahoma", 1, 12));
        btnAggiungi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnAggiungi.setForeground(Color.BLACK);
        btnAggiungi.setBounds(30, 360, 170, 30);
        btnAggiungi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAggiungi.setBackground(Color.CYAN);
        finestraGioco.add(btnAggiungi);
        
        
        // Per definire un gestore di eventi abbiamo implementato l’interfaccia ActionListener.
        // L'ActionListener implementa un metodo chiamato actionPerformed; questo metodo in base all'evento che si verifica  
        // risponde a quel determinato evento associando il rispettivo comportamento. 
        // Questi eventi di tipo ActionEvent sono eventi che consistono nel compiere un’azione nella GUI da parte dell’utente. 
        // Gli eventi implementati relativi ai 6 bottoni sono:
        // - Evento bottone "PARTITA SINGOLA" ---> permette all'utente di iniziare a giocare 
        // - Evento bottone "ISTRUZIONI" ---> fornisce all'utente un pannello con le istruzioni su come giocare al gioco
        // - Evento bottone "AUDIO OFF" ---> fornisce all'utente la possibilità di scegliere prima di iniziare il gioco se attivare l'audio
        // - Evento bottone "CLASSIFICA" ---> permette all'utente di visualizzare i migliori 5 risultati del gioco 
        // - Evento bottone "ESCI" ---> permette all'utente di uscire dal gioco 
        // - Evento bottone "AGGIUNGI COMMENTO" ---> permette all'utente di inserire una recensione sul gioco 
        ActionListener a = new ActionListener(){
        // Istanziamo una nuova finestra di gioco che si creerà al click del tasto PARTITA SINGOLA che conterrà la schermata del gioco
        JFrame finestra = new JFrame(); 
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Restituisce la componente dell'oggetto o su cui si è verificato l'evento e
            Object o = evt.getSource(); 
            
            // - Evento bottone "PARTITA SINGOLA"
            if (o==btnStart){ 
                // Istanziamo un oggetto di tipo Spazio a cui passiamo nel costruttore la dimensione della finestra di gioco
                Spazio spazio = new Spazio(width,higth,us); 
     
             /* RENDO LA FINESTRA IN CUI C'E' IL GIOCO VISIBILE.
                ABBIAMO CREATO UNA FUNZIONE creaFinestra CHE APPLICA TUTTI I PARAMETRI
                PER FAR VEDERE LA FINESTRA */
                finestra = creaFinestraDiInizioGioco(finestra); 
                finestra.add(spazio);
                spazio.setVisible(true);
                spazio.play(); // facciamo partire il gioco
                // quando il gioco parte la finestra dei bottoni scompare (la rendiamo non visibile)
                finestraGioco.setVisible(false); 
            }
            
            
            // - Evento bottone "ISTRUZIONI"
            if (o==btnIstruzioni){
                IstruzioniGioco();
            }
            
            // - Evento bottone "AUDIO OFF"
            if (o == btnAudio) {
                if (audioAttivo) {
                    btnAudio.setText("AUDIO OFF");
                    audioAttivo = false;
                    // codice per fermare la riproduzione dell'audio
                    if (audioPlayer != null) {
                        audioPlayer.stop();
                    }
                } else {
                    btnAudio.setText("AUDIO ON");
                    audioAttivo = true;
                    // codice per iniziare la riproduzione dell'audio
                    if (audioPlayer == null) {
                        audioPlayer = new RiproduciSuono("JailHouseRock.wav");
                    }
                    audioPlayer.start();
                }
            }
            /*if (o==btnAudio){
                boolean premuto = false;
                if(premuto==false){
                    premuto=true;
                    btnAudio.setText("AUDIO ON");
                    new RiproduciSuono("JailHouseRock.wav").start();
                }
            }*/
            
            // - Evento bottone "CLASSIFICA"
            if (o==btnClassifica){
                MostraRecordActionPerformed(evt);
            }
            
            // - Evento bottone "ESCI"
            if (o==btnLogout){
                TornaAllaLogin(evt);
            }
            
            //Evento bottone "AGGIUNGI COMMENTO"
            if (o==btnAggiungi){
                AggiungiActionPerformed(evt);
                edtCommenti.setText("");
                
            }
        }
        };
        // Per associare/aggiungere alla GUI l’ascoltatore degli eventi relativi ai bottoni, 
        // abbiamo passato a ciascun bottone il metodo addActionListener.
        btnStart.addActionListener(a);
        btnIstruzioni.addActionListener(a);
        btnAudio.addActionListener(a);
        btnClassifica.addActionListener(a);
        btnLogout.addActionListener(a);
        btnAggiungi.addActionListener(a);
        
        finestraGioco.setVisible(true); // Rendiamo la finestra di gioco visibile
        // Definiamo cosa avviene quando clicchiamo sulla x della finestra
        finestraGioco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraGioco.setResizable(false); // Impediamo che il frame (finestraGioco) possa essere ridimensionato
        finestraGioco.setLocationRelativeTo(null); // Mette al centro dello schermo la finestra del gioco relativa
    }

    // Metodo per creare la finestra dove si svilupperà il gioco
    private JFrame creaFinestraDiInizioGioco(JFrame finestra){
            finestra.setLayout(null);
            finestra.setSize(width,higth);  
            finestra.setTitle("Escape in the space");           
            finestra.setVisible(true);
            finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            finestra.setResizable(false); 
            finestra.setLocationRelativeTo(null); 
            return finestra;
    }
      
    // Metodo per creare il pannello con le istruzioni di gioco
    private void IstruzioniGioco() {
         JOptionPane.showMessageDialog(null, 
              "Benvenuto nel pannello relativo alle istruzioni. \nPer giocare puoi utilizzare: "
                      + "\n - Il mouse e le freccette direzionali \n   per spostarti. \n - La barra spaziatrice per sparare. "
                      + "\nL'obiettivo del gioco è quello di superare \npiù livelli accumulando più punti possibili.",
              "ISTRUZIONI GIOCO",JOptionPane.INFORMATION_MESSAGE); 
    }

    // Metodo per ritornare alla finestra di Login
    private void TornaAllaLogin(ActionEvent evt) {
        finestraGioco.setVisible(false);
        FinestraConLogin mf = new FinestraConLogin();       
    }
    
    // Metodo per aggiungere il commento
    private void AggiungiActionPerformed(ActionEvent evt) {
        String user = labUsername.getText();
        String commento = edtCommenti.getText(); 
        PreparedStatement ps;
        String query = "INSERT INTO `recensioni`(`User`,`Commento`) VALUES (?,?)";
        try {
            Connection connection = ConnesioneDB.getConnection();  
            ps = connection.prepareStatement(query);
            if (commento.equals("")) {
                JOptionPane.showMessageDialog(null, "Commento non Inserito");
            } else {
                ps.setString(1, user);
                ps.setString(2, commento);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Commento Aggiunto");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinestraBottoni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Metodo in cui abbiamo creato il pannello per visualizzare i 5 migliori risultati 
    // ottenuti dai giocatori sul gioco
    private void MostraRecordActionPerformed(ActionEvent evt) {
        final JPanel pannello = new JPanel();
        pannello.setBackground(Color.CYAN);
        JLabel lab1Domanda = new JLabel("I 5 migliori punteggi del gioco");
        lab1Domanda.setFont(new java.awt.Font("Thaoma", 1, 13));
        lab1Domanda.setForeground(Color.BLACK);
        pannello.add(lab1Domanda);
        JLabel lab2Chiusura = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\chiudi.png"));
        lab2Chiusura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pannello.add(lab2Chiusura);
        lab2Chiusura.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelChiudiMouseClicked(evt);
            }

            private void jLabelChiudiMouseClicked(MouseEvent evt) {
                pannello.setVisible(false);
                FinestraBottoni fb = new FinestraBottoni(us); 
            }
        });
        DefaultTableModel model = new DefaultTableModel() { 
            String[] colonne = {"USERNAME","RECORD", "LIVELLO"}; 

            @Override 
            public int getColumnCount() { 
                return colonne.length; 
            } 

            @Override 
            public String getColumnName(int indice) { 
                return colonne[indice]; 
            } 
        }; 
        tbRecord =new JTable(model);
        tbRecord.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
        tbRecord.setPreferredScrollableViewportSize(new Dimension(250,80));
        tbRecord.setVisible(true);
        pannello.add(new JScrollPane(tbRecord));
        Lista_Record();

        pannello.setSize(300, 150);
        pannello.setLocation(350, 120);
        finestraGioco.setContentPane(pannello);
    }
    
    // Metodo per prelevare i 5 migliori risultati dal database
    private ArrayList<Utenti> prendiPunteggiRecord(){
        ArrayList<Utenti> listaRecord = new ArrayList<Utenti>();
        String query = "SELECT * FROM `classifica` ORDER BY Punteggio DESC LIMIT 5";
        PreparedStatement ps;
        ResultSet rs;
        try {
            //ps = (PreparedStatement) ConnesioneDB.getConnection().prepareStatement(serchQuery);
            Connection connection = ConnesioneDB.getConnection();  
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery(query);
            Utenti record;
                
            while (rs.next()){
                record = new Utenti(rs.getString("User"), rs.getInt("Punteggio"), rs.getInt("Livello"));
                listaRecord.add(record);
            }
        }catch (java.sql.SQLException ex) {
            Logger.getLogger(FinestraAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRecord;
    } 
       
    // Metodo per inserire i valori (prelevati dal database) nella tabella definita dentro il pannello  
    private void Lista_Record() {  
        ArrayList<Utenti> lista = prendiPunteggiRecord();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"USERNAME","RECORD", "LIVELLO"});
      
        Object[] riga = new Object[3];  
        for(int i=0; i < lista.size(); i++){
            riga[0] = lista.get(i).getUsername();
            riga[1] = lista.get(i).getPunteggioMax();
            riga[2] = lista.get(i).getLivello();
           
            model.addRow(riga);
        }    
        tbRecord.setModel(model);
    }
    
    // Metodo per visualizzare il miglior punteggio ottenuto dall'utente giocatore
    private int visualizzaTuoPunteggioMax() {
        String user = labUsername.getText();
        PreparedStatement ps;
        ResultSet rs;
        int p = 0;
                
        String query = "SELECT max(Punteggio) FROM `classifica` WHERE `User` =? ";      
        try {
            Connection connection = ConnesioneDB.getConnection();  // Otteniamo la connessione utilizzando l'interfaccia Connection
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()){
                p = rs.getInt("max(Punteggio)");
            }     
        } catch (SQLException ex) {
            Logger.getLogger(FinestraConLogin.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return p;
        
    }
         

}
