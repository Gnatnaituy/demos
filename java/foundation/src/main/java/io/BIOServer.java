package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BIOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(3333);

        // 接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理
        new Thread(() -> {
            while (true) {
                try {
                    // 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    // 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            int receivedLen;
                            int messageLen = 0;
                            StringBuilder messageBuilder = new StringBuilder();
                            String message;

                            // 按字节流方式读取数据
                            byte[] data = new byte[1024];
                            while ((receivedLen = socket.getInputStream().read(data)) != -1) {
                                messageBuilder.append(new String(data, 0, receivedLen));
                                messageLen += receivedLen;
                                // 打印收到的字符串
                                message = messageBuilder.toString();
                                System.out.println(message);
                                // 回复客户端
                                socket.getOutputStream().write(("Received[message length:" + messageLen + "]:" + message).getBytes(StandardCharsets.UTF_8));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
