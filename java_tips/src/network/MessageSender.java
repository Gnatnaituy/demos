package network;

import java.io.*;
import java.net.Socket;

public class MessageSender {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 998);

        //获取键盘输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //获取Socket的输出流，用来发送消息到服务端
        PrintStream out = new PrintStream(socket.getOutputStream());
        //获取Socket的输入流，用来接收从服务端发送过来的消息
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String message;
        while (!(message = input.readLine()).equals("exit")) {
            //发送消息到服务端
            out.println(message);
            // 读取服务端的回复
            String reply = in.readLine();
            System.out.println("Server: " + reply);
        }
        // 把 exit 发送到服务端
        out.println(message);

        input.close();
        socket.close();
    }
}