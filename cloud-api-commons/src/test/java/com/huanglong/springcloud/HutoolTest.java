package com.huanglong.springcloud;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class HutoolTest {
    public static void main(String[] args) {
System.out.println(DateUtil.now());//2022-08-28 10:20:55
System.out.println(DateUtil.date());//2022-08-28 10:20:55
System.out.println(DateUtil.today());//2022-08-28
System.out.println(DateUtil.parse("2017-03-01"));//2017-03-01 00:00:00
System.out.println(DateUtil.parseDate("2022-08-28 10:20:55"));
System.out.println(DateUtil.parse("2022年8月28日 5时55分47秒"));//2022-08-28 05:55:47

//        DateUtil.parse 字符串转为日期       DateUtil.format日期转为字符串
String dateStr = "2017-03-01";
Date date = DateUtil.parse(dateStr);
System.out.println(date);
String format = DateUtil.format(date, "yyyy年MM月dd日");
System.out.println(format);

System.out.println(DateUtil.format(DateUtil.parse("2022-08-28 05:55:47"),"yyyy年MM月dd日 HH时mm分ss秒"));//2022年08月28日 05时55分47秒
        System.out.println(DateUtil.format(new Date(),"yyyy年MM月dd日 HH时mm分ss秒"));//2022年08月28日 10时39分44秒

    }
}
