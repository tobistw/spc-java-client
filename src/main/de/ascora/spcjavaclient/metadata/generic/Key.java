package de.ascora.spcjavaclient.metadata.generic;

/**
 * Created by tobi on 22.09.2015.
 */
public class Key<T> {

    private T key;

    public Key(T key) {
        this.key = key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Key{" +
                "key=" + key +
                '}';
    }
}
