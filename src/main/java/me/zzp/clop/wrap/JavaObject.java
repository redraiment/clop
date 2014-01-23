package me.zzp.clop.wrap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import me.zzp.clop.Any;
import me.zzp.clop.read.Symbol;
import me.zzp.clop.run.NunEval;

public class JavaObject extends NunEval {
  protected final Object javaObject;

  public JavaObject(Object javaObject) {
    super();

    this.javaObject = javaObject;
    prepare(javaObject.getClass());
  }

  public final Object raw() {
    return javaObject;
  }

  protected final void prepare(Class<?> javaClass) {
    for (Field f : javaClass.getFields()) {
      add(f.getName(), new JavaField(javaObject, f));
    }

    for (Method m : javaClass.getMethods()) {
      String name = m.getName();
      JavaMethod wrapper;
      if (contains(name)) {
        wrapper = (JavaMethod) get(name);
      } else {
        wrapper = new JavaMethod(javaObject);
        add(name, wrapper);
      }
      wrapper.add(m);
    }
  }

  @Override
  public Any pass(Any thing) {
    if (thing instanceof Symbol) {
      String name = thing.toString();

      if (contains(name)) {
        Any object = get(name);
        return object.pass(thing);
      }
    }

    return super.pass(thing);
  }

  @Override
  public String toString() {
    return javaObject.toString();
  }

  @Override
  public String toString(int offset) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
