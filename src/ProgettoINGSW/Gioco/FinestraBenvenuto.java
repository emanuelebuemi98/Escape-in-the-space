

package ProgettoINGSW.Gioco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinestraBenvenuto {
    
    // Creiamo la finestra di benvenuto al gioco 
    JFrame f;
    
    private URL url;
    private final JLabel labBenvenuto;
    private final JLabel labCaricamento;
    private final JLabel labTesto;
    private final JLabel labCopyright;
    private JButton btn1Ospite;
    private JButton btn2Utente;
    
    public FinestraBenvenuto(){
        
        // Creiamo la finestra di benvenuto al gioco 
        f = new JFrame();
        f.setSize(700,450);
        f.setTitle("Escape in the space"); // Definiamo il nome del gioco
       
        f.getContentPane().setBackground(Color.ORANGE); // Definiamo il colore dello sfondo del frame
        
        // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try {
            url = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            f.setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
        }
        
        //Definiamo l'immagine di sfondo
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\sfondoGiocoIniziale.jpg")));
        

        // Imponiamo il gestore di layout uguale a null per poter posizionare le componenti in modo manuale con il setBounds
        f.setLayout(null);
        
        // Aggiungiamo alla finestra di gioco le etichette create
        labBenvenuto = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\LogoWelcome.png"));
        labBenvenuto.setBounds(180, 100, 330, 142);
        f.add(labBenvenuto);
        
        labCaricamento = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\loading1.gif"));
        labCaricamento.setBounds(220, 260, 260, 30);
        f.add(labCaricamento);
        
        labTesto = new JLabel();
        labTesto.setText(" Continua come?");
        labTesto.setFont(new java.awt.Font("Arial", 1, 20));
        labTesto.setForeground(Color.WHITE);
        labTesto.setBackground(Color.BLACK);
        labTesto.setOpaque(true);
        labTesto.setBounds(250, 250, 170, 40);
        labTesto.setVisible(false);
        f.add(labTesto); 
        
        labCopyright = new JLabel();
        labCopyright.setText("<html> COPYRIGHT <br> Gioco realizzato da Buemi & Giambò <html>");
        labCopyright.setFont(new java.awt.Font("Arial", 3, 12));
        labCopyright.setForeground(Color.ORANGE);
        labCopyright.setBounds(34, 27, 130, 50);
        f.add(labCopyright); 
        
        btn1Ospite = new JButton("OSPITE");
        btn1Ospite.setFont(new java.awt.Font("Tahoma", 1, 15));
        btn1Ospite.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btn1Ospite.setForeground(Color.BLACK);
        btn1Ospite.setBounds(210, 313, 100, 30);
        btn1Ospite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn1Ospite.setBackground(Color.CYAN);
        btn1Ospite.setVisible(false);
        f.add(btn1Ospite);
        
        btn2Utente = new JButton("UTENTE");
        btn2Utente.setFont(new java.awt.Font("Tahoma", 1, 15));
        btn2Utente.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btn2Utente.setForeground(Color.BLACK);
        btn2Utente.setBounds(360, 313, 100, 30);
        btn2Utente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn2Utente.setBackground(Color.CYAN);
        btn2Utente.setVisible(false);
        f.add(btn2Utente);
        
        
        // Associamo l'evento relativo al bottone ospite
            btn1Ospite.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnOspiteActionPerformed(evt);
                }

                private void btnOspiteActionPerformed(ActionEvent evt) {
                    f.setVisible(false);  
                    FinestraOspite fOsp = new FinestraOspite();
                
                }
            });
         
            // Associamo l'evento relativo al bottone utente
            btn2Utente.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnUtenteActionPerformed(evt);
            }

                private void btnUtenteActionPerformed(ActionEvent evt) {
                    f.setVisible(false);  
                    FinestraConLogin fLog = new FinestraConLogin();
                }
            });
        
        Timer timer = new Timer(9000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labCaricamento.setVisible(false);
                labTesto.setVisible(true);
                btn1Ospite.setVisible(true);
                btn2Utente.setVisible(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
        
        f.setVisible(true); // Rendiamo la finestra di gioco visibile
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // chiusura del processo di sviluppo quando si preme x sulla finestra gi gioco
        f.setResizable(false); // Impediamo che il frame possa essere ridimensionato
        f.setLocationRelativeTo(null); // Mette al centro dello schermo la finestra del gioco relativa
       
    }
}