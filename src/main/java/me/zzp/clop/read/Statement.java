package me.zzp.clop.read;

import java.util.ArrayList;
import java.util.List;
import javax.script.Bindings;
import me.zzp.clop.Any;

public class Statement extends Nun {
  Statement(List<Any> list) {
    super(list);
  }

  @Override
  public Any compile(Bindings scope) {
    List<Any> compiled = new ArrayList<>();
    for (Any thing : list) {
      compiled.add(thing.compile(scope));
    }
    list.clear();
    list.addAll(compiled);

    return this;
  }

  @Override
  public Any pass(Any thing) {
    boolean first = true;
    Any subject = nil();
    for (Any message : list) {
      if (first) {
        first = false;
        subject = message;
      } else {
        subject = subject.pass(message);
      }
    }
    return subject;
  }

  @Override
  public String toString(int offset) {
    StringBuilder s = new StringBuilder();
    boolean first = true;
    for (Any e : list) {
      if (first) {
        first = false;
        for (int i = 0; i < offset; i++) {
          s.append(' ');
        }
      } else {
        s.append(' ');
      }
      s.append(e.toString(offset));
    }
    return s.toString();
  }
}
