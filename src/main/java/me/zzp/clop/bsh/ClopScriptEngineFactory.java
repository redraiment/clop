package me.zzp.clop.bsh;

import java.util.Arrays;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public final class ClopScriptEngineFactory implements ScriptEngineFactory {
  private final ScriptEngine engine;

  public ClopScriptEngineFactory() {
    engine = new ClopScriptEngine(this);
  }

  @Override
  public String getEngineName() {
    return "JClop";
  }

  @Override
  public String getEngineVersion() {
    return "1.0";
  }

  @Override
  public List<String> getExtensions() {
    return Arrays.asList(".clop");
  }

  @Override
  public List<String> getMimeTypes() {
    return Arrays.asList("application/clop", "text/clop");
  }

  @Override
  public List<String> getNames() {
    return Arrays.asList("clop", "Clop");
  }

  @Override
  public String getLanguageName() {
    return "Clop";
  }

  @Override
  public String getLanguageVersion() {
    return "1.0";
  }

  @Override
  public Object getParameter(String key) {
    // nothing special
    return null;
  }

  @Override
  public String getMethodCallSyntax(String object, String method, String... args) {
    StringBuilder call = new StringBuilder(object);
    call.append(' ').append(method).append(" (");
    for (int i = 0; i < args.length; i++) {
      if (i > 0) {
        call.append(", ");
      }
      call.append(args[i]);
    }
    call.append(')');
    return call.toString();
  }

  @Override
  public String getOutputStatement(String toDisplay) {
    return toDisplay.concat(" print");
  }

  @Override
  public String getProgram(String... statements) {
    StringBuilder code = new StringBuilder();
    for (String line : statements) {
      code.append(line).append('\n');
    }
    return code.toString();
  }

  @Override
  public ScriptEngine getScriptEngine() {
    return engine;
  }
}
