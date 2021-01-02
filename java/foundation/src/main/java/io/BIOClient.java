package io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class BIOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 3333);
                String message = "CLIENT-" + 1 + ": " + new Date();
                while (true) {
                    try {
                        socket.getOutputStream().write(message.getBytes());
                        System.out.println(message);
                        // 读取服务端回复信息
                        byte[] responseMessage = new byte[1024];
                        int responseMessageLength;
                        if ((responseMessageLength = socket.getInputStream().read(responseMessage)) != -1) {
                            System.out.println(new String(responseMessage, 0, responseMessageLength));
                        }
                        Thread.sleep(20000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
