package com.common.utils;

import java.sql.Timestamp;

public class TimeUtils {

  public static String TIME_SECOND = 1;
  
  public static String TIME_MIUNTE = 60;
  
  public static long getCurrentMillis() {
    return System.currentTimeMillis();
  }

  public static String getTimeString(long time) {
    return new Timestamp(time).toString();
  }
  
  public static long minutesToMillis(long minutes) {
    return minutes * 60 * 1000;
  }
  public static long secondsToMillis(long seconds) {
    return seconds * 1000;
  }
  public static long millisToMinutes(long millis) {
    return Math.round(millis / 60.0 / 1000.0);
  }
  public static long millisToSeconds(long millis) {
    return Math.round(millis / 1000.0);
  }
  public static long timeAfterMillis(long millis) {
    return System.currentTimeMillis() + millis;
  }
}
