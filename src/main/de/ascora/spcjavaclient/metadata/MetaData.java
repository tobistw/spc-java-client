package de.ascora.spcjavaclient.metadata;

/**
 * Created by tobi on 22.09.2015.
 */
public abstract class MetaData {

    protected Entity entity;

    public String getAuthenticatedEntityId() {
        return entity.get_id();
    }

    public String getAuthenticatedEntityName() {
        return entity.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaData metaData = (MetaData) o;

        return !(entity != null ? !entity.equals(metaData.entity) : metaData.entity != null);

    }

    @Override
    public int hashCode() {
        return entity != null ? entity.hashCode() : 0;
    }
}
