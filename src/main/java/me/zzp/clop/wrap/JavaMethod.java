package me.zzp.clop.wrap;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import me.zzp.clop.Any;
import me.zzp.clop.NunEval;
import me.zzp.clop.Nil;

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
  public Any pass(Any thing) {
    if (thing instanceof NunEval) {
    } else {
      return super.pass(thing);
    }
    return super.pass(thing);
  }
}
