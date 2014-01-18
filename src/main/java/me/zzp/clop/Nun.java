package me.zzp.clop;

import javax.script.Bindings;
import javax.script.SimpleBindings;

/**
 * 比Lambda更NB
 * @author redraiment
 */
public class Nun {
  private final Bindings lexicalScope;
  private final Bindings scope;

  public Nun(Bindings lexicalScope) {
    this.lexicalScope = lexicalScope;
    scope = new SimpleBindings();
  }
}
