package me.zzp.clop.runtime;

import me.zzp.clop.parsor.ClopElement;

public class Nil implements Lambda {
  public final static Nil nil = new Nil();

  @Override
  public Lambda send(ClopElement message) {
    return nil;
  }

  @Override
  public String toString() {
    return "";
  }
}
