package me.zzp.clop.runtime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import me.zzp.clop.parsor.ClopCode;
import me.zzp.clop.parsor.ClopElement;

public final class JavaMethodWrapper {
  private final List<Method> fn;

  public JavaMethodWrapper(Method... methods) {
    fn = new LinkedList<>();
    addAll(methods);
  }

  public void add(Method method) {
    fn.add(method);
  }

  public void addAll(Method... methods) {
    fn.addAll(Arrays.asList(methods));
  }
  
  private Object[] cast(ClopCode args) {
    
    return null;
  }

  private boolean accept(Class<?>[] args, ClopCode params) {
    return false;
  }

  public Lambda invoke(Object target, ClopElement message) {
    if (message.is(ClopElement.Type.EvalList)) {
      ClopCode params = message.list();

      for (Method call : fn) {
        Class<?>[] args = call.getParameterTypes();
        if (accept(args, params)) {
          try {
            call.invoke(target);
          } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.err.println("JavaMethod.send");
            System.err.println(e.getMessage());
          }
          break;
        }
      }
    }

    return Nil.nil;
  }
}
