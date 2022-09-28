package Client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestClient {
    public static String ip = "127.0.0.1";

    public static int port = 8888;

    public static String filePath = "src/main/resources/TestHttp/httpResponse";

    public static void main(String[] args) throws IOException {
        Socket s = new Socket(ip,port);
        OutputStream out = s.getOutputStream();
        File f = new File(filePath + "0");
        System.out.println();
        FileInputStream in = new FileInputStream(f);
        BufferedReader bio = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bio.readLine())!= null) {
            sb.append(line).append("\n");
        }
        System.out.print(sb.toString());
        out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        out.flush();
    }
}
