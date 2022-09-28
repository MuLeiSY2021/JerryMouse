package org.Server.Service.Connector;

import org.Server.Service.Connector.ProtoCol.SimpleHttpResponseConstrue;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SampleConnector {
    private static final int port = 8888;

    private static ServerSocket socket;

    public static void startConnect() throws Exception{
            socket = new ServerSocket(port);
            while (true){
                Socket s = socket.accept();
                InputStream in = s.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                StringBuilder sb = new StringBuilder();
                while ((len = in.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
                }
                //System.out.println(sb.toString());
                SimpleHttpResponseConstrue request = new SimpleHttpResponseConstrue(sb.toString().getBytes(StandardCharsets.UTF_8));
                System.out.println(request.toResponsePacketStr());
            }
    }

    public static void main(String[] args) throws Exception {
        startConnect();
    }
}
