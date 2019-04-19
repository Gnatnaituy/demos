package network;

import java.io.*;
import java.net.Socket;

public class FileTransClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.2.73.93", 25535);
        File file = new File("C:\\Users\\Hasaker\\Documents\\Storage\\MateBook_X_Pro_Driver.zip");
        uploadFileUnderSocket(file, socket);
    }

    private static void uploadFileUnderSocket(File file, Socket socket) {
        try (InputStream in = new FileInputStream(file);
             OutputStream out = socket.getOutputStream()) {
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
