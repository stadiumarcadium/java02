package gbchat.chat;

import gbchat.chat.Client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {
    @FXML
    private TextField messageFiled;
    @FXML
    private TextArea messageArea;
    final ChatClient client;

    public ClientController() {
        client = new ChatClient(this);
    }

    public void onClickCheckButton(ActionEvent actionEvent) {
        final String message = messageFiled.getText();
        if (!message.isEmpty() && message != null)
//            messageArea.appendText(message + "\n");
        client.sendMessage(message);
        messageFiled.clear();
        messageFiled.requestFocus();
    }

    public void addMessage(String message) {
        messageArea.appendText(message + "\n");
    }
}