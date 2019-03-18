package network;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClientDemoMultiThread {

    public static void main(String[] args) throws IOException {
        int port = 998;
        String host = "localhost";

        // 创建一个套接字并将其连向指定端口号
        Socket socket = new Socket(host, port);

        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("请输入正方形的边长: ");
            double length = sc.nextDouble();
            dos.writeDouble(length);
            dos.flush();

            double area = dis.readDouble();
            System.out.println("从服务器返回的计算面积为: " + area);

            while (true) {
                System.out.println("继续计算? y/n");
                String str = sc.next();

                if (str.equalsIgnoreCase("n")) {
                    dos.writeInt(0);
                    dos.flush();
                    flag = false;
                    break;
                } else if (str.equalsIgnoreCase("y")) {
                    dos.writeInt(1);
                    dos.flush();
                    break;
                } else {
                    System.out.println("请输入'y'或'n'");
                }
            }
        }

        socket.close();
    }
}