
package ProgettoINGSW.Gioco;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Proiettili extends Oggetto {
    private String fileimg;
    private boolean visible=true;

    public Proiettili(){
    }
    
    public Proiettili(int x, int y) {
        super(x, y);
        try {
            fileimg = getImageFile();
            fileimg = "/Img/missile.jpg"; // definiamo il nome del file che conterrà le immaggine del missile
            setImage(new ImageIcon(getClass().getResource(fileimg))); // associamo l'immagine del missile  
            // Il metodo getResource ci permette di andare nella cartella Img 
            // quando passiamo un immagine si può verificare un eccezione e pertanto bisogna che venga gestita in un try-catch    
        }catch(Exception e){
            System.err.println(e); 
            JOptionPane.showMessageDialog(null, "Errore caricamento Razzo", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        setPunteggio(8);// settiamo il punteggio = a 8
        
        // Definiamo la larghezza e l'altezza dell'oggetto missile
        setWImage(getImage().getIconWidth());
        setHImage(getImage().getIconHeight());
        
        int H_SPACE = 4; //posizione di distanza dello sparo dall'oggetto razzo secondo l'asse x
        setX(x + H_SPACE);

        int V_SPACE = 1; //posizione di distanza dello sparo dall'oggetto razzo secondo l'asse y
        setY(y - V_SPACE);
    }

    public void die() {
        visible = false;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    protected void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    @Override
    public void move() {
    }
    
}
