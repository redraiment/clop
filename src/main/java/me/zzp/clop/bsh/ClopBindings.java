package me.zzp.clop.bsh;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.script.Bindings;

/**
 * Support closure.
 * @author redraiment
 */
public final class ClopBindings implements Bindings {
  private final Bindings lexical;
  private final Map<String, Object> scope;

  public ClopBindings() {
    this(null);
  }

  public ClopBindings(Bindings lexical) {
    this.lexical = lexical;
    this.scope = new HashMap<>();
  }

  @Override
  public Object put(String name, Object value) {
    return scope.put(name, value);
  }

  @Override
  public void putAll(Map<? extends String, ? extends Object> toMerge) {
    scope.putAll(toMerge);
  }

  @Override
  public boolean containsKey(Object key) {
    return scope.containsKey(key) || (lexical != null && lexical.containsKey(key));
  }

  @Override
  public Object get(Object key) {
    Object value = scope.get(key);
    return value != null? value: (lexical != null? lexical.get(key): null);
  }

  @Override
  public Object remove(Object key) {
    return scope.remove(key);
  }

  @Override
  public int size() {
    return scope.size();
  }

  @Override
  public boolean isEmpty() {
    return scope.isEmpty();
  }

  @Override
  public boolean containsValue(Object value) {
    return scope.containsValue(value) || (lexical != null && lexical.containsValue(value));
  }

  @Override
  public void clear() {
    scope.clear();
  }

  @Override
  public Set<String> keySet() {
    return scope.keySet();
  }

  @Override
  public Collection<Object> values() {
    return scope.values();
  }

  @Override
  public Set<Entry<String, Object>> entrySet() {
    return scope.entrySet();
  }
}
