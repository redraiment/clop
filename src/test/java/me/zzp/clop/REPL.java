package me.zzp.clop;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class REPL {
    public static void main(String[] args) throws ScriptException {
      ScriptEngineManager engine = new ScriptEngineManager();
      ScriptEngine clop = engine.getEngineByName("Clop");
      System.out.println(clop.eval("java math BigInteger ZERO"));
//      System.out.println(clop.eval("1 times {"));
//      System.out.println(clop.eval("  \"Hello world\" print"));
//      System.out.println(clop.eval("}"));
    }
}
