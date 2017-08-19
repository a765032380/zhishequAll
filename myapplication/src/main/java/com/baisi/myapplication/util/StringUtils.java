package com.baisi.myapplication.util;

import android.widget.TextView;

/**
 * Created by fengyongge on 2016/5/20 0020.
 */
public class StringUtils {

    public static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static void filtNull(TextView textView, String s) {
        if (s != null) {
            textView.setText(s);
        } else {
            textView.setText(filtNull(s));
        }
    }


    public static void filtNull(TextView textView, String s1, String s2) {
        if (s1 != null&&s2!=null) {
            textView.setText(s1+" "+s2);
        } else {
            textView.setText(filtNull(s1)+filtNull(s2));
        }

    }

    //判断过滤单个string为null
    public static String filtNull(String s) {
        if (s!=null) {
           return  s;
        } else {
            s="null";
        }
        return  s;
    }


    //判断单个对象不为null
    public static boolean filtObjectNull(Object object) {
        if (object != null) {
           return false;
        } else {
           return true;
        }
    }
    public static String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }



}
