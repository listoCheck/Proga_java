package lab5.Server;

import lab5.Server.file.MyObject;
import lab5.Server.file.WriteFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Serializable{

    private static ServerSocket server; // серверсокет
    public static FileWriter log;
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    static ArrayList<Socket> cookies = new ArrayList<>();
    public static String out_to_client = "";

    public Server() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        WriteFile.WRITE_FILE.writeFileInMain();
        //Scanner sc = new Scanner(System.in);
        log = new FileWriter("C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\Server\\log.txt", true);
        //try {
        //FileWriter writer = new FileWriter("C:\\Users\\admin\\IdeaProjects\\lab2sem\\src\\lab5\\Server\\log.txt")
        //writer.write("Начата запись в файл:\n");
        //} catch (FileNotFoundException e) {
        //    Server.out_to_client += ("Файл не найден: " + e.getMessage());
        //} catch (IOException e) {
        //    Server.out_to_client += ("Ошибка при записи в файл: " + e.getMessage());
        //}
        //WriteFile.WRITE_FILE.WriteFileInMain();
        try {
            server = new ServerSocket(6789); // серверсокет прослушивает порт 4004
            System.out.println("Сервер запущен!");
            log.write("Сервер запущен!" + "\n");
            //writer.write("Сервер запущен!");
            boolean flag = true;
            while (flag) {
                Socket clientSocket = server.accept(); // accept() будет ждать пока
                cookies.add(clientSocket);
                for (Socket client : cookies) {
                    new Thread(new ClientHandler(client)).start();
                }
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

    public static class ClientHandler implements Runnable {
        private Socket clientSocket; //сокет для общения

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            System.out.println("Клиент подключился по сокету: " + socket.getPort());
            log.write("Клиент подключился по сокету: " + socket.getPort() + "\n");
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String word;
                while ((word = new MyObject(in.readLine()).getName()) != null) {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    lab5.Server.commands.Console console = new lab5.Server.commands.Console();
                    System.out.println("Получено сообщение от клиента: " + clientSocket.getPort() + " " + word);
                    log.write("Получено сообщение от клиента: " + clientSocket.getPort() + " " + word + "\n");
                    log.flush();
                    console.start(word, false, "");
                    out.write(new MyObject(out_to_client).getName() + "\n");
                    out_to_client = "";
                    out.flush();
                    if (word.equals("exit_server")) {
                        System.out.println("Клиент отключился");
                        log.write("Клиент отключился" + "\n");
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