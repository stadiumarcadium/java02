package gbchat.chat;

import gbchat.chat.Client.ChatClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ClientApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.show();
//        ClientController controller = fxmlLoader.<ClientController>getController();
//        stage.setOnCloseRequest(event -> {
//            System.out.println("закрытие");
//            controller.client.sendMessage("/end");
//            controller.client.closeConnections();
//        });
    }

    public static void main(String[] args) {
        launch();
    }
}