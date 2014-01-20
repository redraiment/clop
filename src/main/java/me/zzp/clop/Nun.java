package me.zzp.clop;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public abstract class Nun extends Nil {
  protected final List<Statement> code;

  protected Nun(List<Statement> code) {
    this.code = code;
  }

  public static Nun read(Deque<String> pool) {
    List<Statement> code = new LinkedList<>();
    char type = ')';

    while (!pool.isEmpty()) {
      Statement line = Statement.read(pool);
      if (line != null) {
        code.add(line);
      }

      if (!pool.isEmpty()) {
        char close = pool.peek().charAt(0);
        if (close == ')' || close == ']' || close == '}') {
          type = pool.poll().charAt(0);
          break;
        }
      }
    }
    
    if (code.isEmpty()) {
      return null;
    } else if (type == ')') {
      return new NunEval(code);
    } else if (type == ']') {
      return new NunSplice(code);
    } else {
      return new NunLazy(code);
    }
  }

  @Override
  public String toString() {
    return toString(0);
  }
}
