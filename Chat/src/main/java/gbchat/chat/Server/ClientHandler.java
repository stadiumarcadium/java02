package gbchat.chat.Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String name;
    private boolean isAuth;
    private boolean atomic = true;

    public String getName() {
        return name;
    }

    private int authTimer;

    public int getAuthTimer() {
        return authTimer;
    }

    public void setAuthTimer(int authTimer) {
        this.authTimer = authTimer;
    }

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            new Thread(() -> {
                try {
                    authentication();
                    if (isAuth) readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();

        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    public void authentication() throws IOException {
        while (true) {
            final String str = in.readUTF();
            if (str.equals("/end")) {
                isAuth = false;
                break;
            }
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authok " + nick);
                        name = nick;
                        myServer.broadcastMsg(name + " зашел в чат");
                        myServer.subscribe(this);
                        isAuth=true;
                        return;
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                } else {
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }

    public void readMessages() throws IOException {
        while (atomic) {
            String strFromClient = in.readUTF();
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.startsWith("/")) {
                if (strFromClient.equals("/end")) {
                    break;
                }
                if (strFromClient.startsWith("/w ")) {
                    String[] tokens = strFromClient.split("\\s");
                    String nick = tokens[1];
                    String msg = strFromClient.substring(4 + nick.length());
                    myServer.sendMsgToClient(this, nick, msg);
                }
                continue;
            }
            myServer.broadcastMsg(name + ": " + strFromClient);
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (!name.isEmpty()) {
            myServer.unsubscribe(this);
            myServer.broadcastMsg(name + " вышел из чата");
            System.out.println(name + " вышел из чата");
            sendMsg("/end");
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAtomic(boolean atomic) {
        this.atomic = atomic;
    }

    public boolean getAtomic() {
        return atomic;
    }
}