package compar.utils;

import java.io.UnsupportedEncodingException;

public class Charset{
    public static void recover(String str)
    throws UnsupportedEncodingException{
    String[] charsets = new String[]{
    "windows-1252","GB18030","Big5","UTF-8"};
    for(int i=0;i<charsets.length;i++){
    for(int j=0;j<charsets.length;j++){
    if(i!=j){
    String s = new String(str.getBytes(charsets[i]),charsets[j]);
    System.out.println("---- 原来编码(A)假设是: "
    +charsets[j]+", 被错误解读为(B): "+charsets[i]);
    System.out.println(s);
    System.out.println();
    }
    }
    }
    }
    public static void main(String[] args) {
        try {
            Charset.recover("ÀÏÂí");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

