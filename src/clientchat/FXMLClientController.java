/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Teuku Hashrul
 * 
 */
public class FXMLClientController implements Initializable {

   
    private ClientAccepter clientAccepter;
    @FXML
    private Button btn_Send;
    
    @FXML
    private TextField et_Input;

    @FXML
    private TextArea tv_Receive;

    @FXML
    private Button btn_Connect;
    
    @FXML
    private Label label_Warning;

    @FXML
    void openConnection(ActionEvent event) throws IOException {
        this.clientAccepter = new ClientAccepter(this);
        this.clientAccepter.start();       
    }
    
    @FXML
    void closeConnection(ActionEvent event) throws IOException{
        this.clientAccepter.sendMessage("exit");
        this.clientAccepter.shutdown();
        this.clientAccepter = null;
    }
    
    @FXML
    void sendMessage(ActionEvent event) throws IOException {
       if(this.clientAccepter == null){
           this.label_Warning.setText("You should connect first");
       }else{
           this.label_Warning.setText("");
           String newMessage = this.et_Input.getText();
           this.clientAccepter.sendMessage(newMessage);
       }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      

            

    }
    
    public void setTextArea(String message){
        this.tv_Receive.setText(this.tv_Receive.getText()+"\n "+message);
    }
    
    public void resetEditText(){
        this.et_Input.setText("");
    }
    
    public void serverClosedMessage(){
        this.label_Warning.setText("Server not Online !");
    }
    
    /**
     * matiin data input di receiver 
     * matiiin dataoutput di accepter 
     * matiin socket 
     */
    public void setOffAll(){
        try {
            this.clientAccepter.shutdown();
        } catch (IOException ex) {
            Logger.getLogger(FXMLClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
