package me.zzp.clop.wrap;

import me.zzp.clop.Any;
import me.zzp.clop.Nil;
import me.zzp.clop.Symbol;

public class JavaPackage extends Nil {
  private final String name;
  
  public JavaPackage(String name) {
    this.name = name;
  }

  @Override
  public Any pass(Any thing) {
    if (thing instanceof Symbol) {
      String message = thing.toString();
      String path = name.concat(".").concat(message);
      if (Character.isUpperCase(message.charAt(0))) {
        try {
          return new JavaClass(Class.forName(path));
        } catch (ClassNotFoundException ex) {
          System.err.println("Class ".concat(path).concat(" does not exist"));
          System.err.println(ex.getMessage());
          return nil;
        }
      } else {
        return new JavaPackage(path);
      }
    } else {
      return nil;
    }
  }

  @Override
  public String toString() {
    return name.replaceAll("\\.", " ");
  }
}
