
package com.common.utils;
import java.math.BigInteger;

public class BigIntUtil {

  /**
   * @param value - not null
   * @return true - if the param is zero
   */
  public static boolean isZero(BigInteger value) {
    return value.compareTo(BigInteger.ZERO) == 0;
  }

  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return true - if the valueA is equal to valueB is zero
   */
  public static boolean isEqual(BigInteger valueA, BigInteger valueB) {
    return valueA.compareTo(valueB) == 0;
  }

  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return true - if the valueA is more than valueB is zero
   */
  public static boolean isMoreThan(BigInteger valueA, BigInteger valueB) {
    return valueA.compareTo(valueB) > 0;
  }

  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return true - if the valueA is less than valueB is zero
   */
  public static boolean isLessThan(BigInteger valueA, BigInteger valueB) {
    return valueA.compareTo(valueB) < 0;
  }

  /**
   * @param valueA - not null
   * @param valueB - not null
   * @return sum - valueA + valueB
   */
  public static BigInteger sum(BigInteger valueA, BigInteger valueB) {
    return valueA.add(valueB);
  }

  /**
   * Returns a result of safe addition of two {@code int} values {@code Integer.MAX_VALUE} is
   * returned if overflow occurs
   */
  public static int addSafely(int a, int b) {
    long res = (long) a + (long) b;
    return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
  }
}