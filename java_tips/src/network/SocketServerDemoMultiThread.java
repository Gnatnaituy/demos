package network;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerDemoMultiThread {
    private static final int SERVER_PORT = 998;

    public static void main(String[] args) throws IOException {
        int clientNo = 1;

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            ExecutorService exec = Executors.newCachedThreadPool();
            while (true) {
                Socket socket = serverSocket.accept();
                InetAddress clientIPAddress = socket.getInetAddress();
                exec.execute(new SingleServer(socket, clientNo, clientIPAddress));
                clientNo++;
            }
        }
    }
}


class SingleServer implements Runnable {
    private Socket socket;
    private int clientNo;
    private InetAddress clientIPAddress;

    SingleServer(Socket socket, int clientNo, InetAddress clientIPAddress) {
        this.socket = socket;
        this.clientNo = clientNo;
        this.clientIPAddress = clientIPAddress;
    }

    @Override
    public void run() {
        try (DataInputStream dis =
                     new DataInputStream(new BufferedInputStream(socket.getInputStream()));
             DataOutputStream dos =
                     new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {
            do {
                double length = dis.readDouble();
                System.out.println("从客户端-" + clientNo + ": "
                        + clientIPAddress + " 接收到的边长数据为: " + length);
                double result = length * length;
                dos.writeDouble(result);
                dos.flush();
            } while (dis.readInt() != 0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("与客户端-" + clientNo + ": " + clientIPAddress + " 通信结束");
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
