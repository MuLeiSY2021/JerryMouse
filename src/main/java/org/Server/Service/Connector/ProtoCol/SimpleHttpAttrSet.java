package org.Server.Service.Connector.ProtoCol;

import java.lang.reflect.Field;

public abstract class SimpleHttpAttrSet implements HttpAttrSet{
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

}
