package me.zzp.clop.parsor;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public final class Yacc implements Iterator<ClopCode> {
  private final Iterator<String> source;
  private final Deque<String> embed;
  private final Queue<String> pool;

  public Yacc(Iterator<String> soruce) {
    this.embed = new LinkedList<>();
    this.pool = new LinkedList<>();
    this.source = soruce;
  }

  @Override
  public boolean hasNext() {
    return !pool.isEmpty();
  }

  private ClopStatement nextStatement() throws ParseException {
    ClopStatement line = new ClopStatement();
    while (hasNext()) {
      String token = pool.poll();
      switch (token) {
        case ",":
          return line;
        case ")":
        case "]":
        case "}":
          line.add(new ClopElement(token));
          return line;
        case "(":
          line.add(new ClopElement(ClopElement.Type.EvalList, nextCode(")")));
          break;
        case "[":
          line.add(new ClopElement(ClopElement.Type.SpliceList, nextCode("]")));
          break;
        case "{":
          line.add(new ClopElement(ClopElement.Type.LazyList, nextCode("}")));
          break;
        default:
          line.add(new ClopElement(token));
          break;
      }
    }
    return line;
  }

  private ClopCode nextCode(String end) throws ParseException {
    ClopCode code = new ClopCode();
    for (boolean finish = false; !finish && hasNext();) {
      ClopStatement statement = nextStatement();

      if (!statement.isEmpty() && end != null) {
        ClopElement e = statement.peekLast();
        if (e.is(ClopElement.Type.Atom) && e.atom().equals(end)) {
          statement.pollLast();
          finish = true;
        }
      }

      if (!statement.isEmpty()) {
        code.add(statement);
      }
    }
    return code;
  }

  @Override
  public ClopCode next() {
    enough:
    while (source.hasNext()) {
      String token = source.next();
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
          break enough;
      }
    }

    ClopCode code = null;
    if (embed.isEmpty()) {
      code = nextCode(null);
      pool.clear();
    }
    return code;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Yacc.remove");
  }
}
