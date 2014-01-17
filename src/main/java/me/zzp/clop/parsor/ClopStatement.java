package me.zzp.clop.parsor;

import java.util.LinkedList;

public final class ClopStatement extends LinkedList<ClopElement> {
  public String toString(int offset) {
    StringBuilder line = new StringBuilder();
    for (int i = 0; i < offset; i++)
      line.append(' ');

    boolean rest = false;
    for (ClopElement e : this) {
      if (rest) {
        line.append(' ');
      } else {
        rest = true;
      }
      line.append(e.toString(offset));
    }
    return line.toString();
  }

  @Override
  public String toString() {
    return toString(0);
  }
}
