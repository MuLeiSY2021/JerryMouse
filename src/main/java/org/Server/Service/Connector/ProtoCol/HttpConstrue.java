package org.Server.Service.Connector.ProtoCol;

import org.Server.Service.Util.Util;

import java.lang.reflect.Field;

public abstract class HttpConstrue {
    private static final String spiltTag = ": ";

    public static String getSpiltTag() {
        return spiltTag;
    }

    //Common Report
    private String method;
    private String askUrl;
    private String httpVersion;

    public String toPacketStr(Object child) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(method).append(' ')
                .append(askUrl).append(' ')
                .append(httpVersion).append('\n');
        for (Field f:child.getClass().getDeclaredFields()) {
            if(f.getName().equals("innerContent") ||
                    f.getName().equals("method") ||
                    f.getName().equals("askUrl") ||
                    f.getName().equals("httpVersion") ||
                    f.getName().equals("spiltTag") ||
                    f.getName().equals("subAttrSpiltTag")) {
                continue;
            }
            try {
                Object text = f.get(child);
                if(text != null) {

                    sb.append(Util.toHttpType(f.getName())).append(spiltTag).append((f.get(child)));
                    sb.append("\n");
                }
            } catch (IllegalAccessException e) {
                System.err.println(f.getType() + " name:" + f.getName());
                throw e;
            }

        }
        return sb.toString();
    }

    public void stuff(String text,Object child) throws Exception {
        String[] lines = text.split("\n");
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
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            if(line.isEmpty() || line.indexOf(0) == '\n') {
                return ;
            }
            content = line.split(spiltTag);
            if (content.length < 2) {
                continue;
            }
            String[] attr;
            content[0] = content[0].toLowerCase().replace("-","_");
//            if (((attr = content[0].split(subAttrSpiltTag)).length) > 1) {
//                for (Field attrF : this.getClass().getDeclaredFields()) {
//                    if (attrF.getDeclaringClass().getName().equals(attr[0])) {
//                        if (attrF.get(child) == null) {
//                            attrF.set(child, attrF.getDeclaringClass().newInstance());
//                        }
//                        attrF.getClass().getDeclaredMethod("attrReflect", String.class, String.class).invoke(attrF.get(child), attr[1], attr[2]);
//                    }
//                }
//            } else {
            Field contentF = child.getClass().getDeclaredField(content[0]);
            if (!contentF.getType().equals(String.class)) {
                if (contentF.get(child) == null) {
                    contentF.set(child, contentF.getDeclaringClass().getConstructor().newInstance(content[1]));
                }
//                    if (contentF.get(child) instanceof LinkedList) {
//                        Class attrC = Class.forName(content[0]);
//                        Object obj = attrC.newInstance();
//                        attrC.getDeclaredMethod("attrReflect", String.class, String.class).invoke(attrC, attr[1], attr[2]);
//                        ((LinkedList) (contentF.get(child))).add(obj);
//                    }
            } else {
                contentF.set(this,content[1]);
            }
//            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getAskUrl() {
        return askUrl;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}
