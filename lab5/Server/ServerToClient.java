package lab5.Server;

import lab5.commands.Console;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerToClient {
    public static void startServer() throws IOException, ClassNotFoundException {
        int port = 6789;
        Console c = new Console();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("Server started. Waiting for connection...");
        boolean flag = true;
        while (flag) { // Бесконечный цикл ожидания новых подключений
            SocketChannel clientSocketChannel = serverSocketChannel.accept();
            System.out.println("Connection established with client.");

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = clientSocketChannel.read(buffer)) > 0) {
                buffer.flip();
                byteArrayOutputStream.write(buffer.array(), 0, bytesRead);
                buffer.clear();

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                String message = (String) objectInputStream.readObject();
                System.out.println("Received message from client: " + message);

                if (message.equals("exit")) { // Проверяем, если клиент отправил команду "exit"
                    flag = false;
                    break; // Выходим из внутреннего цикла чтения команд
                } else {
                    c.start(message, false, "");
                }

                byteArrayOutputStream.reset();
                byteArrayInputStream.close();
                objectInputStream.close();
            }

            // Закрываем потоки для текущего клиента
            clientSocketChannel.close();
            System.out.println("Connection with client closed.");
        }

        // Закрываем серверный сокет (этот код никогда не выполнится)
        serverSocketChannel.close();
    }
}
