package com.doodzthreads.app.util;

public final class NameUtils {

    private NameUtils() {}

    public static String firstName(String fullName) {
        if (fullName == null) return "";
        String s = fullName.trim();
        if (s.isEmpty()) return "";
        int idx = s.indexOf(' ');
        return (idx < 0) ? s : s.substring(0, idx);
    }
}
