package org.Server.Service.Connector.ProtoCol;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

public class SimpleHttpResponseConstrue extends HttpConstrue{
    private String httpVersion;

    private String statusCode;

    private String server;

    private Accept acceptInfo;

    private String connection;

    private Content content;

    private byte[] innerContent;

    public SimpleHttpResponseConstrue(byte[] bytes) {
        super.stuff(bytes,this.getClass());
    }

    public String toResposePacketStr() throws IllegalAccessException {
        StringBuilder sb = new StringBuilder(super.toPacketStr(this.getClass()));
        return sb.append(innerContent).toString();
    }
}
