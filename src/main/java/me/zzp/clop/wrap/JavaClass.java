package me.zzp.clop.wrap;

import static me.zzp.clop.read.Nun.nil;
import me.zzp.clop.Any;
import me.zzp.clop.read.Symbol;

public final class JavaClass extends JavaObject {
  private final String className;

  public JavaClass(Class<?> javaClass) {
    super(javaClass);
    prepare(javaClass);
    className = javaClass.getName().toString().replaceAll("\\.", " ");;
  }

  @Override
  public Any pass(Any thing) {
    if (thing instanceof Symbol) {
      return super.pass(thing);
    } else {
      return nil();
    }
  }

  @Override
  public String toString() {
    return className;
  }  

//  public void alias(String javaMethodName, String alias) throws NoSuchMethodException {
//    if (hook.containsKey(javaMethodName)) {
//      hook.put(alias, hook.get(javaMethodName));
//    } else {
//      throw new NoSuchMethodException(javaMethodName);
//    }
//  }
//
//  public Object invoke(Object target, String method, Object... args) {
//    if (hook.containsKey(method)) {
//      Method fn = hook.get(method);
//      try {
//        fn.invoke(target, args);
//      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//        System.err.printf("JavaClassWrapper.invoke(%s.%s(%s))", target, method, args);
//        System.err.println(e.getMessage());
//      }
//    }
//    return null;
//  }

}
