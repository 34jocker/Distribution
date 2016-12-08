package com.jzbwlkj.distribution.util;

import com.heima.googleplay.util.LogUtils;

/**
 * 创建者     伍碧林
 * 版权       传智播客.黑马程序员
 * 描述
 */
public class Constants {
    /*
    LogUtils.LEVEL_ALL:打开日志(显示所有的日志输出)
    LogUtils.LEVEL_OFF:关闭日志(屏蔽所有的日志输出)
     */
    public static final int DEBUGLEVEL = LogUtils.LEVEL_ALL;
    //设置的过期时间
    public static final long PROTOCOLTIMEOUT = 5 * 60 * 1000;//5分钟
    public static final class URLS {
        public static final String BASEURL = "http://192.168.117.29:8080/GooglePlayServer/";
        public static final String IMGBASEURL = BASEURL + "image?name=";

    }
}
