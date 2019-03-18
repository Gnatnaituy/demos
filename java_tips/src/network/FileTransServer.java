package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(25535);
        Socket socket = serverSocket.accept();
        String fileName = "C:\\Users\\Hasaker\\Documents\\Storage\\Uploaded\\aaa.zip";
        receiveFileUnderSocket(socket, fileName);
    }

    private static void receiveFileUnderSocket(Socket socket, String fileName) {
        try (InputStream in = socket.getInputStream();
             OutputStream out = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[1024 * 1024];
            int len;

            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
