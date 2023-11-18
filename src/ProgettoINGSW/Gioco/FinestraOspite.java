
package ProgettoINGSW.Gioco;

import java.sql.PreparedStatement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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


public class FinestraOspite {

    final JFrame finestraOsp;
    
    private URL url; 
    private final JLabel labImage;
    private final JLabel labPrecedente;
    private final JLabel labSuccessivo;
    private final JLabel labRegistrazione;
    private final JButton btnRegistrati;
    private final JButton btnVisualizzaCom;  
    private List listCom;
    private int pos = 0;
    private ArrayList<Utenti> risultati;

    
    public FinestraOspite() {
        
        finestraOsp = new JFrame();

        finestraOsp.setSize(700,450); // Impostiamo la dimensione del frame        
        finestraOsp.setTitle("Escape in the space"); // Definiamo il nome del gioco            
        finestraOsp.getContentPane().setBackground(Color.ORANGE); // Definiamo il colore dello sfondo del frame
        
        
        // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try { 
            url  = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            finestraOsp.setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
        }
        
        //Definiamo l'immagine di sfondo
        finestraOsp.setLayout(new BorderLayout());
        finestraOsp.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\sfondoGiocoOspite.jpg")));
     
        // Imponiamo il gestore di layout uguale a null per poter posizionare le componenti in modo manuale con il setBounds
        finestraOsp.setLayout(null);
        
        //Definiamo le varie componenti che appariranno nel frame
        labImage = new JLabel();
        labImage.setText("");
        labImage.setBounds(115, 30, 450, 250);
        finestraOsp.add(labImage);
        mostraImage(pos);
        
