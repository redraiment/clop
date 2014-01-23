package me.zzp.clop;

import javax.script.Bindings;

public interface Compilable {
  public Any compile(Bindings scope);
}
