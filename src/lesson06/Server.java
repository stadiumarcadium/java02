import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                final String message = scanner.nextLine();
                out.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void start() {
        Socket socket = null;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("\u001B[32mСервер запущен и ожидает подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился...\u001B[0m");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        final String message = in.readUTF();
                        if ("/end".equalsIgnoreCase(message)) {
                            out.writeUTF("/end");
                            System.err.println("Соединение закрыто");
                            System.exit(0);
                            break;
                        }
                        System.out.println("Получено сообщение:" + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        if (socket != null) {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
