
package ProgettoINGSW.Gioco;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.PreparedStatement;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class FinestraConLogin {

    final JFrame finestraLogin;
    
    private URL url; 
    private final JLabel labLogoLogin; 
    private final JLabel labUsername; 
    private final JLabel labPassword; 
    private final JTextField edtUsername;
    private final JPasswordField edtPassword;
    private final JCheckBox check;
    private final JButton btnLogin;  
    private final JButton btnPassDimenticata;  
    private final JLabel labNonSeiRegistrato; 
    private final JLabel labCliccaQui; 
   
        

    public FinestraConLogin() {
        
        finestraLogin = new JFrame();

        finestraLogin.setSize(700,450); // Impostiamo la dimensione del frame        
        finestraLogin.setTitle("Escape in the space"); // Definiamo il nome del gioco            
        
        // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try { 
            url  = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            finestraLogin.setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
        }
        
        //Definiamo l'immagine di sfondo
        finestraLogin.setLayout(new BorderLayout());
        finestraLogin.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\sfondoGiocoUtente.jpg")));
     
        // Imponiamo il gestore di layout uguale a null per poter posizionare le componenti in modo manuale con il setBounds
        finestraLogin.setLayout(null);
        
        //Definiamo le varie componenti che appariranno nel frame
        labLogoLogin = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\LogoLogin.png"));
        labLogoLogin.setBounds(250, 5, 173, 201);
        finestraLogin.add(labLogoLogin);

        //Definiamo le etichette e le TextField per l'inserimento delle credenziali dell'utente
        labUsername = new JLabel();
        labUsername.setText("Username: ");
        labUsername.setFont(new java.awt.Font("Arial Black", 1, 18));
        labUsername.setForeground(Color.ORANGE);
        labUsername.setBounds(188, 208, 120, 30);
        finestraLogin.add(labUsername);
        
        edtUsername = new JTextField();
        edtUsername.setBounds(340, 208, 130, 25);
        finestraLogin.add(edtUsername);
        
        labPassword  = new JLabel();
        labPassword.setText("Password: ");
        labPassword.setFont(new java.awt.Font("Arial Black", 1, 18));
        labPassword.setForeground(Color.ORANGE);
        labPassword.setBounds(188, 245, 120, 30);
        finestraLogin.add(labPassword);
        
        edtPassword = new JPasswordField();
        edtPassword.setBounds(340, 245, 130, 25);
        finestraLogin.add(edtPassword);
        
        //Creamo la casella che ci permette di rendere la password visibile
        check = new JCheckBox();
        check.setText("mostra password");
        check.setBounds(340, 270, 130, 20);
        finestraLogin.add(check);
        
        //Definiamo i 2 bottoni
        btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnLogin.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBounds(168, 313, 100, 30);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setBackground(Color.CYAN);
        finestraLogin.add(btnLogin);
        
        btnPassDimenticata = new JButton("PASSWORD DIMENTICATA");
        btnPassDimenticata.setFont(new java.awt.Font("Tahoma", 1, 15));
        btnPassDimenticata.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnPassDimenticata.setForeground(Color.BLACK);
        btnPassDimenticata.setBounds(320, 313, 220, 30);
        btnPassDimenticata.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPassDimenticata.setBackground(Color.CYAN);
        finestraLogin.add(btnPassDimenticata);
        
        labNonSeiRegistrato = new JLabel();
        labNonSeiRegistrato.setFont(new java.awt.Font("Tahoma", 2, 14));
        labNonSeiRegistrato.setText("Non sei Registrato? ");
        labNonSeiRegistrato.setForeground(Color.WHITE);
        labNonSeiRegistrato.setBounds(178, 350, 150, 30);
        finestraLogin.add(labNonSeiRegistrato);

        labCliccaQui = new JLabel();
        labCliccaQui.setText("CLICCA QUI");
        labCliccaQui.setFont(new java.awt.Font("Arial Black", 1, 14)); 
        labCliccaQui.setForeground(Color.WHITE);
        labCliccaQui.setBounds(328, 350, 150, 30);
        labCliccaQui.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finestraLogin.add(labCliccaQui);       
        labCliccaQui.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRegisterMouseClicked(evt);
            }
        });
        
        // Per definire un gestore di eventi abbiamo implementato l’interfaccia ActionListener.
        // L'ActionListener implementa un metodo chiamato actionPerformed; questo metodo in base all'evento che si verifica  
        // risponde a quel determinato evento associando il rispettivo comportamento. 
        // Questi eventi di tipo ActionEvent sono eventi che consistono nel compiere un’azione nella GUI da parte dell’utente. 
        // Gli eventi implementati relativi ai 3 bottoni sono:
        // - Evento bottone "LOGIN" ---> permette all'utente di effettuare l'accesso 
        // - Evento bottone "PASSWORD DIMENTICATA" ---> permette all'utente di andare nella finestra relativa al recupero della password 
        // - Evento bottone "mostra password" ---> permette all'utente di rendere visibile la password
        ActionListener a = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Restituisce la componente dell'oggetto o su cui si è verificato l'evento e
            Object o = evt.getSource(); 
            
            // - Evento bottone "LOGIN"
            if (o==btnLogin){ 
                btnLoginActionPerformed(evt);
            }
            
            // - Evento bottone "PASSWORD DIMENTICATA"
            if (o==btnPassDimenticata){
                btnPasswordDimenticataActionPerformed(evt);
            }
            
            // - Evento bottoncino "mostra password"
            if (o==check){
                jCheckBox1ActionPerformed(evt);
            }
            
        }
        };
        // Per associare/aggiungere alla GUI l’ascoltatore degli eventi relativi ai bottoni, 
        // abbiamo passato a ciascun bottone il metodo addActionListener.
        btnLogin.addActionListener(a);
        btnPassDimenticata.addActionListener(a);
        check.addActionListener(a);

        
        finestraLogin.setVisible(true); // Rendiamo la finestra di gioco visibile
        // Definiamo cosa avviene quando clicchiamo sulla x della finestra
        finestraLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraLogin.setResizable(false); // Impediamo che il frame (finestraGioco) possa essere ridimensionato
        finestraLogin.setLocationRelativeTo(null); // Mette al centro dello schermo la finestra del gioco relativa
        
    }
    
    //Metodo per effettuare il Login dell'utente
    private void btnLoginActionPerformed(ActionEvent evt) {
        PreparedStatement ps;
        ResultSet rs;
        String username = edtUsername.getText();
        String pass = String.valueOf(edtPassword.getPassword()); 
                
        String query = "SELECT * FROM `utentiregistrati` WHERE `Username` =? AND `Password` =?";
        
        try {
            
            ps = (PreparedStatement) ConnesioneDB.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                if(username.equals("AdminGioco") && pass.equals("admin")){
                    JOptionPane.showMessageDialog(null, "Ben tornato capo!!!");
                    finestraLogin.setVisible(false);
                    FinestraAdminHome admin = new FinestraAdminHome();  
                }
                else {
                    JOptionPane.showMessageDialog(finestraLogin, "Login effettuato con successo");
                    finestraLogin.setVisible(false);
                    FinestraModalitaGioco mf = new FinestraModalitaGioco(username);
                }
            }
            else if(username.equals("") || pass.equals("")) {
                JOptionPane.showMessageDialog(null, "Login fallito! Username o password vuote: Riprova");
            }     
            else{
                JOptionPane.showMessageDialog(null, "Login fallito! Username o Password non corrette: Riprova");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinestraConLogin.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    //Metodo per passare alla schermata "FinestraRecuperaPassword"
    private void btnPasswordDimenticataActionPerformed(ActionEvent evt) {
        finestraLogin.setVisible(false);
        FinestraRecuperaPassword frp = new FinestraRecuperaPassword();
    }
    
    //Metodo per visualizzare la password inserita dall'utente
    private void jCheckBox1ActionPerformed(ActionEvent evt) {
        if(check.isSelected()) {
            edtPassword.setEchoChar((char)0);
        }else {
            edtPassword.setEchoChar('•');
        }
    }
    
     //Metodo per passare alla schermata "FinestraConRegistrazione"
    private void jLabelRegisterMouseClicked(MouseEvent evt) {
        finestraLogin.setVisible(false);
        FinestraConRegistrazione rgf = new FinestraConRegistrazione();          
    }
   
}
