package me.zzp.clop.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* 多线程不安全 */
public abstract class AbstractAssociativeList<E> implements AssociativeList<E> {
  protected final List<E> list;
  protected final Map<String, Integer> index;

  protected AbstractAssociativeList(List<E> list, Map<String, Integer> index) {
    this.list = list;
    this.index = index;
  }

  @Override
  public void add(E element) {
    list.add(element);
  }

  @Override
  public void add(String key, E element) {
    index.put(key, index.size());
    add(element);
  }

  @Override
  public void add(int i, E element) {
    list.add(i, element);
    for (Map.Entry<String, Integer> e : index.entrySet()) {
      if (e.getValue() >= i) {
        e.setValue(e.getValue() + 1);
      }
    }
  }

  @Override
  public void clear() {
    list.clear();
    index.clear();
  }

  @Override
  public boolean contains(String key) {
    return index.containsKey(key);
  }

  @Override
  public boolean contains(int index) {
    return 0 <= index && index < list.size();
  }

  @Override
  public E get(String key) {
    try {
      return get(Integer.parseInt(key));
    } catch (NumberFormatException e) {
      return get(indexOf(key));
    }
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public int indexOf(String key) {
    return contains(key)? index.get(key): -1;
  }

  @Override
  public Set<String> keySet() {
    return index.keySet();
  }

  @Override
  public void set(String key, E element) {
    if (contains(key)) {
      set(index.get(key), element);
    } else {
      add(key, element);
    }
  }

  @Override
  public void set(int index, E element) {
    if (contains(index)) {
      list.set(index, element);
    }
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }
}
