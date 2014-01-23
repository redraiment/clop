package me.zzp.clop.util;

import java.util.Set;

public interface AssociativeList<E> extends Iterable<E> {
  public void add(E element);
  public void add(String key, E element);
  public void add(int index, E element);

  public void clear();

  public boolean contains(String key);
  public boolean contains(int index);

  public E get(String key);
  public E get(int index);

  public boolean isEmpty();

  public int indexOf(String key);
  public Set<String> keySet();

  public void set(String key, E element);
  public void set(int index, E element);

  public int size();

  public Object[] toArray();
}
