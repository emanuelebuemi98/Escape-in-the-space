
package ProgettoINGSW.Gioco;

// La classe Utenti è la classe che abbiamo utilizzato per definire i costruttori e 
// i metodi getter per accedere ai dati relativi agli utenti.
public class Utenti {
    private String nomeUtente;
    private String username;
    private String password;
    private String domandaDiSicurezza;
    private String Risposta;
    private String DataDiNascita;
    private String email;
    private String commenti;
    private int punteggioMax;
    private int livello;

    //Costruttore utilizzato nella classe FinestraOspiti
    public Utenti(String username, String commenti) {
        this.username = username;
        this.commenti = commenti;
    }

    //Costruttore utilizzato nella classe FinestraBottoni
    public Utenti(String username, int punteggioMax, int livello) {
        this.username = username;
        this.punteggioMax = punteggioMax;
        this.livello = livello;
    }
    
    //Costruttore utilizzato nella classe FinestraAdmin 
    public Utenti(String nomeUtente, String username, String password, String commenti) {
        this.nomeUtente = nomeUtente;
        this.username = username;
        this.password = password;
        this.commenti = commenti;
    }
    
    //Metodi Getter
    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDomandaDiSicurezza() {
        return domandaDiSicurezza;
    }

    public String getRisposta() {
        return Risposta;
    }

    public String getDataDiNascita() {
        return DataDiNascita;
    }

    public String getEmail() {
        return email;
    }

    public String getCommenti() {
        return commenti;
    }

    public int getPunteggioMax() {
        return punteggioMax;
    }

    public int getLivello() {
        return livello;
    }
    
    
}
