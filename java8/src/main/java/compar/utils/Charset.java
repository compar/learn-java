package compar.utils;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class Charset{
    public static void recover(String str){
    SortedMap<String,java.nio.charset.Charset> availableCharsets = java.nio.charset.Charset.availableCharsets();

     List<String> charlist = availableCharsets.values().stream().map(v->v.name()).collect(Collectors.toList());
     String[] charsets = charlist.toArray(new String[charlist.size()]);  //太多了，有172个

    //String[] charsets =new String[]{"windows-1252","GB18030","Big5","UTF-8"};
    for(int i=0;i<charsets.length;i++){
    for(int j=0;j<charsets.length;j++){
    if(i!=j){
        try{
            String s = new String(str.getBytes(charsets[i]),charsets[j]);
            System.out.println("---- 原来编码(A)假设是: "
            +charsets[j]+", 被错误解读为(B): "+charsets[i]);
            System.out.println(s);
            System.out.println();
        }catch(Exception e){
            System.out.printf("异常Exception  A: %s, B: %s\n",charsets[i],charsets[j]);
        }
    }
    }
    try {
        System.in.read();
    } catch (IOException e) {

        e.printStackTrace();
    }
    }
    }
    public static void main(String[] args) {
            Charset.recover("ÀÏÂí");
    }
}

