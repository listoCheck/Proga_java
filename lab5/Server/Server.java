package lab5.Server;

import lab5.Server.file.WriteFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Server {

    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    static ArrayList<Socket> cookies = new ArrayList<>();
    public static String out_to_client = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WriteFile.WRITE_FILE.WriteFileInMain();
        try {
            server = new ServerSocket(6789); // серверсокет прослушивает порт 4004
            System.out.println("Сервер запущен!");

            while (true) {
                Socket clientSocket = server.accept(); // accept() будет ждать пока
                cookies.add(clientSocket);
                for (Socket client : cookies) {
                    new Thread(new ClientHandler(client)).start(); // создаем и запускаем новый поток для обработки клиента
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (server != null || sc.nextLine().equals("exit")) {
                    server.close(); // закрываем серверный сокет при завершении работы
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ClientHandler implements Runnable {
        private Socket clientSocket; //сокет для общения

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            System.out.println("Клиент подключился по сокету: " + socket.getPort());
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String word;
                while ((word = in.readLine()) != null) {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    lab5.Server.commands.Console console = new lab5.Server.commands.Console();
                    System.out.println("Получено сообщение от клиента: " + clientSocket.getPort() + " " + word);
                    console.start(word, false, "");
                    out.write(out_to_client + "\n");
                    out_to_client = "";
                    out.flush();
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
                        clientSocket.close(); // закрываем сокет клиента при завершении работы
                    }
                    if (in != null) {
                        in.close(); // закрываем поток чтения
                    }
                    if (out != null) {
                        out.close(); // закрываем поток записи
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
