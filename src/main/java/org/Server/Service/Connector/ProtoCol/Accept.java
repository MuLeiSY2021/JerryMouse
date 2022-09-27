package org.Server.Service.Connector.ProtoCol;

import java.lang.reflect.Field;

public class Accept extends SimpleHttpAttrSet{
    public static final String className = "Accept";

    private String encoding;

    private String language;

    private String ranges;

    public Accept(){
    }

    public String toHttp() throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        if(super.note != null) {
            sb.append(className).append(SimpleHttpRequestConstrue.spiltTag).append(super.note);
        }
        for (Field f:this.getClass().getDeclaredFields()) {
            if(f.get(this) != null) {
                char[] name = f.getName().toCharArray();
                name[0] = f.getName().substring(0,1).toUpperCase().toCharArray()[0];
                sb.append(className).append(SimpleHttpRequestConstrue.subAttrSpiltTag).append(name)
                        .append((String)(f.get(this)));
            }
        }
        return sb.toString();
    }
}
