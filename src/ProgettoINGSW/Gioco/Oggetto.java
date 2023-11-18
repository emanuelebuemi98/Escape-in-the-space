
package ProgettoINGSW.Gioco;

import java.awt.Rectangle;
import javax.swing.ImageIcon;


public abstract class Oggetto {
    private int x;
    private int y; 
    private byte level;
    private int hImage; 
    private int wImage; 
    private int punteggio;
    private String imageFile;
    private ImageIcon image;

    // Costruttore che verrà ereditato dalla classe Razzo
    public Oggetto() {
    }
    
    public Oggetto(int x, int y) {
        this.x = x;
        this.y = y;
    } 
    
    // Costruttore che verrà ereditato dalle classi NavicellaNemica e AsteroideNemico
    public Oggetto(int x, int y, byte level) {
        this.x = x;
        this.y = y;
        this.level = level;
    } 

    // Metodi getter e setter utilizzati per controllare l’accesso degli attributi della classe
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x=x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y=y;
    }
    public byte getLevel() {
        return level;
    }
    public int getHImage(){
        return hImage;
    }
    public int getWImage(){
        return wImage;
    }
    public void setHImage(int hImage) {
        this.hImage = hImage;
    }
    public void setWImage(int wImage) {
        this.wImage = wImage;
    }
    public int getPunteggio(){
        return punteggio;
    }
    public void setPunteggio(int p){
        punteggio=p;
    }
    public String getImageFile() {
        return imageFile;
    }
    public ImageIcon getImage(){
        return image;
    }
    public void setImage(ImageIcon image) {
        this.image = image;
    }
    
    // Metodo astratto che servirà per definire il movimento dei vari oggetti.
    // Tale movimento varia a secondo del tipo di oggetti e pertanto 
    // il movimento sarà implementato nelle varie sottoclassi
    public abstract void move();
    
    // Metodo che definisce la collisione tra l’oggetto Razzo e gli altri oggetti.
    public boolean collisioneOggetto(Oggetto o){
        return (new Rectangle(x,y,wImage,hImage)).intersects(
                  new Rectangle(o.getX(),o.getY(),o.getWImage(),o.getHImage()));
    }
  
}
