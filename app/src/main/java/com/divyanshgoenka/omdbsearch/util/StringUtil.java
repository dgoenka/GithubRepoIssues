package com.divyanshgoenka.omdbsearch.util;

/**
 * Created by divyanshgoenka on 17/05/17.
 */

public class StringUtil {
    public static String limit(String str, int i) {
        if (str != null && str.length() >= i)
            return str.substring(0, i);
        return str;
    }
}
