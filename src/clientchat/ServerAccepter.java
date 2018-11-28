
package clientchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teuku Hashrul
 */
public class ServerAccepter extends Thread {
    private ServerController serverController ;
    private Thread myThread ;
    private final int PORT_NUMBER = 6666; 
    private ServerSocket serverSocket ; 
    private Socket socket;
    
    private DataOutputStream outToClient ; 
   
    
    private ServerReceived serverReceived;
    
    public ServerAccepter(ServerController serverController){
        this.serverController = serverController;
        this.myThread = new Thread();
        
    }
    
    public void sendMessage(String message){
        if(outToClient == null){
            
        } else{
            try {
                this.outToClient.writeUTF(message);
                this.serverController.setTextArea("Server : "+message);
                this.serverController.resetEditText();
            } catch (IOException ex) {
                Logger.getLogger(ServerAccepter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
   

    @Override
    public void run() {
        try {
            //To change body of generated methods, choose Tools | Templates.
            this.serverSocket = new ServerSocket(PORT_NUMBER);
            this.serverController.setTextArea("waiting Connection");
            this.socket = serverSocket.accept();
            this.serverController.setTextArea("Connected With Client");
            
            this.outToClient = new DataOutputStream(this.socket.getOutputStream());
            
            this.serverReceived = new ServerReceived(this.serverController , this.socket);
            this.serverReceived.start();
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerAccepter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void shutDown() throws IOException{
        this.outToClient.close();
        this.serverReceived.closeInput();
        this.socket.close();
    }

   
    
    
    
}
