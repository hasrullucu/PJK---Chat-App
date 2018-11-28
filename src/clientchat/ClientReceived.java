
package clientchat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

/**
 *
 * @author Teuku Hashrul
 */
public class ClientReceived extends Thread {
    private DataInputStream inFromServer ; 
    private FXMLClientController clientController;
    private Thread myThread ;
    

    public ClientReceived(FXMLClientController clientController ,  Socket socket) throws IOException {
        this.myThread = new Thread();
        this.clientController = clientController;   
        this.inFromServer = new DataInputStream(socket.getInputStream());
        
    }

    @Override
    public void run() {
        try {
            // TODO
            String message = "";
            while (!message.equals("exit")) {
                message = inFromServer.readUTF();
                this.clientController.setTextArea("Server : " +message);

            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLClientController.class.getName()).log(Level.SEVERE, null, ex);
           
        } //To change body of generated methods, choose Tools | Templates.
    }
    
    public void shutDown() {
        try {
            this.inFromServer.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientReceived.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}