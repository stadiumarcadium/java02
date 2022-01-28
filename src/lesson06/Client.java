import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
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
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        final String message = in.readUTF();
                        if ("/end".equalsIgnoreCase(message)) {
                            out.writeUTF("/end");
                            System.err.println("Соединение закрыто");
                            closeConnections();
                            System.exit(0);
                        }
                        System.out.println("Получено сообщение: "+message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

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
}