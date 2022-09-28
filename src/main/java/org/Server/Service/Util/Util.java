package org.Server.Service.Util;

public class Util {
    public static String toHttpType(String str) {
        StringBuilder sb = new StringBuilder();
        for (String subStr:str.split("_")) {
            String firstC = subStr.substring(0,1);
            String next = subStr.substring(1);
            sb.append(firstC.toUpperCase()).append(next).append("-");
        }
        sb.delete(sb.length() - 1,sb.length());
        return sb.toString();
    }

    public static String Capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
