package me.zzp.clop.parse;

import java.io.IOException;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import me.zzp.clop.Compilable;
import me.zzp.clop.read.Reader;

public final class Yacc implements Iterator<Compilable>, Appendable {
  private final Deque<String> embed;
  private final Deque<String> pool;
  private final Lex lex;

  public Yacc() {
    this.embed = new LinkedList<>();
    this.pool = new LinkedList<>();
    this.lex = new Lex();
  }

  @Override
  public boolean hasNext() {
    return !pool.isEmpty();
  }

  public void pull() {
    while (lex.hasNext()) {
      String token = lex.next();
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
            return;
      }
    }
  }
  
  @Override
  public Compilable next() {
    pull();

    Compilable compiler = null;
    if (embed.isEmpty() && !pool.isEmpty()) {
      compiler = Reader.read(pool);
      pool.clear();
    }
    return compiler;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Yacc.remove");
  }

  @Override
  public Appendable append(CharSequence snippet) throws IOException {
    lex.append(snippet);
    return this;
  }

  @Override
  public Appendable append(CharSequence snippet, int start, int end) throws IOException {
    lex.append(snippet, start, end);
    return this;
  }

  @Override
  public Appendable append(char snippet) throws IOException {
    lex.append(snippet);
    return this;
  }
}
