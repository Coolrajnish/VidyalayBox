package com.ms.vidhyalebox.utility;

public class VidhyaleBoxUtil {
    public static final String DATE_FORMAT = "dd-MMM-yyyy";
    
    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().isEmpty();  
    }

}
