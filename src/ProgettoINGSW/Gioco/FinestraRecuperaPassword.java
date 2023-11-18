
package ProgettoINGSW.Gioco;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class FinestraRecuperaPassword {
    
    private URL url;
    private final JFrame finestraReset;
    private final JLabel labLogoReset;
    private final JLabel labUsername;
    private final JTextField edtUsername;
    private final JButton btnCerca; 
    private final JLabel labDomanda;
    private final JTextField edtDomanda;
    private final JLabel labRisposta;
    private final JTextField edtRisposta;
    private final JLabel labNuovaPassword;
    private final JPasswordField edtPasswordNuova;
    private final JButton btnSalva;
    private final JLabel labTornaAllaLogin;


    
    public FinestraRecuperaPassword() {
        
        
        finestraReset = new JFrame();
        
        finestraReset.setSize(700,450); // Impostiamo la dimensione del frame        
        finestraReset.setTitle("Escape in the space"); // Definiamo il nome del gioco 
        
         // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try {
            url = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            finestraReset.setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
        }

        //Definiamo l'immagine di sfondo
        finestraReset.setLayout(new BorderLayout());
        finestraReset.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\sfondoGiocoUtente.jpg")));
          
         // Imponiamo il gestore di layout uguale a null per poter posizionare le componenti in modo manuale con il setBounds
        finestraReset.setLayout(null);
        
        //Definiamo le varie componenti che appariranno nel frame
        labLogoReset = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\LogoPasswordDimenticata.png"));
        finestraReset.add(labLogoReset);
        
         //Campi necessari per inserire una nuova password che verrà aggiornata sul DB
        labUsername = new JLabel();
        labUsername.setText("Username: ");
        labUsername.setFont(new java.awt.Font("Tahoma", 1, 13));
        labUsername.setForeground(Color.ORANGE);
        labUsername.setBounds(50, 60, 150, 30);
        finestraReset.add(labUsername);
        
        edtUsername = new JTextField();
        edtUsername.setBounds(250, 60, 230, 25);
        finestraReset.add(edtUsername);
        
        btnCerca = new JButton("CERCA UTENTE");
        btnCerca.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnCerca.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnCerca.setForeground(Color.BLACK);
        btnCerca.setBounds(500, 60, 120, 25);
        btnCerca.setBackground(Color.CYAN);
        btnCerca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finestraReset.add(btnCerca);    
        
        labDomanda = new JLabel(); 
        labDomanda.setText("Domanda di sicurezza: "); 
        labDomanda.setFont(new java.awt.Font("Tahoma", 1, 13));
        labDomanda.setForeground(Color.ORANGE);
        labDomanda.setBounds(50, 100, 150, 30);
        finestraReset.add(labDomanda);
        
        edtDomanda = new JTextField();
        edtDomanda.setBounds(250, 100, 230, 25);
        finestraReset.add(edtDomanda);
        
        labRisposta = new JLabel(); 
        labRisposta.setText("Risposta: ");
        labRisposta.setForeground(Color.ORANGE);
        labRisposta.setBounds(50, 140, 150, 30);
        labRisposta.setFont(new java.awt.Font("Tahoma", 1, 12));
        finestraReset.add(labRisposta);
        
        edtRisposta = new JTextField();
        edtRisposta.setBounds(250, 140, 230, 25);
        finestraReset.add(edtRisposta);
        
        labNuovaPassword = new JLabel();
        labNuovaPassword.setText("Nuova password:       ");
        labNuovaPassword.setForeground(Color.ORANGE);
        labNuovaPassword.setBounds(50, 180, 150, 30);
        labNuovaPassword.setFont(new java.awt.Font("Tahoma", 1, 12));
        finestraReset.add(labNuovaPassword);
        
        edtPasswordNuova = new JPasswordField();
        edtPasswordNuova.setBounds(250, 180, 230, 25);
        finestraReset.add(edtPasswordNuova);
        
        btnSalva = new JButton("SALVA PASSWORD");
        btnSalva.setFont(new java.awt.Font("Tahoma", 1, 16));
        btnSalva.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnSalva.setForeground(Color.BLACK);
        btnSalva.setBounds(100, 250, 180, 40);
        btnSalva.setBackground(Color.CYAN);
        btnSalva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finestraReset.add(btnSalva);        
        
        labTornaAllaLogin = new JLabel();
        labTornaAllaLogin.setText("Torna alla login");
        labTornaAllaLogin.setFont(new java.awt.Font("Arial Black", 0, 14));
        labTornaAllaLogin.setForeground(Color.WHITE);
        labTornaAllaLogin.setBounds(130, 320, 150, 30);
        labTornaAllaLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finestraReset.add(labTornaAllaLogin);
        labTornaAllaLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLoginMouseClicked(evt);
            }
        });
        
        // Per definire un gestore di eventi abbiamo implementato l’interfaccia ActionListener.
        // L'ActionListener implementa un metodo chiamato actionPerformed; questo metodo in base all'evento che si verifica  
        // risponde a quel determinato evento associando il rispettivo comportamento. 
        // Questi eventi di tipo ActionEvent sono eventi che consistono nel compiere un’azione nella GUI da parte dell’utente. 
        // Gli eventi implementati relativi ai 2 bottoni sono:
        // - Evento bottone "CERCA UTENTE" ---> permette all'utente di cercare l'username di quando si è registrato
        // - Evento bottone "SALVA PASSWOED" ---> permette all'utente salvre la nuova password inserita
        ActionListener a = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Restituisce la componente dell'oggetto o su cui si è verificato l'evento e
            Object o = evt.getSource(); 
            
            // - Evento bottone "CERCA UTENTE"
            if (o==btnCerca){ 
                btnCercaActionPerformed(evt);
            }
            
            // - Evento bottone "SALVA PASSWORD"
            if (o==btnSalva){
                btnSalvaActionPerformed(evt);
            }
        }
        };
        // Per associare/aggiungere alla GUI l’ascoltatore degli eventi relativi ai bottoni, 
        // abbiamo passato a ciascun bottone il metodo addActionListener.
        btnCerca.addActionListener(a);
        btnSalva.addActionListener(a);
        
        finestraReset.setVisible(true); // Rendiamo la finestra di gioco visibile
        // Definiamo cosa avviene quando clicchiamo sulla x della finestra
        finestraReset.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraReset.setResizable(false); // Impediamo che il frame possa essere ridimensionato
        finestraReset.setLocationRelativeTo(null); // Mette al centro dello schermo la finestra del gioco relativa    
   
    }
    
    //Metodo che cerca l'username dell'utente che non ricorda la password
    private void btnCercaActionPerformed(ActionEvent evt) {
        int check = 0; // significa non aver inserito nessun nome 
        String username= edtUsername.getText();
        if(username.equals("")) {
            JOptionPane.showMessageDialog(null, "L'username è obbligatorio");
        }
        else { // altrimenti se user != null controlliamo se lo username è corretto o no
            check=1; // significa username è stato inserito
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT * FROM `utentiregistrati` WHERE `Username` =?";
            try {
                Connection connection = ConnesioneDB.getConnection();  // Otteniamo la connessione utilizzando l'interfaccia Connection
                ps = connection.prepareStatement(query);
                ps.setString(1, username);
                rs = ps.executeQuery();
                
                if(rs.next()) { // verificiamo se l'username è presente nel db
                    edtUsername.setEnabled(true); //username sarà visibile
                    edtDomanda.setEnabled(true); // la domanda sara visibile
                    edtDomanda.setText(rs.getString(4)); // compare la domanda di sicurezza (4 serve per indicare la quarta colonna del db, quella contenente le domande)
                }
                else {
                    JOptionPane.showMessageDialog(null, "L'username non è corretto");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }
    
    //Metodo che permette di salvare la nuova password inserita.
    //Per aggiornare la passord sarà necessario seguire i seguenti passi:
    //   - inserire l'username dell'utente di quando si è registrato
    //   - se l'username inserito è presente nel database apparità la domanda
    //     di sicurezza con il quale si è registrato quell'uetnte
    //   - a questo punto dovrà inserire la risposta corretta.
    // Dopo aver seguito questi passi l'utente potrà inserire una nuova password 
    private void btnSalvaActionPerformed(ActionEvent evt) {
        int check=0;
        String username= edtUsername.getText();
        String domSic = edtDomanda.getText();
        String risp = edtRisposta.getText(); 
        String newPass = String.valueOf(edtPasswordNuova.getPassword()); 
        if (risp.equals("") || newPass.equals("")) {
            JOptionPane.showMessageDialog(null, "Errore, alcuni campi non sono stati inseriti");
        }
        else {
            check = 1;
            PreparedStatement ps;
            ResultSet rs;
            String query = "SELECT * FROM `utentiregistrati` WHERE `Username` =? AND `Domanda_di_sicurezza` =? AND `Risposta` =?";
            try {
                Connection connection = ConnesioneDB.getConnection();  // Otteniamo la connessione utilizzando l'interfaccia Connection
                ps = connection.prepareStatement(query);       
                ps.setString(1, username);
                ps.setString(2, domSic);
                ps.setString(3, risp);
                rs = ps.executeQuery();
                
                String updateQuery = "UPDATE `utentiregistrati` SET `Password` = '"+newPass+"' WHERE Risposta=?";
                if(rs.next()) { // verifichiamo se la risposta coincide con quella memorizzata nel database 
                    ps = (PreparedStatement) ConnesioneDB.getConnection().prepareStatement(updateQuery);
                    ps.setString(1, risp);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Password modificata con successo");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Risposta sbagliata! RIPROVA");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    //Metodo per tornare alla finestra di Login
    private void jLabelLoginMouseClicked(MouseEvent evt) {
        finestraReset.setVisible(false);
        FinestraConLogin lgf = new FinestraConLogin();
    }
    
    
}
