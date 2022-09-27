package org.Server.Service.Connector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleConnector {
    private int port;

    private ServerSocket socket;

    public void startConnect() {
        try {
            this.socket = new ServerSocket(port);
            Socket s = socket.accept();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
