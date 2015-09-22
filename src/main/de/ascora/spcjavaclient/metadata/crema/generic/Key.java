package de.ascora.spcjavaclient.metadata.crema.generic;

/**
 * Created by tobi on 22.09.2015.
 */
public class Key<K> {

    private K key;

    public Key(K key) {
        this.key = key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }
}
