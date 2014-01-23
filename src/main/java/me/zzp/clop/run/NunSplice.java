package me.zzp.clop.run;

import me.zzp.clop.Any;
import java.util.List;
import javax.script.Bindings;
import me.zzp.clop.read.Nun;
import me.zzp.clop.util.StringUtil;

public class NunSplice extends Nun {
  public NunSplice(List<Any> list) {
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
    return "[".concat(StringUtil.join(", ", list)).concat("]");
  }
}
