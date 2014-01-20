package me.zzp.clop;

import java.util.List;
import me.zzp.clop.util.StringUtil;

public class NunSplice extends Nun {
  public NunSplice(List<Statement> code) {
    super(code);
  }

  @Override
  public String toString(int offset) {
    return "[".concat(StringUtil.join(", ", code)).concat("]");
  }
}
