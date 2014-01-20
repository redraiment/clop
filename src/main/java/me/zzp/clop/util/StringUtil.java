package me.zzp.clop.util;

import java.util.List;

public final class StringUtil {
  public static String join(String delimiter, List<? extends Object> list) {
    StringBuilder s = new StringBuilder();

    boolean first = true;
    for (Object o : list) {
      if (first) {
        first = false;
      } else {
        s.append(delimiter);
      }
      s.append(o);
    }

    return s.toString();
  }

  public static String times(String s, int n) {
    if (n <= 0) {
      return "";
    } else if (n == 1) {
      return s;
    } else {
      String sub = times(s, n / 2);
      return n % 2 == 1? sub.concat(sub).concat(s): sub.concat(sub);
    }
  }
}
