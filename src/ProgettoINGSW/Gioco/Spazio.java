
package ProgettoINGSW.Gioco;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Spazio extends JPanel {
    private final int width=700;
    private final int higth=450;
    private final Image background;
    private final Razzo razzo; 
    private int vite;
    private int punti;
    private byte level;
    private boolean pause,invulnerabile;
    private Timer timer;
    private Vector<Oggetto> array;
    private JLabel lab;
    private String user;
    private Proiettili proiettile;
    private String explImg; 
    
    
    public Spazio(int width,int higth, String us) {
    
        ImageIcon referencia = new ImageIcon(getClass().getResource("/Img/sfondoGiocoUtente.jpg"));
        background = referencia.getImage();
        lab = new JLabel();
        lab.setText(us);
        user=us;
        setFocusable(true); // imposta la capacità dell'oggetto di ricevere il focus
        setDoubleBuffered(true);
        setSize(this.width,this.higth); // passiamo le dimendioni dello sfondo
        
        razzo = new Razzo(); // Istanziamo un oggetto di tipo Razzo  
        razzo.setY(this.higth-razzo.getHImage()-45); // partendo dal basso definisce la distanza del razzo dal riquadro in basso
        proiettile = new Proiettili(); 
        
        
        addMouseMotionListener(new MouseMotionAdapter(){
          @Override
          public void mouseMoved(MouseEvent e){ // questo metodo viene richiamato quando il mouse viene mosso all'interno del riquado di gioco
              if(razzo.getX() > 0 || razzo.getX()<680) { // per gestire i bordi del pannello
                 razzo.moveMouse(e); // quando muoviamo il mause dentro il riquadro di gioco il razzo si muoverà 
              }
          }
        });
     
        addKeyListener(new KeyAdapter(){
          @Override
          public void keyPressed(KeyEvent e){ // questo metodo viene richiamato quando si preme sui tasti 
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT: // quando schiacciamo il tasto della freccia a sinistra il razzo si muoverà a sinistra
                    razzo.moveFreccette(e);
                    break;
                case KeyEvent.VK_RIGHT: // quando schiacciamo il tasto della freccia a destra il razzo si muoverà a destra
                    razzo.moveFreccette(e);
                    break;
                case KeyEvent.VK_SPACE: // quando schiacciamo il tasto della bara spaziatrice il razzo sparerà un missile
                    if (!proiettile.isVisible()) {
                        int x = razzo.getX();
                        int y = razzo.getY();
                        proiettile = new Proiettili(x, y);
                        proiettile.setVisible(true);
                    }
                    break;
                case KeyEvent.VK_P: // quando schiacciamo il tasto P il gioco si metterà in pausa 
                    pause=!pause; 
                    repaint(); // questo metodo ridisegna ---> ripremendo P il gioco riparte                   
                    break;
            }
          }
        });
    }

    // Metodo per disegnare le varie figure e le varie scritte relative ai punteggi
    @Override
    public void paint(Graphics g){
        if(pause==true){
            g.setFont(new Font("Serif",Font.BOLD+Font.ITALIC,50)); 
            g.setColor(Color.YELLOW); // colore scritta PAUSA 
            g.drawString("PAUSA",120,higth/2); // scritta Pausa e posizione nello sfondo
            return;
        }
        super.paint(g);
        // La classe Graphics2D estende la classe Graphics per fornire un controllo più sofisticato per la gestione del layout
        Graphics2D g2D=(Graphics2D)g;    
        g2D.drawImage(background, 0, 0, null); //Disegnamo l'immagine dello sfondo
   
        //Inseriamo le scritte del riquado in alto a sinistra del gioco
        g.setFont(new Font("Serif",Font.BOLD,13)); 
        g.setColor(Color.WHITE); // colore scritta Livello
        g.drawString("Utente: "+user,10,20);
        g.setColor(Color.WHITE); // colore scritta Utente
        g.drawString("Livello: "+level,10,40); 
        g.setColor(Color.WHITE); // colore scritta Vite
        g.drawString("Vite: "+vite,10,60);  
        g.setColor(Color.WHITE); // colore scritta Punti
        g.drawString("Punti: "+punti,10,80);
        g.setColor(Color.WHITE);
        
        // Disegno scritta GAME OVER
        if(vite==0){
            g.setFont(new Font("Serif",Font.ROMAN_BASELINE,70)); 
            g.setColor(Color.RED); 
            g.drawString("GAME OVER",130,100);
        }
    
        //Disegno razzo
        razzo.getImage().paintIcon(this, g2D, razzo.getX(), razzo.getY());
        
         //Disegno missile 
        if (proiettile.isVisible()) {
            try {
                proiettile.getImage().paintIcon(this, g2D,proiettile.getX(),proiettile.getY());              
            } catch (NullPointerException ex) {
                System.out.println("");
            }
        }    
    
        //Disegno gli asteroidi e la navicella nemica
        for(Oggetto o:array)
            if(o.getY()>=0) 
                o.getImage().paintIcon(this, g2D, o.getX(), o.getY());
    
        //Cancello gli oggetti nemici usciti dallo schermo
        for(int i=array.size()-1;i>=0;i--){
            if(array.get(i).getY()>=higth){
                // Ogni volta che un oggetto nemico viene cancellato attribuiamo il punteggio per quel tipo di oggetto 
                punti+=(int)array.get(i).getPunteggio(); 
                array.remove(i);
            }
        }
        g.dispose(); // Metodo per disporre le figure
    }
  
    // Metodo che permette di aggiungere in modo random i meteoriti e le navicelle nemiche
    private void nemici(){
        array=new Vector<Oggetto>();
        Random rand=new Random(); // istanziamo un oggetto di tipo Random
    
        //Aggiungo meteoriti
        for(int i=0;i<level*20;i++){ 
            int x=rand.nextInt(width); // per aggiungere i meteoriti su una riga
            // per far scendere dall'alto i meteoriti (partendo da 0, scendendo verso il basso: -1,-2,-3,-4...)
            int y=0-rand.nextInt(higth*2*level); 
            Oggetto o=new AsteroideNemico(x,y,level);
            if(collisione(o)) // per ogni collisione con l'oggetto diminuiscono il numero di meteoriti
                i--;
            else
               array.add(o); // altrimenti lo aggiungo
        }
    
        //Aggiungi Astronavi Nemiche
        for(int i=0;i<level*5;i++){
            int x=rand.nextInt(width);
            int y=0-rand.nextInt(higth*2*level);
            Oggetto o=new NavicellaNemica(x,y,level);
            if(collisione(o)) 
                i--; 
            else
                array.add(o);
        }
    }

    // Metodo che per ogni oggetto dell'array (che contiene solo oggetti nemici) ci dice se ci è stata una collisione tra il razzo e gli oggetti nemici
    private boolean collisione(Oggetto o){
        for(Oggetto x:array) // per ogni oggetto dell'array
            if(o.collisioneOggetto(x)) // se l'oggetto nemico collide con l'oggetto giocatore  
                return true; // ritorna true che sta a significare che c'è stata una collisione, altrimenti ritorna false
        return false;
    }
    
    //Metodo che ci permette di gestire la collisione del proiettile con i vari oggetti nemici
    private void collisioneProiettile(){
        for(int i=array.size()-1;i>=0;i--) {
            if(array.get(i).collisioneOggetto(proiettile)) {
                punti+=(int)proiettile.getPunteggio();
                explImg = "/Img/esplosione.png";
                proiettile.setImage(new ImageIcon(getClass().getResource(explImg)));
                array.remove(i);
                try { 
                    Thread.sleep(150);
                    proiettile.setVisible(false);
                } catch (InterruptedException ex) { 
                    Logger.getLogger(LancioGioco.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }

    //Metodo che ci permette di gestire la collisione del razzo con i vari oggetti nemici
    private void collisioneRazzo(){
        if(invulnerabile) // se siamo allo stato invulnerabile non succede nulla
            return;
        for(Oggetto o:array){  // per ogni oggetto dell'array
            if(o.collisioneOggetto(razzo))
                colpito(); // quando l'oggetto tocca il razzo viene richiamato il metodo colpito
        }
    }

    // Metodo che gestisce le dinamiche di quando il razzo verrà colpito dai meteoriti/navicelle
    private void colpito(){
        vite--; // ogni volta che si attiva la funzione colpito le vite vengono decrementate
        if(vite==0){ // quando la vita diventa = a 0 finisce la partita
            JOptionPane.showMessageDialog(null,
              "Punteggio: " + (int)punti + " \nLivello: " + level, // comparirà un messaggio che ci dirà, Game Over
              "Fine gioco", JOptionPane.INFORMATION_MESSAGE); // il punteggio fatto e un messaggio di fine gioco 
            memorizzaPunti();
            finePartita(); // premendo il tasto "OK" apparià successivamente il pannello per decidere se rigiocare o no
            return;
        }
        invulnerabile=true; // quando si verrà colpiti si attiverà la variabile invulnerabile che ci permette di non subire danni per pochi secondi
        (new Timer()).schedule(new TimerTask(){ // Il metodo schedule è un metodo della classe Timer che permette di definire un timer in millisecondi
          @Override
          public void run(){ // allo scadere di 1500 millisecondi si ridiventerà vulnerabili e lo sfondo diventa nuovamente nero
              invulnerabile=false;             
          }
        }, 1500);
    }

    // Metodo che permette di avviare il gioco e che andrà a gestire le varie dinamiche del gioco
    protected boolean play(){
        level=1; // primo livello
        vite=3; // massimo numero di vite del
        punti=0; //variabile che segna il punteggio giocatore
        pause=invulnerabile=false; 
        nemici(); // per aggiungere i vari oggetti nemici
        timer=new Timer(); // istanziamo il Timer per utilizzare il metodo scheduleAtFixedRate
        // Lo scheduleAtFixedRate è il metodo della classe Timer che viene utilizzato per pianificare le varie 
        // attività relative ai vari oggetti a velocità di esecuzione fissa (andrà a gestire le varie dinamiche del gioco)
        // Inizierà dopo il ritardo specificato.
        timer.scheduleAtFixedRate(new TimerTask(){ 
          @Override
          public void run(){
            if(pause)  // in caso di pausa, in quanto è impostata false, non fa niente
                return;
                razzo.move(); // il razzo si muoverà
            if (proiettile.isVisible()) {
               // proiettile.move();
                int velY = proiettile.getY();
                velY -= 8;
                if (velY < 0) {
                    proiettile.die();
                } else {
                   proiettile.setY(velY);
                }
                collisioneProiettile();
            }
            for(Oggetto o:array) 
                // In base all'oggetto dell'array viene richiamato il suo metodo move 
                // (Polimorfismo ---> dynamic binding)
                o.move(); 
                repaint();
            if(array.isEmpty()){ // appena il numero di oggetti nemici si svuolta 
                level++; // passiamo al livello successivo
                nemici(); // richiamiamo la funzione nemici che aggiunge i vari oggetti nemici 
                return;
            }
            collisioneRazzo(); // richiamiamo la funzione per la collisione con il razzo 
          }
        }, 100, 25); // il metodo scheduleAtFixedRate prevede un ritardo e un periodo di ritardo
        return false;
    }

    // Riquadro che compare quando finisco la partita
    private void finePartita(){
        timer.cancel();
        // Creiamo un bottone con "YES" o "NO" affinchè l'utente decida di rigiocare oppure uscire
        int r=JOptionPane.showConfirmDialog(null,"Vuoi Iniziare una nuova partita?",
            "Nuova Partita",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(r==JOptionPane.YES_OPTION){
            play(); // se preme sul tasto "YES" ricomincia la partita
        }else{
            FinestraBottoni f = new FinestraBottoni(user); // se preme sul tasto "NO" ritorniamo alla schermata di gioco
            setVisible(false);
        }
    }
    
    //Metodo per memorizzare nel database il risultato ottenuto dall'utente per la partita giocata
    private void memorizzaPunti(){
        user = lab.getText();
        int p = punti;
        int l = level;
        PreparedStatement ps;
        //selezionare il miglior punteggio e lo si confronta con i punti 
        String queryInsert = "INSERT INTO `classifica`(`User`,`Punteggio`,`Livello`) VALUES (?,?,?) ";
        try {
                Connection connection = ConnesioneDB.getConnection();  // Otteniamo la connessione utilizzando l'interfaccia Connection
                ps = connection.prepareStatement(queryInsert);
                ps.setString(1, user);
                ps.setInt(2, p);
                ps.setInt(3, l);
                ps.executeUpdate();
         } catch (SQLException ex) {
                Logger.getLogger(FinestraBottoni.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
