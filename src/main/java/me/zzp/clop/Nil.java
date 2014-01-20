package me.zzp.clop;

public class Nil implements Any {
  public final static Nil nil = new Nil();

  @Override
  public Any pass(Any thing) {
    return nil;
  }

  @Override
  public String toString(int offset) {
    return "";
  }
}
