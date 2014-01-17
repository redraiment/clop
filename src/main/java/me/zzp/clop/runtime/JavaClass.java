package me.zzp.clop.runtime;

import me.zzp.clop.parsor.ClopElement;

public final class JavaClass extends JavaObject {
  private final String className;

  public JavaClass(Object javaObject) {
    super(javaObject);

    Class javaClass;
    if (javaObject instanceof Class) {
      javaClass = (Class) javaObject;
      prepare(javaClass);
    } else {
      javaClass = javaObject.getClass();
    }
    className = javaClass.getName().toString().replaceAll("\\.", " ");;
  }

  @Override
  public Lambda send(ClopElement e) {
    if (e.is(ClopElement.Type.Atom)) {
      return super.send(e);
    } else {
      return nil;
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
