
package ProgettoINGSW.Gioco;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class FinestraConRegistrazione {
   
    private final JFrame finestraRegistrazione;
    
    private URL url;
    private final JLabel labLogoRegistrazione;
    private final JLabel labNomeCompleto;
    private final JTextField edtNomeCompleto;
    private final JLabel labAnnoDiNascita ;
    private final JDateChooser edtAnnoDiNascita;
    private final JLabel labUsername;
    private final JTextField edtUsername;
    private final JLabel labPassword;
    private final JPasswordField edtPassword;
    private final JLabel labRipetiPassword;
    private final JPasswordField edtRipetiPassword;
    private final JLabel labDomanda;
    private final JComboBox<String> boxDomanda;
    private final JLabel labRisposta;
    private final JTextField edtRisposta;
    private final JLabel labEmail;
    private final JTextField edtEmail;
    private final JButton btnCrea;
    private final JButton btnAzzera;
    private final JLabel labTornaAllaLogin;
    

    public FinestraConRegistrazione() {

        finestraRegistrazione = new JFrame();
        
        finestraRegistrazione.setSize(700,450); // Impostiamo la dimensione del frame        
        finestraRegistrazione.setTitle("Escape in the space"); // Definiamo il nome del gioco            
        
        // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try {
            url = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            finestraRegistrazione.setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
        }
        
        //Definiamo l'immagine di sfondo
        finestraRegistrazione.setLayout(new BorderLayout());
        finestraRegistrazione.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\sfondoGiocoUtente.jpg")));
     
         // Imponiamo il gestore di layout uguale a null per poter posizionare le componenti in modo manuale con il setBounds
        finestraRegistrazione.setLayout(null);
        
        //Definiamo le varie componenti che appariranno nel frame
        labLogoRegistrazione = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\LogoRegistrazione.png"));
        labLogoRegistrazione.setBounds(200, 5, 280, 106);
        finestraRegistrazione.add(labLogoRegistrazione);
        
        //Campi per inserire i dati necessari alla registrazione
        labNomeCompleto = new JLabel();
        labNomeCompleto.setText("Nome utente: ");
        labNomeCompleto.setFont(new java.awt.Font("Tahoma", 1, 13));
        labNomeCompleto.setForeground(Color.ORANGE);
        labNomeCompleto.setBounds(80, 120, 150, 30);
        finestraRegistrazione.add(labNomeCompleto);
        
        edtNomeCompleto = new JTextField();
        edtNomeCompleto.setBounds(260, 120, 330, 22);
        finestraRegistrazione.add(edtNomeCompleto);
        
        labAnnoDiNascita = new JLabel();
        labAnnoDiNascita.setText("Anno di nascita: ");
        labAnnoDiNascita.setFont(new java.awt.Font("Tahoma", 1, 13));
        labAnnoDiNascita.setForeground(Color.ORANGE);
        labAnnoDiNascita.setBounds(80, 150, 150, 30);
        finestraRegistrazione.add(labAnnoDiNascita);
        
        edtAnnoDiNascita = new JDateChooser();
        edtAnnoDiNascita.setFont(new java.awt.Font("Tahoma", 1, 13));
        edtAnnoDiNascita.setBounds(260, 150, 330, 22);
        finestraRegistrazione.add(edtAnnoDiNascita);
        
        labUsername = new JLabel();
        labUsername.setText("Username: ");
        labUsername.setFont(new java.awt.Font("Tahoma", 1, 13));
        labUsername.setForeground(Color.ORANGE);
        labUsername.setBounds(80, 180, 150, 30);
        finestraRegistrazione.add(labUsername);
          
        edtUsername = new JTextField();
        edtUsername.setBounds(260, 180, 330, 22);
        finestraRegistrazione.add(edtUsername);
        
        labPassword = new JLabel();
        labPassword.setText("Password: ");
        labPassword.setFont(new java.awt.Font("Tahoma", 1, 13));
        labPassword.setForeground(Color.ORANGE);
        labPassword.setBounds(80, 210, 150, 30);
        finestraRegistrazione.add(labPassword);
        
        edtPassword = new JPasswordField();
        edtPassword.setBounds(260, 210, 330, 22);
        finestraRegistrazione.add(edtPassword);
        
        labRipetiPassword = new JLabel(); 
        labRipetiPassword.setText("Ripeti password: ");
        labRipetiPassword.setFont(new java.awt.Font("Tahoma", 1, 13));
        labRipetiPassword.setForeground(Color.ORANGE);
        labRipetiPassword.setBounds(80, 240, 150, 30);
        finestraRegistrazione.add(labRipetiPassword );
        
        edtRipetiPassword = new JPasswordField();
        edtRipetiPassword.setBounds(260, 240, 330, 22);
        finestraRegistrazione.add(edtRipetiPassword);
        
        labDomanda = new JLabel(); 
        labDomanda.setText("Domanda di sicurezza: "); //oppure Domanda di sicurezza
        labDomanda.setFont(new java.awt.Font("Tahoma", 1, 13));
        labDomanda.setForeground(Color.ORANGE);
        labDomanda.setBounds(80, 270, 150, 30);
        finestraRegistrazione.add(labDomanda);
        
        boxDomanda = new JComboBox<>();
        finestraRegistrazione.add(boxDomanda);
        boxDomanda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Come si chiama il tuo animale domestico?", "Qual è stata la tua prima macchina?", "A quale compagnia telefonica sei abbonato?", "Qual è il nome della città dove sei nato?" }));
        boxDomanda.setForeground(Color.BLACK);
        boxDomanda.setBounds(260, 270, 330, 22);
        boxDomanda.setFont(new java.awt.Font("Tahoma", 1, 13));
        
        labRisposta = new JLabel(); 
        labRisposta.setText("Risposta: ");
        labRisposta.setFont(new java.awt.Font("Tahoma", 1, 13));
        labRisposta.setForeground(Color.ORANGE);
        labRisposta.setBounds(80, 300, 150, 30);
        finestraRegistrazione.add(labRisposta);
        
        edtRisposta = new JTextField();
        edtRisposta.setBounds(260, 300, 330, 22);
        finestraRegistrazione.add(edtRisposta);
        
        labEmail = new JLabel();
        labEmail.setText("Email: ");
        labEmail.setFont(new java.awt.Font("Tahoma", 1, 13));
        labEmail.setForeground(Color.ORANGE);
        labEmail.setBounds(80, 330, 150, 30);
        finestraRegistrazione.add(labEmail);
        
        edtEmail = new JTextField();
        edtEmail.setBounds(260, 330, 330, 22);
        finestraRegistrazione.add(edtEmail);
        
        //Definiamo i 2 bottoni
        btnCrea = new JButton("REGISTRATI");
        btnCrea.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnCrea.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnCrea.setForeground(Color.BLACK);
        btnCrea.setBounds(290, 370, 100, 25);
        btnCrea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrea.setBackground(Color.CYAN);
        finestraRegistrazione.add(btnCrea);
         
        btnAzzera = new JButton("CANCELLA"); 
        btnAzzera.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnAzzera.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnAzzera.setForeground(Color.BLACK);
        btnAzzera.setBounds(450, 370, 100, 25);
        btnAzzera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAzzera.setBackground(Color.CYAN);
        finestraRegistrazione.add(btnAzzera);
        
        
        labTornaAllaLogin = new JLabel();
        labTornaAllaLogin .setText("Torna alla login");
        labTornaAllaLogin.setFont(new java.awt.Font("Arial Black", 1, 14));
        labTornaAllaLogin .setForeground(Color.WHITE);
        labTornaAllaLogin .setBounds(100, 370, 150, 30);
        labTornaAllaLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finestraRegistrazione.add(labTornaAllaLogin);
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
        // - Evento bottone "REGISTRATI" ---> permette all'utente di effettuare la registrazione
        // - Evento bottone "CANCELLA" ---> permette all'utente di cancellare i dati inseriti
        ActionListener a = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            Object o = evt.getSource(); 
            
            // - Evento bottone "REGISTRATI"
            if (o==btnCrea){ 
                jButton_RegisterActionPerformed(evt);
            }
            
            // - Evento bottone "CANCELLA"
            if (o==btnAzzera){
                jButton_CancActionPerformed(evt);
            }      
          }  
        };
         // Per associare/aggiungere alla GUI l’ascoltatore degli eventi relativi ai bottoni, 
        // abbiamo passato a ciascun bottone il metodo addActionListener.
        btnCrea.addActionListener(a);
        btnAzzera.addActionListener(a);
        
        finestraRegistrazione.setVisible(true); // Rendiamo la finestra di gioco visibile
        // Definiamo cosa avviene quando clicchiamo sulla x della finestra
        finestraRegistrazione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraRegistrazione.setResizable(false); // Impediamo che il frame possa essere ridimensionato
        finestraRegistrazione.setLocationRelativeTo(null); // Mette al centro dello schermo la finestra del gioco relativa    
    }
    
    //Metodo per memorizzare i dati inseriti dall'utente nella registrazone
    private void jButton_RegisterActionPerformed(ActionEvent evt) {
        String nome_utente = edtNomeCompleto.getText();
       // String cognome = edtCognome.getText();
        String username = edtUsername.getText();
        String pass = String.valueOf(edtPassword.getPassword());
        String rip_pass = String.valueOf(edtRipetiPassword.getPassword());
        String quesito = (String) boxDomanda.getSelectedItem();
        String risposta = edtRisposta.getText();
        String data = null;
        String email = edtEmail.getText(); 
        
        if(nome_utente.equals("") || username.equals("") || pass.equals("") || risposta.equals("")) {
            JOptionPane.showMessageDialog(null, "Registrazione fallita! Tutti campi sono obbligatori");
        }                       
        else if(!pass.equals(rip_pass)) {
            JOptionPane.showMessageDialog(null, "Registrazione fallita! Le due password non coincidono");
        }     
 
        else if (checkUsername(username)) {
            JOptionPane.showMessageDialog(null, "Registrazione fallita! L'username già esistente");
        } 
        else { 
            if(edtAnnoDiNascita.getDate() !=  null) {
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                data = dateformat.format(edtAnnoDiNascita.getDate());        
            }
            PreparedStatement ps;
            String query = "INSERT INTO `utentiregistrati`(`Nome_utente`, `Username`, `Password`, `Domanda_di_sicurezza`, `Risposta`, `Data_di_nascita`, `Email`) VALUES (?,?,?,?,?,?,?)";             
            try {
                
                Connection connection = ConnesioneDB.getConnection();  // Otteniamo la connessione utilizzando l'interfaccia Connection
                ps = connection.prepareStatement(query);
                ps.setString(1, nome_utente);
                ps.setString(2, username);
                ps.setString(3, pass);
                ps.setString(4, quesito);
                ps.setString(5, risposta);
                if (data != null) { 
                    ps.setString(6, data);
                }else{
                    ps.setNull(6, 0);   //l'inserimento della data non è obbligatorio
                }
                if (email != null) { 
                    ps.setString(7, email);
                } else{
                    ps.setNull(7, 0);   //l'inserimento dell'email non è obbligatorio
                }
                if(ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "UTENTE REGISTRATO");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(FinestraConRegistrazione.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    //Metodo per cancellare i dati inseriti dall'utente in fase di registrazione
    private void jButton_CancActionPerformed(ActionEvent evt) {
        edtNomeCompleto.setText("");     
        //edtCognome.setText("");
        edtUsername.setText("");
        edtPassword.setText("");
        edtRipetiPassword.setText("");
        edtRisposta.setText("");  
        edtEmail.setText(""); 
    }

    //Metodo per verificare se il nome utente esiste già nella tabella del database
    private boolean checkUsername(String user)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "SELECT * FROM `utentiregistrati` WHERE `Username` =?";
        
        try {
            Connection connection = ConnesioneDB.getConnection();  
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                checkUser = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinestraConRegistrazione.class.getName()).log(Level.SEVERE, null, ex);
        }
         return checkUser;
    }
    
    //Metodo per tornare alla finestra di Login
    private void jLabelLoginMouseClicked(MouseEvent evt) {
        finestraRegistrazione.setVisible(false);
        FinestraConLogin lgf = new FinestraConLogin();
    }
    
}