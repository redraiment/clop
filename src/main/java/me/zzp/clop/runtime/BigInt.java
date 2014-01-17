package me.zzp.clop.runtime;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import me.zzp.clop.Clop;
import me.zzp.clop.parsor.ClopElement;

public class BigInt implements Lambda {
  private final static Map<String, Method> hook;

  static {
    hook = new HashMap<>();

    Class<BigInteger> parent = BigInteger.class;
    try {
      hook.put("+", parent.getMethod("add", BigInteger.class));
      hook.put("-", parent.getMethod("subtract", BigInteger.class));
      hook.put("*", parent.getMethod("multiply", BigInteger.class));
      hook.put("/", parent.getMethod("divide", BigInteger.class));
    } catch (NoSuchMethodException | SecurityException e) {
      System.err.println("BigInt.static");
      System.err.println(e.getMessage());
    }
  }
  
  private final static class BiFunction implements Lambda {
    private final BigInteger left;
    private final String op;

    public BiFunction(BigInteger left, String op) {
      this.left = left;
      this.op = op;
    }

    @Override
    public Lambda send(ClopElement e) {
      Object right = null;

      if (e.is(ClopElement.Type.Atom)) {
        String message = e.atom();
        if (message.matches("\\d+")) {
          right = new BigInteger(message);
        }
      } else if (e.is(ClopElement.Type.SpliceList)) {
        right = Clop.eval(e.list());
        if (right != null && right instanceof BigInt) {
          right = ((BigInt)right).value;
        }
      }

      try {
        if (right != null) {
          Method fn = hook.get(op);
          Object result = fn.invoke(left, right);
          if (result != null && result instanceof BigInteger) {
            return new BigInt((BigInteger) result);
          }
        }
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        System.err.println("BigInt.Lambda.invoke");
        System.err.println(ex.getMessage());
      }

      return Nil.nil;
    }

    @Override
    public String toString() {
      return left.toString().concat(" ").concat(op);
    }
  }

  private final BigInteger value;
  
  private BigInt(BigInteger value) {
    this.value = value;
  }

  public BigInt(String s) {
    value = new BigInteger(s);
  }

  @Override
  public Lambda send(ClopElement e) {
    if (e.is(ClopElement.Type.Atom)) {
      String message = e.atom();
      
      if (message.equals("+1")) {
        return new BigInt(value.add(BigInteger.ONE));
      } else if (message.equals("-1")) {
        return new BigInt(value.subtract(BigInteger.ONE));
      } else if (hook.containsKey(message)) {
        return new BiFunction(value, message);
      }
    }

    return Nil.nil;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