        labPrecedente =  new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\lab_previous.png"));
        labPrecedente.setBounds(20, 140, 100, 52);
        labPrecedente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finestraOsp.add(labPrecedente);  
        //Definiamo l'evendo per tornare all'immagine precedente tramite il click con il mouse sulla label labPrecedente
        labPrecedente.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelPrecedenteMousePressed(evt);
            }
        });
        
        labSuccessivo =  new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\lab_next.png"));
        labSuccessivo.setBounds(560, 140, 100, 52);
        labSuccessivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finestraOsp.add(labSuccessivo);  
        //Definiamo l'evendo per passare all'immagine successiva tramite il click con il mouse sulla label labSuccessivo
        labSuccessivo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSuccessivoMousePressed(evt);
            }
        });
        
        labRegistrazione =  new JLabel();
        labRegistrazione.setText("Cosa aspetti a registrarti? Fallo ora ");
        labRegistrazione.setFont(new java.awt.Font("Tahoma", 2, 14));
        labRegistrazione.setForeground(Color.WHITE);
        labRegistrazione.setBounds(5, 320, 250, 50);
        finestraOsp.add(labRegistrazione); 
        
        //Definiamo i 2 bottoni 
        btnRegistrati = new JButton("REGISTRATI");
        btnRegistrati.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnRegistrati.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnRegistrati.setForeground(Color.BLACK);
        btnRegistrati.setBounds(30, 360, 160, 30);
        btnRegistrati.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrati.setBackground(Color.CYAN);
        finestraOsp.add(btnRegistrati);
        
        btnVisualizzaCom = new JButton("VEDI RECENSIONI");
        btnVisualizzaCom.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnVisualizzaCom.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnVisualizzaCom.setForeground(Color.BLACK);
        btnVisualizzaCom.setBounds(220, 360, 160, 30);
        btnVisualizzaCom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVisualizzaCom.setBackground(Color.CYAN);
        finestraOsp.add(btnVisualizzaCom);
        
        listCom = new List();
        listCom.setFont(new java.awt.Font("Tahoma", 1, 13));
        listCom.setBackground(Color.CYAN);
        listCom.setForeground(Color.BLACK);
        listCom.setBounds(390, 300, 280, 90);
        finestraOsp.add(listCom); //commenti aggiunti appena il tasto VEDI RECENSIONI viene premuto

        // Per definire un gestore di eventi abbiamo implementato l’interfaccia ActionListener.
        // L'ActionListener implementa un metodo chiamato actionPerformed; questo metodo in base all'evento che si verifica  
        // risponde a quel determinato evento associando il rispettivo comportamento. 
        // Questi eventi di tipo ActionEvent sono eventi che consistono nel compiere un’azione nella GUI da parte dell’utente. 
        // Gli eventi implementati relativi ai 3 bottoni sono:
        // - Evento bottone "REGISTRATI" ---> permette all'utente ospite di passare alla finestra di registrazione
        // - Evento bottone "VEDI COMMENTI" ---> permette all'utente ospite di vedere le recensioni degli utenti registrati sul gioco
        ActionListener a = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Restituisce la componente dell'oggetto o su cui si è verificato l'evento evt
            Object o = evt.getSource(); 
            
            // - Evento bottone "REGISTRATI"
            if (o==btnRegistrati){ 
                btnRegistratiActionPerformed(evt);
            }
            
            // - Evento bottone "VEDI COMMENTI"
            if (o==btnVisualizzaCom){
                btnVisualizzaComActionPerformed(evt);
            }
        }
        };
        // Per associare/aggiungere alla GUI l’ascoltatore degli eventi relativi ai bottoni, 
        // abbiamo passato a ciascun bottone il metodo addActionListener.
        btnRegistrati.addActionListener(a);
        btnVisualizzaCom.addActionListener(a);
        
        finestraOsp.setVisible(true); // Rendiamo la finestra di gioco visibile
        // Definiamo cosa avviene quando clicchiamo sulla x della finestra
        finestraOsp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraOsp.setResizable(false); // Impediamo che il frame (finestraGioco) possa essere ridimensionato
        finestraOsp.setLocationRelativeTo(null); // Mette al centro dello schermo la finestra del gioco relativa
        
    }
    
    //Metodo che ci prende le immagini dalla cartella ImmaginiOspite
    private String [] prendiImage(){
        File fileNameImage = new File(getClass().getResource("/ImmaginiOspite").getFile());
        String[] imagesList = fileNameImage.list();
        return imagesList;
    }
    
    //Metodo che ci mostra le immagini del gioco e li aggiorna in base al tasto premuto
    private void mostraImage(int index){
        String[] imagesList = prendiImage();
        String img = imagesList[index];
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/ImmaginiOspite/" + img));
        Image image = imgIcon.getImage().getScaledInstance(labImage.getWidth(), labImage.getHeight(), Image.SCALE_SMOOTH);
        labImage.setIcon(new ImageIcon(image));
    }
    
    //Metodo per tornare all'immagine precedente 
    private void jLabelPrecedenteMousePressed(MouseEvent evt) {
        Thread thread1 = new Thread();
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(FinestraOspite.class.getName()).log(Level.SEVERE, null, ex);
        }
        pos = pos - 1;
        if (pos < 0){
            pos = 0;
        }
        mostraImage(pos);
    }
    
    //Metodo per andare all'immagine successiva
    private void jLabelSuccessivoMousePressed(MouseEvent evt) {
        Thread thread2 = new Thread();
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(FinestraOspite.class.getName()).log(Level.SEVERE, null, ex);
        }
        pos = pos + 1;
        if (pos >= prendiImage().length){
            pos = prendiImage().length - 1;
        }
        mostraImage(pos);
    }
    
    //Metodo per passare alla finestra di registrazione
    private void btnRegistratiActionPerformed(ActionEvent evt) {
        finestraOsp.setVisible(false);
        FinestraConRegistrazione rgf = new FinestraConRegistrazione();
    }
    
    //Metodo per visualizzare i commenti dei vari utenti
    private void btnVisualizzaComActionPerformed(ActionEvent evt) {
        risultati = new ArrayList<>();
        risultati = elencoCommenti();
        listCom.removeAll();
        for (int i = 0; i < risultati.size(); i++) {
          listCom.add("Utente: " + risultati.get(i).getUsername() + " ---  Recensione: " +risultati.get(i).getCommenti());
        }
         
    }
    
    //Metodo che preleva dalla tabella recensioni del database i commenti inseirti dai vari utenti registrati
    public ArrayList<Utenti> elencoCommenti() {
        ArrayList<Utenti> elenco = new ArrayList<Utenti>();
        PreparedStatement ps;
        ResultSet rs;
                
        String query = "SELECT * FROM `recensioni`";
        
        try {
            Connection connection = ConnesioneDB.getConnection();  // Otteniamo la connessione utilizzando l'interfaccia Connection
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Utenti ut = new Utenti(rs.getString("User"),rs.getString("Commento"));
                elenco.add(ut);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinestraConLogin.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return elenco;
        
    }
}
