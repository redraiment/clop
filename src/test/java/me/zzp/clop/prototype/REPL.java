package me.zzp.clop.prototype;

import me.zzp.clop.parse.Yacc;
import me.zzp.clop.parse.Lex;

public class REPL {
    public static void main(String[] args) {
      Lex lex = new Lex("if (a > 10) { while {a > 10} {a print, a -= 10}   }");
      Yacc yacc = new Yacc(lex);
      System.out.println(yacc.next());
    }
}
