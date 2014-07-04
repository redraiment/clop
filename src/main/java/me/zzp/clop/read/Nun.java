package me.zzp.clop.read;

import me.zzp.clop.Any;
import java.util.HashMap;
import java.util.List;
import javax.script.Bindings;
import me.zzp.clop.run.NunEval;
import me.zzp.clop.util.AbstractAssociativeList;
import me.zzp.clop.wrap.JavaObject;

/* 数据结构 */
public abstract class Nun extends AbstractAssociativeList<Any> implements Any {
  private static <T> T cast(Object thing) {
    return (T) thing;
  }

  public static Any nil() {
    return new NunEval();
  }

  protected Bindings lexical;

  protected Nun(List<Any> list) {
    super(list, new HashMap<String, Integer>());
  }

  @Override
  public Any get(int i) {
    return contains(i)? list.get(i): nil();
  }

  @Override
  public Any compile(Bindings scope) {
    lexical = scope;
    return this;
  }
  
  @Override
  public Any pass(Any thing) {
    if (thing instanceof Symbol) {
      Symbol symbol = cast(thing);
      return get(symbol.name());
    } else if (thing instanceof JavaObject) {
      JavaObject java = cast(thing);
      Object object = java.raw();
      if (object instanceof Number) {
        Number n = cast(object);
        return get(n.intValue());
      }
    }

    return nil();
  }

  @Override
  public String toString() {
    return toString(0);
  }
}
