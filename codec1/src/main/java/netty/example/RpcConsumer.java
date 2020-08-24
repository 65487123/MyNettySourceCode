package com.utstar.netty.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author：luzeping
 * @Date: 2020/2/21 14:13
 */
public class RpcConsumer {
    public static void sendCall(String host){
        Socket socket = new Socket();
        SocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",8297);
        try {
            socket.connect(inetSocketAddress);
            OutputStream out=socket.getOutputStream();
            out.write(host.getBytes());
            out.flush();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
