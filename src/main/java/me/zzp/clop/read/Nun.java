package me.zzp.clop.read;

import me.zzp.clop.Any;
import java.util.HashMap;
import java.util.List;
import me.zzp.clop.run.NunEval;
import me.zzp.clop.util.AbstractAssociativeList;

public abstract class Nun extends AbstractAssociativeList<Any> implements Any {
  public static Any nil() {
    return new NunEval();
  }

  protected Nun(List<Any> list) {
    super(list, new HashMap<String, Integer>());
  }

  @Override
  public Any get(int i) {
    return contains(i)? list.get(i): nil();
  }

  @Override
  public String toString() {
    return toString(0);
  }
}
