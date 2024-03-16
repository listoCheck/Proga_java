package lab5.Client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client implements Serializable{
    private static Socket clientSocket; //сокет для общения
    private static BufferedReader in; // поток чтения из сокета
    public static boolean flag = true;
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        final int MAX_RETRIES = 3;
        int retries = 0;
        NewDragon newDragon = new NewDragon();
        while (retries < MAX_RETRIES && flag) {
            try {
                clientSocket = new Socket("localhost", 6789); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                Scanner scanner = new Scanner(System.in);
                // читать сообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Введите 'help', чтобы узнать возможные команды:");

                while (true) {
                    // Ждем пока клиент что-нибудь напишет в консоль
                    String word = scanner.nextLine();
                    MyObject obj = new MyObject(word);
                    if (word.contains("insert") && (word.split(" ").length > 1 && !word.split(" ")[1].isEmpty())){
                        //out.write(word);
                        //MyObject obj = new MyObject(word);
                        out.write(obj.getName() + " :::" + newDragon.addNew());
                        out.flush();
                    }else if (word.contains("update") && (word.split(" ").length > 1 && !word.split(" ")[1].isEmpty())){
                        //MyObject obj = new MyObject(word);
                        out.write(obj.getName() + " :::" + newDragon.addNew());
                        out.flush();
                    }else if (word.contains("exit_server") || word.contains("save")){
                        System.out.println("У вас нет прав администратора, чтобы управлять сервером");
                        //continue;
                    }else{
                        out.write(obj.getName() + "\n");
                        out.flush();
                    }
                    if (word.equals("exit_server")) {
                        System.out.println("Клиент был закрыт...");
                        flag = false;
                        break;
                    }
                    String serverWord = new lab5.Client.MyObject(in.readLine()).getName(); // ждем, что скажет сервер
                    System.out.println("Ответ сервера: "); // получив - выводим на экран
                    if (serverWord == null){
                        System.out.println("Просим прощения, непредвиденная ошибка");
                    }else {
                        for (String i : serverWord.split("::")) {
                            System.out.println(i);
                        }
                    }
                }
            } catch (SocketException e) {
                System.err.println("Ошибка соединения: " + e.getMessage());
                retries++;
                if (retries < MAX_RETRIES) {
                    System.out.println("Попытка переподключения через 5 секунд...");
                    try {
                        Thread.sleep(5000); // ждем 5 секунд перед следующей попыткой
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.err.println("Превышено количество попыток подключения. Завершение программы.");
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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
