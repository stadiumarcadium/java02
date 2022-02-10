package gbchat.chat.Client;

import gbchat.chat.ClientController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.Socket;

public class ChatClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientController controller;

    public ChatClient(ClientController controller) {
        this.controller = controller;
        openConnection();
    }

    private void openConnection() {
        try {
//            setAuthorized(false);
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String messageFromServer = in.readUTF();
                            if (messageFromServer.startsWith("/authok ")) {
//                            setAuthorized(true);
                                final String myNick = messageFromServer.split("\\s")[1];
                                controller.addMessage("Успешная авторизация под ником " + myNick);
                                continue;
                            }
//                            controller.addMessage(messageFromServer);
//                        }
//                        while (true) {
//                            String messageFromServer = in.readUTF();
                            if (messageFromServer.startsWith("/end")) {
                                System.out.println(messageFromServer);
                                sendMessage("/end");
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            controller.addMessage(messageFromServer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
//                    setAuthorized(false);
//                        myNick = "";
                        closeConnections();
                        System.exit(0);
                    }
                }
            });
//            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            controller.addMessage("Не удалось подключиться к серверу");
            e.printStackTrace();
        }
    }

//    public void onAuthClick() {
//        if (socket == null || socket.isClosed()) {
//            start();
//        }
//        try {
//            out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
//            loginField.setText("");
//            passField.setText("");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void closeConnections() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
