package com.guillaumegonnet.scorekeeperV2;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Guillaume Gonnet on 17/05/20.
 */
public class IndexedLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ArrayList<K> al_Index = new ArrayList<K>();

    @Override
    public V put(K key, V val) {
        if (!super.containsKey(key)) al_Index.add(key);
        V returnValue = super.put(key, val);
        return returnValue;
    }

    public V getValueAtIndex(int i) {
        return super.get(al_Index.get(i));
    }

    public K getKeyAtIndex(int i) {
        return al_Index.get(i);
    }

    public int getIndexOf(K key) {
        return al_Index.indexOf(key);
    }

}