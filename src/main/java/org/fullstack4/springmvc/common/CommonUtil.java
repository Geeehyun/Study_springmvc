package org.fullstack4.springmvc.common;

public class CommonUtil {
    public static String parseString(String str) {
        return (str != null) ? str : "";
    }
    public static String parseString(Object obj) {
        return (obj != null) ? (String)obj : "";
    }

    public static boolean nullCheck(String str) {
        if (str.equals("")){
            return false;
        } else if (str.trim().equals("")) {
            return false;
        }
        return true;
    }
    public static boolean nullCheck(String[] strs) {
        if (strs != null) {
            for (String str : strs) {
                str = parseString(str);
                if (str.equals("")){
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static int parseInt(String str) {
        int result = 0;
        try {
            str = parseString(str);
            result = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 값 들어옴.");
        }
        return result;
    }

    public static boolean idCheck(String sessionId, String userID) {
        return sessionId.equals(userID);
    }
}

