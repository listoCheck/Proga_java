package lab5.Server;
import lab5.Server.commands.Console;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String message;
        int port = 6789;
        InetAddress host;
        SocketChannel sock;
        System.out.println("команда help - вывести справку по доступным командам");
        //;
        try {
            host = InetAddress.getLocalHost();
            InetSocketAddress addr = new InetSocketAddress(host, port);

            sock = SocketChannel.open();
            sock.connect(addr);
            System.out.println("Connected to server.");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            Scanner sc = new Scanner(System.in);
            while(true) {
                message = sc.nextLine();
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
                sock.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
                System.out.println("Message sent to server.");

                if (message.equals("exit")) {
                    objectOutputStream.close();
                    byteArrayOutputStream.close();
                    sock.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
