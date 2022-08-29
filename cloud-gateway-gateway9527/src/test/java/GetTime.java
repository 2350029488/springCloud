import java.time.ZonedDateTime;

public class GetTime {
    public static void main(String[] args) {
        //如何获取配置所需要的时间
        ZonedDateTime now = ZonedDateTime.now();//默认时区
        System.out.println(now);
/* 2022-08-28T20:39:07.694+08:00[Asia/Shanghai]
 */
    }
}
