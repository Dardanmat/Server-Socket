package it.fi.meucci;

/**
 *
 * @author Dardan Matias Berisha
 */
public class App {
    public static void main(String[] args) {
        
        int porta = 5000; //porta sulla quale si deve servire il client
        
        Server server = new Server(porta);
        server.attendi();
        server.comunica();
    }
}
