package org.Server.Service.Connector.ProtoCol.HttpDeepConstruct;

import org.Server.Service.Connector.ProtoCol.SimpleHttpRequestConstrue;
import org.Server.Service.Connector.ProtoCol.SimpleHttpResponseConstrue;
import org.Server.Service.Util.Util;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

public class ResponseSession extends BasicSession{
    protected String statusCode;

    protected String server;

    protected AcceptRangesTypes accept_ranges;

    protected Content_Type content_type;

    protected String cache_control;

    protected int content_length;

    private byte[] innerContent;

    public ResponseSession(SimpleHttpResponseConstrue simpleHttpResponseConstrue) throws Exception {
        for (Field f:this.getClass().getDeclaredFields()) {
            this.getClass()
                    .getMethod("set" + Util.Capitalize(f.getName()))
                    .invoke(simpleHttpResponseConstrue.getClass()
                            .getDeclaredField(f.getName())
                            .get(simpleHttpResponseConstrue));
        }
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setAccept_ranges(String accept_ranges) {
        this.accept_ranges = AcceptRangesTypes.valueOf(accept_ranges);
    }

    public void setContent_type(String content_type) throws Exception {
        this.content_type = new Content_Type(content_type);
    }

    public void setCache_control(String cache_control) {
        this.cache_control = cache_control;
    }

    public void setContent_length(String content_length) {
        this.content_length =Integer.parseInt(content_length);
    }

    public void setInnerContent(String innerContent) {
        this.innerContent = innerContent.getBytes(StandardCharsets.UTF_8);
    }
}
