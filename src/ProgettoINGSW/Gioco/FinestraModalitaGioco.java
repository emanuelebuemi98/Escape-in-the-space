
package ProgettoINGSW.Gioco;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.sql.PreparedStatement;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class FinestraModalitaGioco {
    
    final JFrame finestraModGioco;
    
    private URL url; 
    private final JLabel labLogoModGioco; 
    private JLabel labScrittaModGioco; 
    private final JLabel labUser;
    private final JLabel labUsername;
    private final JLabel lablivello;
    private final JButton btnCarriera;  
    private final JButton btnMultiplayer;  
    private final String us;
    private static JFrame spazio;


    public FinestraModalitaGioco(String username) {
        
        finestraModGioco = new JFrame();

        finestraModGioco.setSize(700,450); // Impostiamo la dimensione del frame        
        finestraModGioco.setTitle("Escape in the space"); // Definiamo il nome del gioco            
        us=username;
        
        // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try { 
            url  = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            finestraModGioco.setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
        }
        
        //Definiamo l'immagine di sfondo
        finestraModGioco.setLayout(new BorderLayout());
        finestraModGioco.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\sfondoGiocoUtente.jpg")));
     
        // Imponiamo il gestore di layout uguale a null per poter posizionare le componenti in modo manuale con il setBounds
        finestraModGioco.setLayout(null);
      
        //Definiamo le varie componenti che appariranno nel frame
        labScrittaModGioco = new JLabel();
        labScrittaModGioco.setText("Scegli in che modalita' giocare");
        labScrittaModGioco.setFont(new java.awt.Font("Algerian", 1, 35));
        labScrittaModGioco.setForeground(Color.WHITE);
        labScrittaModGioco.setBounds(45, 60, 610, 40);
        finestraModGioco.add(labScrittaModGioco);
        
        labLogoModGioco = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\LogoModGioco.png"));
        labLogoModGioco.setBounds(370, 150, 251, 141);
        finestraModGioco.add(labLogoModGioco);
                
        labUser = new JLabel();
        labUser.setText("Utente: ");
        labUser.setFont(new java.awt.Font("Tahoma", 1, 13));
        labUser.setForeground(Color.ORANGE);
        labUser.setBounds(140, 10, 100, 30);
        finestraModGioco.add(labUser);
        
        labUsername = new JLabel();
        labUsername.setText(username);
        labUsername.setFont(new java.awt.Font("Tahoma", 1, 13));
        labUsername.setForeground(Color.ORANGE);
        labUsername.setBounds(190, 10, 100, 30);
        finestraModGioco.add(labUsername);
        
        lablivello = new JLabel();
        lablivello.setText("Livello massimo raggiunto = " + visualizzaTuoLivelloMax());
        lablivello.setFont(new java.awt.Font("Tahoma", 1, 13));
        lablivello.setForeground(Color.ORANGE);
        lablivello.setBounds(300, 10, 200, 30);
        finestraModGioco.add(lablivello);
        
        //Definiamo i 2 bottoni
        btnCarriera = new JButton("MODALITA' CARRIERA");
        btnCarriera.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnCarriera.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnCarriera.setForeground(Color.BLACK);
        btnCarriera.setBounds(90, 150, 250, 45);
        btnCarriera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCarriera.setBackground(Color.CYAN);
        finestraModGioco.add(btnCarriera);
        
        btnMultiplayer = new JButton("MODALITA' MULTIPLAYER");
        btnMultiplayer.setFont(new java.awt.Font("Tahoma", 1, 18));
        btnMultiplayer.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnMultiplayer.setForeground(Color.BLACK);
        btnMultiplayer.setBounds(90, 240, 250, 45);
        btnMultiplayer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMultiplayer.setBackground(Color.CYAN);
        finestraModGioco.add(btnMultiplayer);
        
        // Per definire un gestore di eventi abbiamo implementato l’interfaccia ActionListener.
        // L'ActionListener implementa un metodo chiamato actionPerformed; questo metodo in base all'evento che si verifica  
        // risponde a quel determinato evento associando il rispettivo comportamento. 
        // Questi eventi di tipo ActionEvent sono eventi che consistono nel compiere un’azione nella GUI da parte dell’utente. 
        // Gli eventi implementati relativi ai 2 bottoni sono:
        // - Evento bottone "MODALITA' CARRIERA" ---> permette all'utente di entrare nell'account della modalità carriera
        // - Evento bottone "MODALITA' MULTIPLAYER" ---> permette all'utente di lanciare la classe GameServer per giocare una partita multiplayer
        ActionListener a = new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Restituisce la componente dell'oggetto o su cui si è verificato l'evento e
            Object o = evt.getSource(); 
         
            // - Evento bottone "MODALITA' CARRIERA"
            if (o==btnCarriera){
                 btnCarrieraActionPerformed(evt);
            }
            
            // - Evento bottone "MODALITA' MULTIPLAYER"
            if (o==btnMultiplayer){ 
                try {
                    btnMultiplayerActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(FinestraModalitaGioco.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        };
        // Per associare/aggiungere alla GUI l’ascoltatore degli eventi relativi ai bottoni, 
        // abbiamo passato a ciascun bottone il metodo addActionListener.
        btnCarriera.addActionListener(a);
        btnMultiplayer.addActionListener(a);
        
        finestraModGioco.setVisible(true); // Rendiamo la finestra di gioco visibile
        // Definiamo cosa avviene quando clicchiamo sulla x della finestra
        finestraModGioco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraModGioco.setResizable(false); // Impediamo che il frame possa essere ridimensionato
        finestraModGioco.setLocationRelativeTo(null); // Mette al centro dello schermo la finestra del gioco relativa
        
    }
    
    //Metodo che permette di visualizzare il livello massimo ragginto dal giocatore
    private int visualizzaTuoLivelloMax() {
        String user = labUsername.getText();
        PreparedStatement ps;
        ResultSet rs;
        int liv = 0;
                
        String query = "SELECT max(Livello) FROM `classifica` WHERE `User` =? ";      
        try {
            
            //ps = (com.mysql.jdbc.PreparedStatement) ConnesioneDB.getConnection().prepareStatement(query);
            Connection connection = ConnesioneDB.getConnection();  // Otteniamo la connessione utilizzando l'interfaccia Connection
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()){
                liv = rs.getInt("max(Livello)");
            }     
        } catch (SQLException ex) {
            Logger.getLogger(FinestraConLogin.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return liv;
        
    }
    
    //Metodo che permette di passare alla schermata relativa all'account della modalità carriera
    private void btnCarrieraActionPerformed(ActionEvent evt) {
        finestraModGioco.setVisible(false);
        FinestraBottoni fp = new FinestraBottoni(us);
        
    }
    
    //Metodo che permette di lanciare la classe GameServer nel caso l'utente avrà 
    //raggiunto almeno il livello 5 
    private void btnMultiplayerActionPerformed(ActionEvent evt) throws IOException {
        PreparedStatement ps;
        ResultSet rs;
        String username = us;
        int livello = visualizzaTuoLivelloMax(); 

        String query = "SELECT * FROM `classifica` WHERE `User` =? AND `Livello` = " +livello;
        
        try {
            Connection connection = ConnesioneDB.getConnection();  // Otteniamo la connessione utilizzando l'interfaccia Connection
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
                if (livello>=5){ //verifichiamo che l'utente abbia almeno raggiunto il livello 5 
                    JOptionPane.showMessageDialog(null, "ACCESSO CONSENTITO! Attendi che i due player si connettino ");
                    finestraModGioco.setVisible(false);
                    try {
                        GameServer gs = new GameServer();
                        gs.acceptConnections();           
                    } catch (NullPointerException ex) {
                        System.out.println("");
                    }
                    
                }
                 else {
                    JOptionPane.showMessageDialog(null, "ACCESSO NEGATO! Devi aver raggiunto almeno il livello 5 in modalità carriera");  
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinestraConLogin.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
   
}
