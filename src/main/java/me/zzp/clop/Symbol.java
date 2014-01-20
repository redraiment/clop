package me.zzp.clop;

public final class Symbol extends Nil {
  private final String name;
  private final Symbol up;
  private Any reference;

  public Symbol(String name) {
    this.name = name.trim();
    this.up = null;
    this.reference = nil;
  }

  public Symbol(String name, Symbol up) {
    this.name = name.trim();
    this.up = up;
    this.reference = up == null? nil: up.reference;
  }

  public Symbol(String name, Any value) {
    this.name = name.trim();
    this.up = null;
    this.reference = value;
  }

  public Symbol up() {
    return up;
  }

  public Any assign(Any value) {
    return reference = value;
  }

  public Any value() {
    return reference;
  }

  @Override
  public String toString(int offset) {
    return name;
  }
}
