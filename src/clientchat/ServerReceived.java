/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

/**
 *
 * @author Teuku Hashrul
 */
public class ServerReceived extends Thread{
    private DataInputStream inFromClient ; 
    private Thread myThread;
    private ServerController serverController ; 
    
    public ServerReceived(ServerController serverController , Socket socket) throws IOException{
        this.myThread = new Thread();
        this.serverController = serverController;
        this.inFromClient =new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
         //To change body of generated methods, choose Tools | Templates.
         
           try {
            // TODO
            String message = "";
            while (!message.equals("exit")) {
                message = inFromClient.readUTF();
                this.serverController.setTextArea("Client : "+message);

            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void closeInput(){
        try {
            this.inFromClient.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerReceived.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
