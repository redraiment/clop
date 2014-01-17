package me.zzp.clop;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import me.zzp.clop.parsor.ClopCode;
import me.zzp.clop.parsor.ClopElement;
import me.zzp.clop.parsor.ClopStatement;
import me.zzp.clop.runtime.JavaPackage;
import me.zzp.clop.runtime.Lambda;
import me.zzp.clop.runtime.Nil;

public final class Clop {
  public static Object eval(ClopCode code) {
    if (code == null || code.isEmpty()) {
      return Nil.nil;
    }

    Lambda lambda = Nil.nil;
    for (ClopStatement line : code) {
      ClopElement first = line.pollFirst();
      if (first.is(ClopElement.Type.Atom)) {
        lambda = new JavaPackage(first.atom());
        for (ClopElement message : line) {
          lambda = lambda.send(message);
        }
      }
    }

    return lambda;
  }
}
