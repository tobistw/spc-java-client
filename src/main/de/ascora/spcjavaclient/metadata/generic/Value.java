package de.ascora.spcjavaclient.metadata.generic;

/**
 * Created by tobi on 22.09.2015.
 */
public class Value<T> {

    private T value;

    public Value(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "value=" + value +
                '}';
    }
}
