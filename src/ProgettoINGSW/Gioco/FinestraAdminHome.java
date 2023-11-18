
package ProgettoINGSW.Gioco;

import java.sql.PreparedStatement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class FinestraAdminHome  {
     private final JFrame finestraHome;
     private URL url;
     private final JLabel label;
     private final JLabel labRicercaUtente;
     private final JTextField edtRicerca;
     private final JButton btnCerca;
     private final JButton btnLogout;
     private final JTable tblUtenti;
     private final JTextField edtUser;
     private final JButton btnEliminaUt;
     private final JTextField edtCommento;
     private final JButton btnEliminaCom;

     public FinestraAdminHome(){
         
        finestraHome = new JFrame();
        
        finestraHome.setSize(700,450); // Impostiamo la dimensione del frame        
        finestraHome.setTitle("Escape in the space"); // Definiamo il nome del gioco            
       
        // Passiamo l'immaginetta che apparirà come logo accanto al nome del frame
        try {
            url = getClass().getResource("/Img/logo.jpg"); 
            BufferedImage image = ImageIO.read(url);
            finestraHome.setIconImage(image);
        } catch (IOException e) {
            System.out.println("ImageError: " + e);
    
        }
        
        //Definiamo l'immagine di sfondo
        finestraHome.setLayout(new BorderLayout());
        finestraHome.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\sfondoGiocoUtente.jpg")));
    
        
        finestraHome.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 20));
        
        //Definiamo le varie componenti che appariranno nel frame
        label = new JLabel(new ImageIcon("C:\\Users\\Asus\\Documenti\\NetBeansProjects\\ProgettoINGSW\\src\\Img\\LogoAdmin.png"));
        finestraHome.add(label);
        
        btnLogout = new JButton("LOGOUT");
        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnLogout.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnLogout.setForeground(Color.BLACK);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.add(Box.createRigidArea(new Dimension(80, 25)));
        btnLogout.setBackground(Color.CYAN);
        finestraHome.add(btnLogout);
        
        labRicercaUtente = new JLabel();
        labRicercaUtente.setText(" Che utente vuoi cercare? ");
        labRicercaUtente.setFont(new java.awt.Font("Tahoma", 1, 15));
        labRicercaUtente.setForeground(Color.ORANGE);
        finestraHome.add(labRicercaUtente);
        
        edtRicerca = new JTextField(12);
        finestraHome.add(edtRicerca);

        btnCerca = new JButton("CERCA");
        btnCerca.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnCerca.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnCerca.setForeground(Color.BLACK);
        btnCerca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerca.add(Box.createRigidArea(new Dimension(80, 25)));
        btnCerca.setBackground(Color.CYAN);
        finestraHome.add(btnCerca);
        
       
        //Definiamo la tabella che conterrà i record contenuti nel database 
        DefaultTableModel model = new DefaultTableModel() { 
            String[] colonne = {"NOME UTENTE", "USERNAME", "PASSWORD", "COMMENTO"}; 

            @Override 
            public int getColumnCount() { 
                return colonne.length; 
            } 

            @Override 
            public String getColumnName(int indice) { 
                return colonne[indice]; 
            } 
        }; 
        
        tblUtenti = new JTable(model); 
        finestraHome.add(new JScrollPane(tblUtenti));
        tblUtenti.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
        tblUtenti.setPreferredScrollableViewportSize(new Dimension(600,80));
        //Definiamo la selezione di un utente tramite il click con il mouse
        tblUtenti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUtentiMouseClicked(evt);
            }
        });
        ListaUtentiAdmin();
        tblUtenti.setVisible(true);
        
        
        //per eliminare un utente
        edtUser = new JTextField(10);
        finestraHome.add(edtUser);
        
        btnEliminaUt = new JButton("ELIMINA UTENTE");
        btnEliminaUt.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnEliminaUt.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnEliminaUt.setForeground(Color.BLACK);
        btnEliminaUt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminaUt.add(Box.createRigidArea(new Dimension(130, 25)));
        btnEliminaUt.setBackground(Color.CYAN);
        finestraHome.add(btnEliminaUt);
        
        //per eliminare un commento
        edtCommento = new JTextField(10);
        finestraHome.add(edtCommento);
        
        btnEliminaCom = new JButton("ELIMINA COMMENTO");
        btnEliminaCom.setFont(new java.awt.Font("Tahoma", 1, 13));
        btnEliminaCom.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(220, 55, 15)));
        btnEliminaCom.setForeground(Color.BLACK);
        btnEliminaCom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminaCom.add(Box.createRigidArea(new Dimension(130, 25)));
        btnEliminaCom.setBackground(Color.CYAN);
        finestraHome.add(btnEliminaCom);
           
    
        // Per definire un gestore di eventi abbiamo implementato l’interfaccia ActionListener.
        // L'ActionListener implementa un metodo chiamato actionPerformed; questo metodo in base all'evento che si verifica  
        // risponde a quel determinato evento associando il rispettivo comportamento. 
        // Questi eventi di tipo ActionEvent sono eventi che consistono nel compiere un’azione nella GUI da parte dell’utente. 
        // Gli eventi implementati relativi ai 6 bottoni sono:
        // - Evento bottone "LOGOUT" ---> permette all'admin di uscire dal gioco  
        // - Evento bottone "CERCA" ---> permette all'admin di cercare un utente inserendo l'username
        // - Evento bottone "ELIMINA UTENTE" ---> permette all'admin di eliminare un utente registrato
        // - Evento bottone "ELIMINA COMMENTO" ---> permette all'admin di eliminare un commento inserito da un utente 
        ActionListener a = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Restituisce la componente dell'oggetto o su cui si è verificato l'evento e
            Object o = evt.getSource(); 
            
            // - Evento bottone "LOGOUT"
            if (o==btnLogout){
               btnLogoutActionPerformed(evt);
            }
            
            // - Evento bottone "CERCA"
            if (o==btnCerca){ 
                btnCercaActionPerformed(evt);
            }
            
            // - Evento bottone "ELIMINA UTENTE"
            if (o==btnEliminaUt){
               btnEliminaUtActionPerformed(evt);
            }
                        
            // - Evento bottone "ELIMINA COMMENTO"
            if (o==btnEliminaCom){
               btnEliminaComActionPerformed(evt);
            }   
            
        }   
        }; 
        // Per associare/aggiungere alla GUI l’ascoltatore degli eventi relativi ai bottoni, 
        // abbiamo passato a ciascun bottone il metodo addActionListener.
        btnCerca.addActionListener(a);
        btnLogout.addActionListener(a);
        btnEliminaUt.addActionListener(a);
        btnEliminaCom.addActionListener(a);

        finestraHome.setVisible(true); // Rendiamo la finestra di gioco visibile
        // Definiamo cosa avviene quando clicchiamo sulla x della finestra
        finestraHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestraHome.setResizable(false); // Impediamo che il frame possa essere ridimensionato
        finestraHome.setLocationRelativeTo(null);
     }     
     
    //Metodo per prelevare i record presenti nel database dalle tabelle utentiregistrati e recensioni
    private ArrayList<Utenti> getListaAdmin(String ValoreDiRicerca){
        ArrayList<Utenti> listaUtenti = new ArrayList<Utenti>();
        String serchQuery = "SELECT * FROM `utentiregistrati` LEFT JOIN `recensioni` ON utentiregistrati.Username=recensioni.User WHERE `Username`  LIKE '%"+ValoreDiRicerca+"%'";
            
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = (PreparedStatement) ConnesioneDB.getConnection().prepareStatement(serchQuery);
            rs = ps.executeQuery(serchQuery);
            Utenti amm;
                
            while (rs.next()){
                amm = new Utenti(rs.getString("Nome_utente"), rs.getString("Username"), rs.getString("Password"), rs.getString("Commento"));
                listaUtenti.add(amm);
            }
        }catch (java.sql.SQLException ex) {
            Logger.getLogger(FinestraAdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUtenti;
    } 
      
    //Metodo per inserire i record prelevati dal database
    private void ListaUtentiAdmin() {  
        ArrayList<Utenti> lista = getListaAdmin(edtRicerca.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"NOME UTENTE", "USERNAME", "PASSWORD", "COMMENTO" });
      
        Object[] riga = new Object[4];  
        for(int i=0; i < lista.size(); i++){
            riga[0] = lista.get(i).getNomeUtente();
            riga[1] = lista.get(i).getUsername();
            riga[2] = lista.get(i).getPassword();
            riga[3] = lista.get(i).getCommenti();
           
            model.addRow(riga);
        }    
        tblUtenti.setModel(model);
    }
       
    private void btnLogoutActionPerformed(ActionEvent evt) {
        finestraHome.setVisible(false);
        FinestraConLogin mf = new FinestraConLogin();
    }  
    
    // Metodo per implementare il bottone di ricera di un utente
    private void btnCercaActionPerformed(ActionEvent evt) {
        ListaUtentiAdmin();
    } 
       
    // Metodo per implementare il bottone per eliminare gli utenti dal database
    private void btnEliminaUtActionPerformed(ActionEvent evt) {
        PreparedStatement ps;
        String username = edtUser.getText();
        String query = "DELETE FROM `utentiregistrati` WHERE `Username` =?";
        
        try {   
            ps = (PreparedStatement) ConnesioneDB.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.executeUpdate();                     
            ListaUtentiAdmin();
            JOptionPane.showMessageDialog(null, "Utente eliminato");
               
        } catch (SQLException ex) {
            Logger.getLogger(FinestraConLogin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"ERRORE");
        }            
    }

     // Metodo per implementare il bottone per eliminare i commenti dal database
    private void btnEliminaComActionPerformed(ActionEvent evt) {
        PreparedStatement ps;
        String comm = edtCommento.getText();
        String query = "DELETE FROM `recensioni` WHERE `Commento` =?";
        
        try {   
            DefaultTableModel model = (DefaultTableModel)tblUtenti.getModel();
            int i = tblUtenti.getSelectedRow();
            if (model.getValueAt(i, 3)==null) {
                JOptionPane.showMessageDialog(null, "Commento vuoto"); 
            } else {
                ps = (PreparedStatement) ConnesioneDB.getConnection().prepareStatement(query);
                ps.setString(1, comm);
                ps.executeUpdate();           
                ListaUtentiAdmin();
                JOptionPane.showMessageDialog(null, "Commento eliminato"); 
            }        
        } catch (SQLException ex) {
            Logger.getLogger(FinestraConLogin.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"ERRORE");
        }            
    }
    
    // Metodo che permette di far comparire l'username e il commento dell'utente nelle rispettive JTextField
    private void tblUtentiMouseClicked(MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel)tblUtenti.getModel();
        int i = tblUtenti.getSelectedRow();
        edtUser.setText(model.getValueAt(i, 1).toString());
        if (model.getValueAt(i, 3)!=null) {
            edtCommento.setText(model.getValueAt(i, 3).toString());
        }
    }
        
}  
            

       
    
       

