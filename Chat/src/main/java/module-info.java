module gbchat.chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens gbchat.chat to javafx.fxml;
    exports gbchat.chat;
}