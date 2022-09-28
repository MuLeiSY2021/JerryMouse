package org.Server.Service.Connector.ProtoCol.HttpDeepConstruct;

public class Cookie extends SimpleHttpAttrSet {
    public static final String className = "Content";

    protected String name;

    protected String value;

    public Cookie(String name,String value) {
        this.name = name;
        this.value = value;
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

    @Override
    public String toHttp() {
        return super.toHttp(className,this.getClass());
    }
}
