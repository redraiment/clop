package me.zzp.clop.parse;

import me.zzp.clop.Statement;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public final class Yacc implements Iterator<Statement> {
  private final Iterator<String> source;
  private final Deque<String> embed;
  private final Deque<String> pool;

  public Yacc(Iterator<String> soruce) {
    this.embed = new LinkedList<>();
    this.pool = new LinkedList<>();
    this.source = soruce;
  }

  @Override
  public boolean hasNext() {
    return !pool.isEmpty();
  }

  @Override
  public Statement next() {
    enough:
    while (source.hasNext()) {
      String token = source.next();
      if (token == null)
        continue;

      pool.offer(token);
      switch (token) {
        case "(":
          embed.offerFirst(")");
          break;
        case "[":
          embed.offerFirst("]");
          break;
        case "{":
          embed.offerFirst("}");
          break;
        case ")":
        case "]":
        case "}":
          if (embed.isEmpty()) {
            throw new ParseException("Unmatch bracket for " + token);
          } else if (token.equals(embed.peekFirst())) {
            embed.pollFirst();
          } else {
            throw new ParseException("expect " + embed.peekFirst() + ", actual " + token);
          }
          break;
        case ",":
          if (embed.isEmpty())
            break enough;
      }
    }

    Statement line = null;
    if (embed.isEmpty() && !pool.isEmpty()) {
      line = Statement.read(pool);
      pool.clear();
    }
    return line;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Yacc.remove");
  }
}
