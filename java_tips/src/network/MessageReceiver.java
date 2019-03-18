package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageReceiver {
    private static final int SERVER_PORT = 998;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(SERVER_PORT);
        Socket socket;
        boolean f = true;

        while(f){
            //等待客户端的连接，如果没有获取连接
            socket = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(socket)).start();
        }
        server.close();
    }
}

class ServerThread implements Runnable {
    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 获取键盘输入
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            // 获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(socket.getOutputStream());
            // 获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while (!"exit".equals(message = in.readLine())) {
                System.out.println("Client: " + message);
                message = input.readLine();
                out.println(message);
            }
            // 收到客户端发过来的 exit, 关闭连接
            System.out.println("Client exit!");

            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
