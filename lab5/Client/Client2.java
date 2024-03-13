// Client.java
package lab5.Client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client2 {

    public static void main(String[] args) {
        final int MAX_RETRIES = 3;
        int retries = 0;

        while (retries < MAX_RETRIES) {
            try (Socket clientSocket = new Socket("localhost", 6789)) {
                System.out.println("Соединение установлено!");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("Введите сообщение:");
                    String word = scanner.nextLine();
                    out.write(word + "\n");
                    out.flush();

                    if (word.equals("exit")) {
                        System.out.println("Клиент был закрыт...");
                        break;
                    }

                    String serverWord = in.readLine();
                    System.out.println("Ответ сервера: " + serverWord);

                    if (serverWord == null) {
                        System.out.println("Просим прощения, непредвиденная ошибка");
                    }
                }
            } catch (SocketException e) {
                System.err.println("Ошибка соединения: " + e.getMessage());
                retries++;
                if (retries < MAX_RETRIES) {
                    System.out.println("Попытка переподключения через 5 секунд...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.err.println("Превышено количество попыток подключения. Завершение программы.");
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
