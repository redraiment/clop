package me.zzp.clop;

import java.util.Iterator;
import me.zzp.clop.parsor.Lex;

public class LexTest {
  public static void dump(Iterator<String> it) {
    while (it.hasNext()) {
      System.out.println(it.next());
    }
    System.out.println();
  }
  
  public static void main(String[] args) throws Exception {
    Lex lex = new Lex();
    dump(lex);

    lex.append("hello world");
    dump(lex);

    lex.append("nice to meet you!~");
    dump(lex);
  }
}
