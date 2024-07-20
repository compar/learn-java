package cn.compar.demo.datetime;

import java.time.Instant;

import java.util.Date;


/**新老时间转换 */
public class TranDateInstant {

    public static void main(String[] args) {
        Instant now = Instant.now();
        Date date = Date.from(now);
        Instant now2 = date.toInstant();

        System.out.println(now);
        System.out.println(now2);
        assert now.equals(now2);

        
    }
}