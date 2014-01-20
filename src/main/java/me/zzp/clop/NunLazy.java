package me.zzp.clop;

import java.util.LinkedList;
import java.util.List;
import me.zzp.clop.util.StringUtil;

public class NunLazy extends Nun {
  public NunLazy(List<Statement> code) {
    super(code);
  }

  @Override
  public String toString(int offset) {
    List<String> line = new LinkedList<>();

    line.add("{\n");
    for (Statement s : code) {
      line.add(s.toString(offset + 2).concat("\n"));
    }
    line.add(StringUtil.times(" ", offset).concat("}"));

    return StringUtil.join("", line);
  }
}
