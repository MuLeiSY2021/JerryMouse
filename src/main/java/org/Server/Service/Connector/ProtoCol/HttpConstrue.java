package org.Server.Service.Connector.ProtoCol;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public abstract class HttpConstrue {
    public static final String spiltTag = ": ";

    public static final String subAttrSpiltTag = "-";

    //Common Report
    protected String method;
    protected String askUrl;
    protected String httpVersion;

    public String toPacketStr(Class clazz) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(method).append(' ')
                .append(askUrl).append(' ')
                .append(httpVersion).append('\n');
        for (Field f:this.getClass().getDeclaredFields()) {
            if(f.getName().equals("method") ||
                    f.getName().equals("askUrl") ||
                    f.getName().equals("httpVersion") ||
                    f.getName().equals("spiltTag") ||
                    f.getName().equals("subAttrSpiltTag")) {
                continue;
            }
            if(f.getType().equals(String.class)) {
                sb.append(f.getName()).append(spiltTag).append((String) (f.get(clazz)));
            } else {
                sb.append(f.get(this).toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void stuff(byte[] bytes,Class clazz) {
        String completeText = new String(bytes, StandardCharsets.UTF_8);
        String[] lines = completeText.split("\n");
        String commonHead = lines[0];
        String[] content = commonHead.split(" ");
        if(content.length > 0) {
            this.method = content[0];
        }
        if(content.length > 1) {
            this.askUrl = content[1];
        }
        if(content.length > 2) {
            this.httpVersion = content[2];
        }
        try {
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                content = line.split(spiltTag);
                if (content.length < 2) {
                    continue;
                }
                String[] attr;
                if (((attr = content[0].split(subAttrSpiltTag)).length) != 0) {
                    for (Field attrF : this.getClass().getDeclaredFields()) {
                        if (attrF.getDeclaringClass().getName().equals(attr[0])) {
                            if (attrF.get(this) == null) {
                                attrF.set(this, attrF.getDeclaringClass().newInstance());
                            }
                            attrF.getClass().getDeclaredMethod("attrReflect", String.class, String.class).invoke(attrF.get(clazz), attr[1], attr[2]);
                        }
                    }
                } else {
                    Field contentF = this.getClass().getDeclaredField(content[0].toLowerCase());
                    if (!contentF.getDeclaringClass().equals(String.class)) {
                        if (contentF.get(clazz) == null) {
                            contentF.set(clazz, contentF.getDeclaringClass().newInstance());
                        }
                        if (contentF.get(clazz) instanceof LinkedList) {
                            Class attrC = Class.forName(content[0]);
                            Object obj = attrC.newInstance();
                            attrC.getDeclaredMethod("init").invoke(obj,content[1]);
                            ((LinkedList) (contentF.get(clazz))).add(obj);
                        }
                    } else {
                        contentF.set(this,content[1]);
                    }
                }
            }
        }catch (Exception e) {
            e.getStackTrace();
        }
    }
}
