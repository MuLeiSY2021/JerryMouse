package org.Server.Service.Connector.ProtoCol.HttpDeepConstruct;

public abstract class BasicSession {
    private boolean connection;

    private String httpVersion;

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void setConnection(String connection) {
        this.connection = connection.equals("keep-alive");
    }
}
