package org.Server.Service.Connector.ProtoCol;

public class Cookie implements HttpAttrInnerList{
    private static final String className = "Content";
    private String name;

    private String value;

    public Cookie() {
    }

    @Override
    public void init(String text) {
        String[] values = text.split("=");
        this.name = values[0];
        this.value = values[1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
