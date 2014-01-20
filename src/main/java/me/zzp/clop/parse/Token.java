package me.zzp.clop.parse;

import java.util.regex.Pattern;

final class Token {
  static enum Type {
    Comment,
    Value,
    Keyword,
    EOL,
    EOS;
  }

  final Type type;
  final Pattern pattern;

  Token(Type type, String pattern) {
    this.type = type;
    this.pattern = Pattern.compile(pattern);
  }

  boolean is(Type type) {
    return this.type == type;
  }
}
