package org.Server.Service.Connector.ProtoCol.HttpDeepConstruct;

public class Content_Type {
    String type;

    String charset;

    public Content_Type(String text) throws Exception {
        String[] str = text.split("; ");
        for (String part : str) {
            String[] deeper = part.split("=");
            if(deeper.length > 1) {
                this.getClass().getDeclaredField(deeper[0]).set(this,deeper[1]);
            }
            else {
                this.type = deeper[0];
            }
        }
    }
}
