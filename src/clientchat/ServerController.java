package clientchat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Teuku Hashrul
 */
public class ServerController implements Initializable {
       
                

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button btn_Send;
    @FXML
    private TextField et_Send;

    @FXML
    private TextArea tv_Receive;

    @FXML
    private Button btn_Connect;
    
    @FXML
    private Label label_Warning;
    
    private ServerAccepter serverAccepter;
    @FXML
    void openConnection(ActionEvent event) throws IOException {
        this.serverAccepter = new ServerAccepter(this);
        this.serverAccepter.start();
    }
    @FXML
    void shutDown(ActionEvent event) throws IOException{
        this.serverAccepter.shutDown();
        this.serverAccepter = null;
    }
    @FXML
    void sendMessage(ActionEvent event) throws IOException {
       if(serverAccepter == null){
           this.label_Warning.setText("you should have Connect first");
       }else{
           this.label_Warning.setText("");
           String message = this.et_Send.getText();
           this.serverAccepter.sendMessage(message);
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         this.setTextArea("Server Should have connect first then Client !");
    }    
    
    public void setTextArea(String message){
        this.tv_Receive.setText(this.tv_Receive.getText()+"\n"+message);
    }
    
     public void resetEditText(){
        this.et_Send.setText("");
    }
     
     
    public void setOffAll(){
         try {
             this.serverAccepter.shutDown();
         } catch (IOException ex) {
             Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
