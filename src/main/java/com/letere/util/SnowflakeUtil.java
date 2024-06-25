package com.letere.util;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * 雪花算法工具类
 * @author gaozijie
 * @since 2024-06-24
 */
public class SnowflakeUtil {

    /**
     * 起始时间戳（暂定：2024-01-01 00:00:00，按实际情况修改，最大使用年长69年）
     */
    private final static long START_TIMESTAMP = LocalDateTime
            .of(2024, 1, 1, 0, 0, 0)
            .toInstant(ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getRawOffset() / 1000))
            .toEpochMilli();

    /**
     * 序列号掩码
     */
    private final static long SEQ_MASK = ~(-1L << 12);

    /**
     * 机器码（10位，按实际情况修改，不同服务要用不同的机器码区分）
     */
    private final static long WORK_ID = 1L;

    /**
     * 时间戳（41位）
     */
    private static long timestamp = -1L;

    /**
     * 序列号（12位），并发生成用
     */
    private static long seq = 0L;

    /**
     * 生成id
     * @return 雪花算法Id
     */
    public synchronized static long getId(){
        long now = System.currentTimeMillis();
        // 当前时间<记录时间（时钟回拨）
        if (now < timestamp){
            throw new RuntimeException("时钟回拨异常，请将时间调整正常后再进行获取");
        }
        // 当前时间=记录时间，序列号+1，并做溢出处理
        if (now == timestamp){
            seq = (seq + 1) & SEQ_MASK;
            // 溢出则等待1ms，并当前时间+1ms，再重新获取当前时间戳
            if (seq == 0L){
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                now = System.currentTimeMillis();
            }
        } else {
            seq = 0L;
        }
        //记录当前时间
        timestamp = now;
        // 组合雪花算法id long(64) =（符号位(1)-时间戳差值(41)-机器码(10)-序列号(12)）
        // 时间戳差值左移（64-1-41=22）位
        long handleTimeStamp = (now - START_TIMESTAMP) << 22;
        // 机器码左移（22-10=12）位
        long handleWorkId = WORK_ID << 12;
        return handleTimeStamp | handleWorkId | seq;
    }
}
