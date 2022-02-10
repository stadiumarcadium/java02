package gbchat.chat.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;
    final int CLIENT_AUTH_TIMEOUT = 120;

    private List<ClientHandler> clients;
    private List<ClientHandler> noAuthClients = new ArrayList<>();

    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {

        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                final ClientHandler clientHandler = new ClientHandler(this, socket);
                noAuthClients.add(clientHandler);

                new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            HashSet<ClientHandler> hss = new HashSet<>();
                            for (ClientHandler o : noAuthClients) {
                                if (o.getName().isEmpty()) {
                                    o.setAuthTimer(o.getAuthTimer() + 1);
                                    if (o.getAuthTimer() > CLIENT_AUTH_TIMEOUT) {
                                        hss.add(o);
                                    }
                                }
                            }
                            for (ClientHandler o : hss) {
                                System.out.println("TimeOut");
                                o.setAtomic(false);
                                o.sendMsg("/end");
                                noAuthClients.remove(o);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
    }

    public synchronized void sendMsgToClient(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nickTo)) {
                o.sendMsg("от " + from.getName() + ": " + msg);
                from.sendMsg("клиенту " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Участника с ником " + nickTo + " нет в чат-комнате");
    }
}