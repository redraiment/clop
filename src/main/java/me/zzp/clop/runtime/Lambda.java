package me.zzp.clop.runtime;

import me.zzp.clop.parsor.ClopElement;

public interface Lambda {
  public Lambda send(ClopElement message);
}
