package it.fi.meucci;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Classe che si occupa di creare e gestire un server, riceve un singolo client e inverte la stringa che gli viene inviata
 * @author Dardan Matias Berisha
 */
public class Server {
    
    protected ServerSocket server       = null;
    protected Socket client             = null;
    
    protected String stringaRicevuta    = null;
    protected String stringaModificata  = null;
    protected BufferedReader inDalClient;
    
    protected DataOutputStream outVersoClient;

    protected int porta;
    
    public Server(int porta) {
        this.porta = porta;
    }
    
    /**
     * Metodo che fa partire e mette in attesa di un client il server
     * @return Ritorna il socket del client
     */
    public Socket attendi(){
        try {
            
            System.out.println("1 SERVER - partito in esecuzione...");
            
            server = new ServerSocket(porta); //creazione di un server su una porta
            
            client = server.accept(); //rimane in attesa di un client
            
            server.close(); //chiudo il server per evitare che arrivino altri client
            
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));   //associo il socket al client per effettuare la lettura
            outVersoClient  = new DataOutputStream(client.getOutputStream());                   //associo il socket al client per effettuare la scrittura
            
        } catch (Exception e) {System.out.println("Errore durante l'istanza del server");}
        return client;
    }
    
    /**
     * Metodo che riceve e modifica la stringa inviata dal client.
     * Il metodo resterà in attesa finché non arriva un client.
     */
    public void comunica(){
        try{
            //prima di eseguire la prossima linea si aspetta l'arrivo di un client
            System.out.println("3 SERVER - Benvenuto client, ti rimanderò la stringa inviata all'incontrario. Attendo...");
            stringaRicevuta = inDalClient.readLine(); //si legge il primo messaggio che si riceve dal client (smetterà di leggere al carattere \n)
            System.out.println("6 SERVER - Il messaggio è: \"" + stringaRicevuta + "\"");
            
            stringaModificata = invertiStringa(stringaRicevuta); //si usa il metodo per invertire la stringa
            System.out.println("7 SERVER - Invio la stringa modificata al client...");
            outVersoClient.writeBytes(stringaModificata + "\n"); //si invia la stringa all'incontrario al client
            
            System.out.println("9 SERVER - Fine elaborazione");
            client.close(); //si chiude la comunicazione col client
            
        }catch(Exception e){
            System.out.println("SERVER - Errore durante la comunicazione");
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Metodo che inverte la stringa che gli viene passa per il parametro str
     * @param str stringa da invertire
     * @return stringa inveritta
     */
    private String invertiStringa(String str){
        StringBuilder s = new StringBuilder(str);
        s.reverse();
        return s.toString();
    }
    
}
