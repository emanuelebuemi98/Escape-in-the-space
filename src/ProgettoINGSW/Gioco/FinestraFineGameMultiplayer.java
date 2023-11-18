
package ProgettoINGSW.Gioco;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class FinestraFineGameMultiplayer extends javax.swing.JFrame {    
    
    private URL url; 
    private JPanel jPanel1;
    private JTextPane jMsg;
    private JScrollPane jScrollPane1;

    public FinestraFineGameMultiplayer() {
                 
        setTitle("Escape in the space"); // Definiamo il nome del gioco            

        // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try { 
            url  = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
        }
        
        //Definiamo il pannello che apparirà dentro il frame quando i due player perderanno 
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(255, 204, 51));// Definiamo il colore di sfondo del pannello
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout); 
        //Definiamo la dimensione del pannello (orizzontalmente e verticalmente)
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        );
        
        // Definiamo uno ScrollPane1 che conterrà dentro il messaggio con i player vincitore/sconfitto 
        // e il punteggio raggiunto da ciascuno
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(jMsg);
        
        //Definiamo il tipo di scrittura, il grassetto, la dimensione e il colore del messaggio che apparirà
        jMsg = new JTextPane();
        jMsg.setEditable(false);
        jMsg.setFont(new java.awt.Font("Tahoma", 1, 12));
        jMsg.setForeground(Color.BLACK);
        
        //Definiamo la posizione dello ScrollPane1 all'interno del pannello
        jScrollPane1.setViewportView(jMsg);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
      
        // Definiamo cosa avviene quando clicchiamo sulla x della finestra
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false); // Impediamo che il frame possa essere ridimensionato
        setLocationRelativeTo(null); // Mette al centro dello schermo il frame di fine game

    }
    
    //Metodo che servira per settare la scritta del messaggio
    public void setMessage(String msg){  
        jMsg.setText(msg);
    } 
}
