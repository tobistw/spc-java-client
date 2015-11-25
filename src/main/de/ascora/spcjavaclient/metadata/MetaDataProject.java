package de.ascora.spcjavaclient.metadata;

import de.ascora.spcjavaclient.metadata.generic.Preference;

/**
 * Created by tobi on 22.09.2015.
 */
public class MetaDataProject extends MetaData {

    private PublicPayload publicPayload;
    private PrivatePayload privatePayload;

    public MetaDataProject(Entity cremaEntity, PublicPayload publicPayload, PrivatePayload privatePayload) {
        super.entity = cremaEntity;
        this.publicPayload = publicPayload;
        this.privatePayload = privatePayload;
    }

    public MetaDataProject(Entity cremaEntity, PublicPayload publicPayload) {
        this.entity = cremaEntity;
        this.publicPayload = publicPayload;
        this.privatePayload = null;
    }

    public MetaDataProject(Entity cremaEntity) {
        this.entity = cremaEntity;
        this.publicPayload = null;
        this.privatePayload = null;
    }

    public PublicPayload getPublicPayload() {

        return this.publicPayload;
    }

    public PrivatePayload getPrivatePayload() {

        return this.privatePayload;
    }

    public boolean updatePublicPayload(String fieldName, String value) {
        if (fieldName.equals("")) {
            return false;
        }
        return this.publicPayload.updateFieldName(fieldName, value);
    }

    public boolean updatePrivatePayload(Preference preference) {
        if (privatePayload == null) {
            return false;
        } else {
            return this.privatePayload.addPreference(preference);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MetaDataProject that = (MetaDataProject) o;

        if (publicPayload != null ? !publicPayload.equals(that.publicPayload) : that.publicPayload != null) return false;
        return !(privatePayload != null ? !privatePayload.equals(that.privatePayload) : that.privatePayload != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (publicPayload != null ? publicPayload.hashCode() : 0);
        result = 31 * result + (privatePayload != null ? privatePayload.hashCode() : 0);
        return result;
    }
}
