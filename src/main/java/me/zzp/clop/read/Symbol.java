package me.zzp.clop.read;

import javax.script.Bindings;
import me.zzp.clop.Any;
import me.zzp.clop.wrap.JavaPackage;

public class Symbol implements Any {
  protected final String name;
  protected Bindings scope;
  protected Symbol upLevel;
  protected Any reference;

  public Symbol(String name) {
    this.name = name.trim();
    this.upLevel = null;
    this.reference = Nun.nil();
  }

  public Symbol upLevel() {
    return upLevel;
  }

  public Any assign(Any value) {
    return reference = value;
  }

  public String name() {
    return name;
  }

  public Any value() {
    return reference;
  }

  @Override
  public Any compile(Bindings scope) {
    this.scope = scope;
    if (scope.containsKey(name)) {
      return (Any) scope.get(name);
    } else if (name.matches("[a-z][a-z0-9]*")) {
      return new JavaPackage(name);
    } else if (name.matches("\\d+")) {
      // long
    } else if (name.matches("")) {
      // double
    }

    return this;
  }

  @Override
  public Any pass(Any thing) {
    if (thing instanceof Symbol) {
      String message = thing.toString();

      if (message.equals(":")) {
        return new SymbolAssign(this);
      }
    }

    return reference.pass(thing);
  }

  @Override
  public String toString(int offset) {
    return reference.toString(offset);
  }

  public static class SymbolAssign implements Any {
    private final Symbol self;
    private boolean first;

    public SymbolAssign(Symbol self) {
      this.self = self;
      first = true;
    }

    @Override
    public Any compile(Bindings scope) {
      return this;
    }

    @Override
    public Any pass(Any thing) {
      if (first) {
        first = false;
        if (thing instanceof Symbol) {
          self.upLevel = (Symbol) thing;
          self.assign(self.upLevel.reference);
        } else {
          self.upLevel = null;
          self.assign(thing);
        }

        self.scope.put(self.name, self);
      } else {
        self.assign(self.value().pass(thing));
      }
      return this;
    }

    @Override
    public String toString(int offset) {
      return self.name().concat(" :");
    }

    @Override
    public String toString() {
      return toString(0);
    }
  }
}
