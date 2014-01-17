package me.zzp.clop.parsor;

import java.io.Serializable;
import java.util.regex.Pattern;

public final class Token implements Serializable {
  public static enum Type {
    Comment,
    Value,
    Keyword,
    EOL,
    EOS;
  }

  public final Type type;
  public final Pattern pattern;

  public Token(Type type, String pattern) {
    this.type = type;
    this.pattern = Pattern.compile(pattern);
  }

  public boolean is(Type type) {
    return this.type == type;
  }
}
