// Server.java
package lab5.Server;

import lab5.Server.file.WriteFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    private static String out_to_client = "";
    public static void main(String[] args) {
        WriteFile.WRITE_FILE.WriteFileInMain();
        try (ServerSocket serverSocket = new ServerSocket(6789)) {
            System.out.println("Сервер запущен!");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            System.out.println("Клиент подключился");
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                String word;
                while ((word = in.readLine()) != null) {
                    System.out.println("Получено сообщение от клиента: " + word);
                    lab5.Server.commands.Console console = new lab5.Server.commands.Console();
                    console.start(word, false, "");
                    String response = "Привет, это Сервер! Подтверждаю, вы написали: " + word;
                    out.write(response);
                    //out.write(out_to_client);
                    //out.flush();
                    if (word.equals("exit")) {
                        System.out.println("Клиент отключился");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении сообщения от клиента: " + e.getMessage());
            } finally {
                try {
                    if (clientSocket != null) {
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
