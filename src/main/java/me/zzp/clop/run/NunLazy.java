package me.zzp.clop.run;

import me.zzp.clop.Any;
import java.util.LinkedList;
import java.util.List;
import javax.script.Bindings;
import me.zzp.clop.read.Nun;
import me.zzp.clop.util.StringUtil;

public class NunLazy extends Nun {
  public NunLazy(List<Any> list) {
    super(list);
  }

  @Override
  public Any compile(Bindings scope) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Any pass(Any thing) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String toString(int offset) {
    List<String> line = new LinkedList<>();

    line.add("{\n");
    for (Any e : list) {
      line.add(e.toString(offset + 2).concat("\n"));
    }
    line.add(StringUtil.times(" ", offset).concat("}"));

    return StringUtil.join("", line);
  }
}
