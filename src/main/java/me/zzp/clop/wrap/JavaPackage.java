package me.zzp.clop.wrap;

import static me.zzp.clop.read.Nun.nil;
import me.zzp.clop.Any;
import me.zzp.clop.read.Symbol;

public class JavaPackage extends Symbol {
  public JavaPackage(String name) {
    super(name);
  }

  private JavaPackage(String name, JavaPackage upLevel) {
    super(name);
    this.upLevel = upLevel;
  }

  @Override
  public Any pass(Any thing) {
    if (thing instanceof Symbol) {
      String message = thing.toString();
      if (Character.isUpperCase(message.charAt(0))) {
        String path = toString().concat(" ").concat(message).replaceAll(" ", ".");
        try {
          return new JavaClass(Class.forName(path));
        } catch (ClassNotFoundException ex) {
          System.err.println("Class ".concat(path).concat(" does not exist"));
          System.err.println(ex.getMessage());
          return nil();
        }
      } else {
        return new JavaPackage(message, this);
      }
    } else {
      return nil();
    }
  }

  @Override
  public String toString() {
    return upLevel == null? name: upLevel.toString().concat(" ").concat(name);
  }
}
