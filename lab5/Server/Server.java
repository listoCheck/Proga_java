package lab5.Server;

import lab5.Server.file.WriteFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Server {

    private static ServerSocket server; // серверсокет
    public final static Server SERVER = new Server();
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    public String out_to_client = "";

    public static void main(String[] args) {
        //  Scanner sc = new Scanner(System.in);
        WriteFile.WRITE_FILE.WriteFileInMain();
        try {
            server = new ServerSocket(6789); // серверсокет прослушивает порт 4004
            System.out.println("Сервер запущен!");

            while (true) {
                Socket clientSocket = server.accept(); // accept() будет ждать пока
                //кто-нибудь не захочет подключиться
                new Thread(new ClientHandler(clientSocket)).start(); // создаем и запускаем новый поток для обработки клиента
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (server != null) {
                    server.close(); // закрываем серверный сокет при завершении работы
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void out(String str) throws IOException {
        //System.out.println("***" + str);
        out.write(str);
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket; //сокет для общения


        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            System.out.println("Клиент подключился");
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String word;
                while ((word = in.readLine()) != null) { // Ждем сообщений от клиента
                    lab5.Server.commands.Console console = new lab5.Server.commands.Console();
                    System.out.println("Получено сообщение от клиента: " + word);
                    console.start(word, false, "");// Обработка полученного сообщения
                    String response = "Привет, это Сервер! Подтверждаю, вы написали: " + word + "\n";
                    //out.write(response); // отправляем ответ клиенту
                    //System.out.println(WriteFile.WRITE_FILE.out_to_client);
                    //Thread.sleep(2000);
                    String answer = SERVER.out_to_client + "\n";
                    //out.write(Arrays.toString(answer.getBytes())); // отправляем ответ клиенту
                    //System.out.println(answer);
                    out(answer);
                    //out.write("end");
                    SERVER.out_to_client = "";
                    out.flush(); // выталкиваем все из буфера
                    if (word.equals("exit")) {
                        System.out.println("Клиент отключился");
                        break;
                    }
                }
            } catch (IOException e) {
                // При разрыве соединения клиента будет выброшено исключение IOException.
                // Мы можем просто вывести сообщение о разрыве и продолжить работу сервера.
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