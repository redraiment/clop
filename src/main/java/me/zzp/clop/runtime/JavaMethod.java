package me.zzp.clop.runtime;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import me.zzp.clop.parsor.ClopElement;

public final class JavaMethod extends Nil {
  private final Object javaObject;
  private final List<Method> list;

  public JavaMethod(Object javaObject) {
    this.javaObject = javaObject;
    list = new LinkedList<>();
  }

  public void add(Method m) {
    list.add(m);
  }

  @Override
  public Lambda send(ClopElement e) {
    if (e.is(ClopElement.Type.EvalList)) {
      
    } else {
      return super.send(e);
    }
    return super.send(e);
  }
}
