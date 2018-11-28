
package clientchat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Teuku Hashrul
 * 
 * PAKAI MULTITHREADING 
 * MAIN THREAD UI CLIENT
 * BACKGROUND THREAD 1 : CLIENT SEND
 * BACKGROUND THREAD 2 : CLINET RECEIVE 
 * 
 * MAIN THREAD UI SERVER
 * BACKGROUND THREAD 1 : SERVER SEND 
 * BACKGROUND THREAD 2 : SERVER RECEIVE
 * 
 * BIKIN 2 STAGE BUAT 2 TAMPILAN 
 * BIKIN MAIN CLASS AJA 
 * BIKIN FXML CONTROLLER 
 * SAMA XML 
 * 
 * NANTI DI MAIN CLASS UTAMA 
 * BIKIN STAGE SAMA KAYA DI PERTAMA 
 * 
 * SOCKET PROGRAMMING 
 * DATA INPUT STREAM readUTF 
 * DATA OUTPUT STREAM writeUTF 
 * 
 * 
 * server : 
 * port number 
 * 
 */
public class ClientChat extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderClient = new FXMLLoader(getClass().getResource("FXMLClient.fxml"));
        Parent root = loaderClient.load();
        FXMLClientController clientController = loaderClient.getController();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Client - pengantar Jarkom");
        stage.setX(300);
        stage.setY(300);
        stage.show();
        
//        GAP

        FXMLLoader loaderServer = new FXMLLoader(getClass().getResource("FXMLServer.fxml"));
        Parent root2 = loaderServer.load();
        Scene scene2 = new Scene(root2);
        ServerController serverController = loaderServer.getController();
        Stage newStage = new Stage(StageStyle.DECORATED);
        newStage.setScene(scene2);
        newStage.setTitle("Server - Pengantar Jarkom");
        newStage.setX(700);
        newStage.setY(300);
        newStage.show();
//        newStage.setOnCloseRequest(e -> {
//            serverController.setOffAll();
//        });
//        
//        stage.setOnCloseRequest(e ->{ 
//            clientController.setOffAll();
//        });
       

        
        
        
        
        
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
