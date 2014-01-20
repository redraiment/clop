package me.zzp.clop.parse;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import me.zzp.clop.parse.Token.Type;

public final class Lex implements Iterator<String>, Appendable {
  private final static Token[] MODEL = new Token[] {
    new Token(Type.Comment, "#.*"),
    new Token(Type.Value, "\"[^\\\\\"]*(?:\\\\.[^\\\\\"]*)*\""),
    new Token(Type.Keyword, "[:'~@()\\[\\]{}]"),
    new Token(Type.EOS, "[\\\\][,\\r\\n]+"),
    new Token(Type.Value, "[^\\s:~@,(){}\\[\\]'\"]+"),
    new Token(Type.EOL, "[,\\r\\n]+"),
    new Token(Type.EOS, "\\s+")
  };

  private final StringBuilder code;

  public Lex() {
    this("");
  }

  public Lex(String code) {
    this.code = new StringBuilder(code);
  }

  @Override
  public boolean hasNext() {
    return code.length() > 0;
  }

  @Override
  public String next() {
    while (hasNext()) {
      Matcher token = MODEL[0].pattern.matcher(code);
      for (Token model : MODEL) {
        token.usePattern(model.pattern);
        if (!token.lookingAt())
          continue;

        String value = token.group();
        code.delete(0, value.length());

        if (model.is(Type.Comment) || model.is(Type.EOS))
          break;
        
        return model.is(Type.EOL)? ",": value;
      }
    }

    return null;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("Lex.remove");
  }

  @Override
  public Appendable append(CharSequence snippet) throws IOException {
    code.append(snippet);
    return this;
  }

  @Override
  public Appendable append(CharSequence snippet, int start, int end) throws IOException {
    code.append(snippet, start, end);
    return this;
  }

  @Override
  public Appendable append(char snippet) throws IOException {
    code.append(snippet);
    return this;
  }
}
