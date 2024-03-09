package lab5;
import lab5.Server.Client;
import lab5.Server.ServerToClient;
import lab5.commands.Console;
import lab5.file.WriteFile;

import java.io.IOException;


/**
 * Класс мейн)
 */
public class Main {
    /**
     * Метод, который запускает код
     * @param args
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        WriteFile.WRITE_FILE.WriteFileInMain();
        ServerToClient.startServer();
    }
}