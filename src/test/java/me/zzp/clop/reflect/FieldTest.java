package me.zzp.clop.reflect;

import java.lang.reflect.Field;

class Foo {
  private int i;
  private Integer a;

  @Override
  public String toString() {
    return "Foo{" + "i=" + i + ", a=" + a + '}';
  }
}

public class FieldTest {
  public static void main(String[] args) throws Exception {
    Foo o = new Foo();

    Class<Foo> type = Foo.class;

    Field meta_i = type.getDeclaredField("i");
    meta_i.setAccessible(true);
    meta_i.set(o, Integer.valueOf(1));

    Field meta_a = type.getDeclaredField("a");
    meta_a.setAccessible(true);
    Class<?> type_a = meta_a.getType();
    System.out.println(type_a.getName());
    meta_a.set(o, null);

    System.out.println(o);
  }
}
