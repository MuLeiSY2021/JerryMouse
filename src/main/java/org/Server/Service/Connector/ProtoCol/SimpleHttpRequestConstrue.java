package org.Server.Service.Connector.ProtoCol;

public class SimpleHttpRequestConstrue extends HttpConstrue{

    //----------------OtherAttr-----------------//

    protected String cookie;

    protected String accept;

    protected String accept_encoding;

    protected String accept_language;

    protected String last_modified;

    protected String referer;

    protected String upgrade_insecure_requests;

    protected String connection;

    protected String host;

    protected String pragma;

    protected String user_agent;

    protected String cache_control;

    public String toRequestPacketStr() throws IllegalAccessException {
        return super.toPacketStr(this);
    }

    public SimpleHttpRequestConstrue(String text) throws Exception {
        super.stuff(text,this);
    }



    public String getCookie() {
        return cookie;
    }

    public String getAccept() {
        return accept;
    }

    public String getAccept_encoding() {
        return accept_encoding;
    }

    public String getAccept_language() {
        return accept_language;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public String getReferer() {
        return referer;
    }

    public String getUpgrade_insecure_requests() {
        return upgrade_insecure_requests;
    }

    public String getConnection() {
        return connection;
    }

    public String getHost() {
        return host;
    }

    public String getPragma() {
        return pragma;
    }

    public String getUser_agent() {
        return user_agent;
    }
}
