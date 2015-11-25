package de.ascora.spcjavaclient.metadata;

import java.util.Arrays;

/**
 * Created by tobi on 22.09.2015.
 */
public class Entity {
    private String _id;
    private String name;
    private String[] roles;

    public Entity(String _id, String name, String[] roles) {
        this._id = _id;
        this.name = name;
        this.roles = roles;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (_id != null ? !_id.equals(entity._id) : entity._id != null) return false;
        return !(name != null ? !name.equals(entity.name) : entity.name != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
