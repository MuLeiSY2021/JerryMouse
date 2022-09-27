package org.Server.Service.Connector.ProtoCol;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class SimpleHttpRequestConstrue extends HttpConstrue{

    //----------------OtherAttr-----------------//

    private LinkedList<Cookie> cookies;

    private Accept acceptInfo;

    private String connection;

    private Content content;

    private String host;

    public String toResqusetPacketStr() throws IllegalAccessException {
        return super.toPacketStr(this.getClass());
    }

    public SimpleHttpRequestConstrue(byte[] bytes) {
        super.stuff(bytes,this.getClass());
    }


}
