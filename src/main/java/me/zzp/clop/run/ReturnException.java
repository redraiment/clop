package me.zzp.clop.run;

import me.zzp.clop.Any;

public class ReturnException extends RuntimeException {
  public final Any value;

  public ReturnException(Any value) {
    this.value = value;
  }
}
