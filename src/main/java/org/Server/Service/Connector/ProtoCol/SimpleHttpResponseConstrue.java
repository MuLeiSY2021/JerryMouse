package org.Server.Service.Connector.ProtoCol;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SimpleHttpResponseConstrue extends HttpConstrue{
    protected String httpVersion;

    protected String statusCode;

    protected String server;

    protected String accept_ranges;

    protected String content_type;

    protected String cache_control;

    protected String connection;

    protected String content_length;

    private String innerContent;

    public void departContent(byte[] bytes) {
        char[] c = new String(bytes,StandardCharsets.UTF_8).toCharArray();
        if(c.length < 2) {
            return;
        }
        char tmp = c[0],next= c[1];
        for (int i = 1; i < c.length; i++) {
            if(tmp == '\n' && next == '\n') {
                this.innerContent =new String(Arrays.copyOfRange(c,i + 2,c.length));
                return;
            }else {
                tmp = next;
                next = c[i + 1];
            }
        }
    }

    public SimpleHttpResponseConstrue(byte[] bytes) throws Exception{
        departContent(bytes);
        super.stuff(new String(bytes,StandardCharsets.UTF_8),this);
    }

    public String toResponsePacketStr() throws IllegalAccessException {
        return super.toPacketStr(this) + '\n' + innerContent;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getServer() {
        return server;
    }

    public String getAccept_ranges() {
        return accept_ranges;
    }

    public String getContent_type() {
        return content_type;
    }

    public String getCache_control() {
        return cache_control;
    }

    public String getConnection() {
        return connection;
    }

    public String getContent_length() {
        return content_length;
    }

    public String getInnerContent() {
        return innerContent;
    }
}
