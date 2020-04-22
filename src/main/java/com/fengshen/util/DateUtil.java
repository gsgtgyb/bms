package com.fengshen.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateUtil {

    public static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");//
        Date date = new Date();// 获取当前时间
        return sdf.format(date); // 输出已经格式化的现在时间（24小时制）
    }

    public static String getNumber() {
        Random random = new Random();
        int ends = random.nextInt(999999);

        return String.format("%06d", ends);
    }
}