package me.zzp.clop.parsor;

import java.util.LinkedList;

public final class ClopCode extends LinkedList<ClopStatement> {
  public String toString(int offset) {
    StringBuilder code = new StringBuilder();
    for (ClopStatement line : this) {
      code.append(line.toString(offset)).append("\n");
    }
    return code.toString();
  }

  @Override
  public String toString() {
    return toString(0);
  }
}
