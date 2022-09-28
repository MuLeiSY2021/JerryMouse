package org.Server.Service.Connector.ProtoCol.HttpDeepConstruct;

import org.Server.Service.Connector.ProtoCol.SimpleHttpRequestConstrue;
import org.Server.Service.Util.Util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class ResuqsetSession extends BasicSession{
    private Methods method;

    private String askUrl;



    private int upgrade_insecure_requests;



    private String host;

    private String pragma;

    private String cache_control;

    private LinkedList<Cookie> cookie = new LinkedList<>();

    private LinkedList<String> user_agent = new LinkedList<>();

    private LinkedList<EncodingType> accept_encoding = new LinkedList<>();

    private HashMap<String,Double> accept = new HashMap<>();

    private HashMap<AcceptLanguageType,Double> accept_language = new HashMap<>();

    public ResuqsetSession(SimpleHttpRequestConstrue simpleHttpRequestConstrue) throws Exception {
        for (Field f:this.getClass().getDeclaredFields()) {
            this.getClass()
                    .getMethod("set" + Util.Capitalize(f.getName()))
                    .invoke(simpleHttpRequestConstrue.getClass()
                            .getDeclaredField(f.getName())
                            .get(simpleHttpRequestConstrue));
        }
    }

    public void setMethod(String method) {
        this.method = Methods.valueOf(method);
    }

    public void setAskUrl(String askUrl) {
        this.askUrl = askUrl;
    }



    public void setAccept(String accepts) {
        String[] lines = accepts.split(",");
        LinkedList<String> tmpAccepts = new LinkedList<>();
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(";q=");
            if(parts.length > 1 || i == lines.length - 1) {
                for (String accept:tmpAccepts) {
                    this.accept.put(accept,Double.parseDouble(parts[1]));
                }
            }else {
                tmpAccepts.add(lines[i]);
            }
        }
    }

    public void setAccept_encoding(String accept_encoding) {
        for (String acceptEnding: accept_encoding.split(" ,")) {
            this.accept_encoding.add(EncodingType.valueOf(acceptEnding));
        }
    }

    public void setAccept_language(String accept_language) {
        String[] lines = accept_language.split(",");
        LinkedList<String> tmpAccepts = new LinkedList<>();
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(";q=");
            if(parts.length > 1 || i == lines.length - 1) {
                for (String accept:tmpAccepts) {
                    this.accept_language.put(AcceptLanguageType.valueOf(accept),Double.parseDouble(parts[1]));
                }
            }else {
                tmpAccepts.add(lines[i]);
            }
        }
    }

    public void setCache_control(String cache_control) {
        this.cache_control = cache_control;
    }



    public void setCookie(String cookie) {
        String[] words = cookie.split("=");
        this.cookie.add(new Cookie(words[0],words[1]));
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPragma(String pragma) {
        this.pragma = pragma;
    }

    public void setUpgrade_insecure_requests(int upgrade_insecure_requests) {
        this.upgrade_insecure_requests = upgrade_insecure_requests;
    }

    public void setUser_agent(String user_agent) {
        String[] words = user_agent.split(" ");
        this.user_agent.addAll(Arrays.asList(words));

    }
}
