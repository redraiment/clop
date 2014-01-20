package me.zzp.clop;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Statement extends Nil {
  private final List<Any> line;

  private Statement(List<Any> line) {
    this.line = line;
  }

  public static Statement read(Deque<String> pool) {
    List<Any> line = new LinkedList<>();

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
          line.add(Nun.read(pool));
          break;
        default:
          line.add(new Symbol(token));
          break;
      }
    }

    return line.isEmpty()? null: new Statement(line);
  }

  @Override
  public String toString(int offset) {
    StringBuilder s = new StringBuilder();
    boolean first = true;
    for (Any e : line) {
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

  @Override
  public String toString() {
    return toString(0);
  }
}
