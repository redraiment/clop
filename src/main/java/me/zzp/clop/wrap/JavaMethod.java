package me.zzp.clop.wrap;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import me.zzp.clop.Any;
import me.zzp.clop.run.NunEval;

public final class JavaMethod extends JavaObject {
  private final Object javaObject;
  private final List<Method> methods;

  public JavaMethod(Object javaObject) {
    super(null);
    this.javaObject = javaObject;
    methods = new LinkedList<>();
  }

  public void add(Method m) {
    methods.add(m);
  }

  @Override
  public Any pass(Any thing) {
    if (thing instanceof NunEval) {
    }

    //return super.pass(scope, thing);
    return nil();
  }

  @Override
  public String toString(int offset) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
