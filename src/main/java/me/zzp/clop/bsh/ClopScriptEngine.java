package me.zzp.clop.bsh;

import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import me.zzp.clop.Clop;
import me.zzp.clop.parsor.Lex;
import me.zzp.clop.parsor.Yacc;

public final class ClopScriptEngine extends AbstractScriptEngine {
  private final ScriptEngineFactory factory;
  private final Yacc yacc;
  private final Lex lex;

  public ClopScriptEngine(ScriptEngineFactory factory) {
    super(new ClopBindings());

    this.factory = factory;
    lex = new Lex();
    yacc = new Yacc(lex);
  }

  /**
   * REPL.
   * @param line
   * @param context
   * @return
   * @throws ScriptException 
   */
  @Override
  public Object eval(String line, ScriptContext context) throws ScriptException {
    try {
      lex.append(line).append("\n");
      return Clop.eval(yacc.next());
    } catch (IOException e) {
      System.err.println("ClopScriptEngine.eval(String, ScriptContext)");
      System.err.println(e.getMessage());
      throw new ScriptException(e);
    }
  }

  /**
   * Execute file.
   * @param reader
   * @param context
   * @return
   * @throws ScriptException 
   */
  @Override
  public Object eval(Reader reader, ScriptContext context) throws ScriptException {
    StringBuilder code = new StringBuilder();
    char[] buffer = new char[512];

    try {
      while (true) {
        int len = reader.read(buffer);
        if (len <= 0) {
          break;
        }
        code.append(buffer);
      }
      lex.append(code.toString());
    } catch (IOException e) {
      System.err.println("ClopScriptEngine.eval(Reader, ScriptContext)");
      System.err.println(e.getMessage());
      throw new ScriptException(e);
    }

    Object result = Clop.eval(yacc.next());
    if (yacc.hasNext()) {
      throw new ScriptException("Parse file failed.");
    }
    return result;
  }

  @Override
  public Bindings createBindings() {
    return new ClopBindings();
  }

  @Override
  public ScriptEngineFactory getFactory() {
    return factory;
  }
}
