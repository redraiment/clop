package me.zzp.clop.parsor;

public final class ClopElement {
  public static enum Type {
    Atom,
    LazyList,
    EvalList,
    SpliceList;
  }

  private final Type type;
  private final String atom;
  private final ClopCode list;

  public ClopElement(String value) {
    atom = value;
    type = Type.Atom;
    list = null;
  }

  public ClopElement(Type type, ClopCode list) {
    this.atom = null;
    this.type = type;
    this.list = list;
  }

  public boolean is(Type type) {
    return this.type == type;
  }

  public String atom() {
    if (is(Type.Atom)) {
      return atom;
    } else {
      throw new ParseException("The element is not an atom");
    }
  }

  public ClopCode list() {
    if (!is(Type.Atom)) {
      return list;
    } else {
      throw new ParseException("The element is not a list");
    }
  }

  public String toString(int offset) {
    switch (type) {
      case EvalList:
        return '(' + list.toString(0).trim().replaceAll("\n", ", ") + ')';
      case SpliceList:
        return '[' + list.toString(0).replaceAll("\n", "") + ']';
      case LazyList:
        StringBuilder prefix = new StringBuilder("{\n");
        prefix.append(list.toString(offset + 2));
        for (int i = 0; i < offset; i++)
          prefix.append(' ');
        prefix.append('}');
        return prefix.toString();
      default:
        return atom;
    }
  }

  @Override
  public String toString() {
    return toString(0);
  }
}
