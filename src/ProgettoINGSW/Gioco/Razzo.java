
package ProgettoINGSW.Gioco;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Razzo extends Oggetto{
    private byte spostamento;
    private final int vel = 5;
    private int lastX;
    private String fileimg;
    
    public Razzo(){
        //super();
        try{
            fileimg = getImageFile();
            fileimg = "/Img/razzo.gif"; // definiamo il nome del file che conterrà le immaggine del razzo
            setImage(new ImageIcon(getClass().getResource(fileimg))); // associamo l'immagine del razzo che rappresenta l'oggetto del giocatore 
            // Il metodo getResource ci permette di andare nella cartella Img 
        // quando passiamo un immagine si può verificare un eccezione e pertanto bisogna che venga gestita in un try-catch    
        }catch(Exception e){
            System.err.println(e); 
            JOptionPane.showMessageDialog(null, "Errore caricamento Razzo", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        // Definiamo la larghezza e l'altezza dell'oggetto razzo
        setWImage(getImage().getIconWidth());
        setHImage(getImage().getIconHeight());
    }
    
    // Sovascrittura del metodo move 
    // Metodo che setta la nuova posizione del razzo quando viene spostato 
    @Override
    public void move(){
        lastX=getX(); 
        // Settiamo il movimento che sarà uguale 
        // alla posizione + lo spostamento compiuto
        setX(lastX+spostamento); 
    }
  
    // Metodo per definire il movimento del razzo tramite il mouse 
    public void moveMouse(MouseEvent e){
        if(e.getX()==lastX) // se la posizione corrente è uguale alla precedente il razzo rimane nella stessa posizione
            return;
        setX(e.getX()); // altrimenti setta la nuova posizione
        move();
    }
    
    // Metodo per definire il movimento del razzo tramite le freccette
    public void moveFreccette(KeyEvent e){
        int key = e.getKeyCode();
        // In base al tasto premuto verrà eseguito il metodo spostaDestra() o spostaSinistra()
        if (key == KeyEvent.VK_LEFT) 
            spostaSinistra();
        else if (key == KeyEvent.VK_RIGHT)
            spostaDestra();
        move();
    }
    
    // Metodo per far spostare a destra il razzo con la freccetta
    public void spostaDestra() {
        if(getX() < 680 - getWImage()) {
            int X = getX();
            setX(X += vel);           
        }
    }
    
    // Metodo per far spostare a sinistra il razzo con la freccetta
    public void spostaSinistra() {
        if(getX() > 0) {
            int X = getX();
            setX(X -= vel);
        }
    }
    
}
