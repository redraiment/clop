package me.zzp.clop.runtime;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import me.zzp.clop.parsor.ClopElement;

public class JavaObject extends Nil {
  protected final Object javaObject;

  protected final List<Constructor> constructor;
  protected final Map<String, JavaMethod> method;
  protected final Map<String, Field> field;

  public JavaObject(Object javaObject) {
    this.javaObject = javaObject;

    constructor = new LinkedList<>();
    method = new HashMap<>();
    field = new HashMap<>();
    prepare(javaObject.getClass());
  }

  protected final void prepare(Class<?> javaClass) {
    constructor.addAll(Arrays.asList(javaClass.getConstructors()));
    
    for (Method m : javaClass.getMethods()) {
      String name = m.getName();
      JavaMethod wrapper;
      if (method.containsKey(name)) {
        wrapper = method.get(name);
      } else {
        wrapper = new JavaMethod(javaObject);
        method.put(name, wrapper);
      }
    }

    for (Field f : javaClass.getFields()) {
      field.put(f.getName(), f);
    }
  }

  @Override
  public Lambda send(ClopElement message) {
    if (message.is(ClopElement.Type.Atom)) {
      // eval
      String name = message.atom();
      if (field.containsKey(name)) {
        Field f = field.get(name);
        try {
          return new JavaObject(f.get(javaObject));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
          return super.send(message);
        }
      }
    } else {
      return super.send(message);
    }

    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String toString() {
    return javaObject.toString();
  }
}
