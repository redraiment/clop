package me.zzp.clop.read;

import me.zzp.clop.Compilable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import me.zzp.clop.Any;
import me.zzp.clop.run.NunEval;
import me.zzp.clop.run.NunLazy;
import me.zzp.clop.run.NunSplice;

public final class Reader {
  public static Compilable read(Deque<String> pool) {
    return atom(pool);
  }

  private static Statement atom(Deque<String> pool) {
    List<Any> line = new ArrayList<>();

    enough:
    while (!pool.isEmpty()) {
      String token = pool.poll();
      switch (token) {
        case ",":
        case ")":
        case "]":
        case "}":
          if (!token.equals(",")) {
            pool.offerFirst(token);
          }
          break enough;
        case "(":
        case "[":
        case "{":
          line.add(list(pool));
          break;
        default:
          line.add(new Symbol(token));
          break;
      }
    }

    return line.isEmpty()? null: new Statement(line);
  }

  private static Nun list(Deque<String> pool) {
    List<Any> code = new ArrayList<>();
    char type = '.';

    while (!pool.isEmpty()) {
      Statement line = atom(pool);
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

    if (type == ')') {
      return new NunEval(code);
    } else if (type == ']') {
      return new NunSplice(code);
    } else if (type == '}') {
      return new NunLazy(code);
    } else {
      throw new ReadException("Reader.nun");
    }
  }
}
