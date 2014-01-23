package me.zzp.clop;

public interface Any extends PrettyPrint, Compilable {
  public Any pass(Any thing);
}
