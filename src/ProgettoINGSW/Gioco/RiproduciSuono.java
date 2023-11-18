package ProgettoINGSW.Gioco;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class RiproduciSuono {
    private Clip clip;

    // Costruttore che inizializza l'oggetto RiproduciSuono con un file audio specificato
    public RiproduciSuono(String filename) {
        try {
            // Creiamo un oggetto File dal percorso del file audio
            File fileAudio = new File(filename);
            // Controlliamo se il file esiste e viene trovato
            if (!fileAudio.exists()) { 
                System.err.println("File Wav non trovato: " + filename); 
                return;
            }  
             // Otteniamo un flusso di input audio da questo file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileAudio);
            // Otteniamo un oggetto Clip e lo apriamo con il flusso di input audio
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    // Metodo per avviare la riproduzione dell'audio
    public void start() {
        // Verifichiamo che l'oggetto Clip esista e non stia già riproducendo
        if (clip != null && !clip.isRunning()) {
            clip.start(); //avvio flusso audio
        }
    }

    // Metodo per interrompere la riproduzione dell'audio
    public void stop() {
        // Verifichiamo che l'oggetto Clip esista e stia attualmente riproducendo
        if (clip != null && clip.isRunning()) {
            clip.stop(); //Interruzione flusso audio
            clip.setFramePosition(0);
        }
    }
}