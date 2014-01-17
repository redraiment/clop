package me.zzp.clop.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

public class StaticField {
    public static void main(String[] args) throws Exception {
      Class<BigInteger> type = BigInteger.class;
      for (Field field : type.getFields()) {
        field.setAccessible(true);
        if (Modifier.isStatic(field.getModifiers())) {
          System.out.println(field.getName() + ": " + field.get(type));
        }
      }
    }
}
