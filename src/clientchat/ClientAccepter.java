
package clientchat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teuku Hashrul
 */
public class ClientAccepter extends Thread {
    private FXMLClientController clientController ;
    private Thread myThread ;
    private final int PORT_NUMBER = 6666 ; 
    private final String IP_ADRESS = "localhost";
    private Socket socket;
    
    private DataOutputStream outToServer; 
    
    private ClientReceived clientReceived;
    
    public ClientAccepter(FXMLClientController clientController ){
        this.clientController = clientController; 
        this.myThread = new Thread();
        
    }
    
    public void sendMessage(String message) throws IOException{
        if(outToServer == null){
            
        }else{
            this.outToServer.writeUTF(message);
            this.clientController.setTextArea("Client : "+message);
            this.clientController.resetEditText();
        }
    }

    @Override
    public void run() {
        try {
            //To change body of generated methods, choose Tools | Templates.
            this.socket = new Socket(IP_ADRESS, PORT_NUMBER);
            this.clientController.setTextArea("Connected With Server");
            
            this.outToServer = new DataOutputStream(this.socket.getOutputStream());
            this.clientReceived = new ClientReceived(this.clientController , this.socket);
            this.clientReceived.start();
            
        } catch (IOException ex) {
            Logger.getLogger(ClientAccepter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("salah coy ");
            this.clientController.setTextArea("salah coy");
            this.clientController.serverClosedMessage();
        }
    }
    
    public void shutdown() throws IOException{
       this.outToServer.close();
       this.clientReceived.shutDown();
       this.socket.close();
    }
}
