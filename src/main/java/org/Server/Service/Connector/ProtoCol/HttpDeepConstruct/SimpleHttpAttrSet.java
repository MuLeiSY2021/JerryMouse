package org.Server.Service.Connector.ProtoCol.HttpDeepConstruct;

import org.Server.Service.Connector.ProtoCol.SimpleHttpRequestConstrue;

import java.lang.reflect.Field;

public abstract class SimpleHttpAttrSet implements HttpAttrSet {
    protected String note;

    @Override
    public void attrReflect(String attr, String content) {
        if(attr.equals(this.getClass().getName())) {
            this.note = content;
        } else {
            attr = attr.toLowerCase();
            try {
                Field f = this.getClass().getDeclaredField(attr);
                f.set(this, content);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.getStackTrace();
            }
        }
    }

    public abstract String toHttp();

    public String toHttp(String className,Class clazz){
        StringBuilder sb = new StringBuilder();
        if(note != null) {
            sb.append(className).append(SimpleHttpRequestConstrue.getSpiltTag()).append(note);
        }
        for (Field f:clazz.getClass().getFields()) {
            try {
                if(f.get(clazz) != null) {
                    char[] name = f.getName().toCharArray();
                    name[0] = f.getName().substring(0,1).toUpperCase().toCharArray()[0];
                    sb.append(className).append("-").append(name)
                            .append((String)(f.get(clazz)));
                }
            } catch (IllegalAccessException e) {
                e.getStackTrace();
            }
        }
        return sb.toString();
    }

}
