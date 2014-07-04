package me.zzp.clop.bsh;

import java.io.IOException;
import java.io.Reader;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import me.zzp.clop.parse.Yacc;
import me.zzp.clop.Compilable;
import me.zzp.clop.Any;

public final class ClopScriptEngine extends AbstractScriptEngine {
  private final ScriptEngineFactory factory;
  private final Yacc yacc;

  public ClopScriptEngine(ScriptEngineFactory factory) {
    super(new ClopBindings());

    this.factory = factory;
    yacc = new Yacc();
  }

  /* REPL */
  @Override
  public Object eval(String snippet, ScriptContext context) throws ScriptException {
    try {
      yacc.append(snippet).append('\n');
      Compilable code = yacc.next();
      if (code != null) {
        Any object = code.compile(context.getBindings(ScriptContext.ENGINE_SCOPE));
        return object.pass(null);
      }
    } catch (IOException e) {
      System.err.println("ClopScriptEngine.eval(String, ScriptContext)");
      System.err.println(e.getMessage());
      throw new ScriptException(e);
    }

    return null;
  }

  /* Execute file */
  @Override
  public Object eval(Reader soruce, ScriptContext context) throws ScriptException {
    try {
      StringBuilder code = new StringBuilder();
      char[] snippet = new char[512];

      while (true) {
        int len = soruce.read(snippet);
        if (len <= 0) {
          break;
        }
        code.append(snippet);
      }

      yacc.append(code);
    } catch (IOException e) {
      System.err.println("ClopScriptEngine.eval(Reader, ScriptContext)");
      System.err.println(e.getMessage());
      throw new ScriptException(e);
    }

    Bindings scope = context.getBindings(ScriptContext.ENGINE_SCOPE);
    while (yacc.hasNext()) {
      Compilable code = yacc.next();
      if (code != null) {
        code.compile(scope).pass(null);
      } else {
        throw new ScriptException("Parse file failed.");
      }
    }

    return null;
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
