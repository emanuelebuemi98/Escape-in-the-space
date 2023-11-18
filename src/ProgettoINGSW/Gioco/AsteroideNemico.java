package ProgettoINGSW.Gioco;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class AsteroideNemico extends Oggetto{
    private String fileimg;
    
    public AsteroideNemico(int x,int y, byte level){
        super(x, y, level);
        int r;
        // In base al tipo di meteorite attribuiamo un punteggio
        switch((new Random()).nextInt(3)){ // Viene generato un numero random tra 1 e 3 
            case 1:
                setPunteggio(2);
                r=1;break;
            case 2:
                setPunteggio(3);
                r=2;break;
            default:
                setPunteggio(1);    
                r=3;break;
        }
        try{
            fileimg = getImageFile(); 
            fileimg = "/Img/meteorite_0"+r+".jpg"; // definiamo il nome del file che conterrà le immaggini dei meteoriti
            setImage(new ImageIcon(getClass().getResource(fileimg))); // associamo le 3 immagini dei metioriti
        // quando passiamo un immagine si può verificare un eccezione e pertanto bisogna che venga gestita in un try-catch 
        }catch(Exception e){
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Errore caricamento Astreroide Nemico", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        setWImage(getImage().getIconHeight());
        setHImage(getImage().getIconHeight());
    }
    
    // Sovascrittura del metodo move 
    // Metodo che definisce la velocità di caduta dei meteoriti 
    @Override
    public void move(){
        int Y = getY();
        // La velocità di caduta dell'oggetto nemico aumenterà man mano che il livello di gioco aumenta
        setY(Y+=1+getLevel());  
    }
    
    
}
