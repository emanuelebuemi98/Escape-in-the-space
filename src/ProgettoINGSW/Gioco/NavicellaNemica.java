
package ProgettoINGSW.Gioco;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class NavicellaNemica extends Oggetto{
    private String fileimg;
    
    public NavicellaNemica(int x,int y, byte level){
        super(x, y, level);
        try{
            fileimg = getImageFile();
            fileimg = "/Img/navicella.jpg"; // definiamo il nome del file che conterrà le immaggine della navicella
            setImage(new ImageIcon(getClass().getResource(fileimg))); // associamo l'immagine della navicella nemica
        // quando passiamo un immagine si può verificare un eccezione e pertanto bisogna che venga gestita in un try-catch    
        }catch(Exception e){ 
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Errore caricamento Navicella Nemica", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        setPunteggio(5); // settiamo il punteggio a 5 ogni volta che la navicella nemica non ci colpisce
        
        // Definiamo la larghezza e l'altezza dell'oggetto astronave nemica
        setWImage(getImage().getIconHeight());
        setHImage(getImage().getIconHeight());
    }
    
    // Sovascrittura del metodo move 
    // Metodo che definisce la velocità di caduta delle navicelle
    @Override
    public void move(){
        int Y = getY();
        // La velocità di caduta dell'oggetto nemico aumenterà man mano che il livello di gioco aumenta
        setY(Y+=1+getLevel()); 
    }
    
}
