package me.zzp.clop.wrap;

import java.lang.reflect.Field;
import me.zzp.clop.read.Symbol;

public class JavaField extends Symbol {
  private final Object target;
  private final Field field;

  public JavaField(Object target, Field field) {
    super(field.getName());
    this.target = target;
    this.field = field;
  }
}
