package de.ascora.spcjavaclient.metadata;

/**
 * Created by tobi on 22.09.2015.
 */
public class PublicPayload {
    private String fieldName1;
    private String fieldName2;
    private String fieldName3;

    private static final String CONFIG_FIELDNAME1 = "address";
    private static final String CONFIG_FIELDNAME2 = "company";
    private static final String CONFIG_FIELDNAME3 = "factory";

    public PublicPayload(String fieldName1, String fieldName2, String fieldName3) {
        this.fieldName1 = fieldName1;
        this.fieldName2 = fieldName2;
        this.fieldName3 = fieldName3;
    }

    public boolean updateFieldName(String fieldName, String value) {
        switch (fieldName) {
            case CONFIG_FIELDNAME1 :
                fieldName1 = value;
                return true;

            case CONFIG_FIELDNAME2 :
                fieldName2 = value;
                return true;

            case CONFIG_FIELDNAME3 :
                fieldName3 = value;
                return true;

            default:
                return false;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicPayload that = (PublicPayload) o;

        if (fieldName1 != null ? !fieldName1.equals(that.fieldName1) : that.fieldName1 != null) return false;
        if (fieldName2 != null ? !fieldName2.equals(that.fieldName2) : that.fieldName2 != null) return false;
        return !(fieldName3 != null ? !fieldName3.equals(that.fieldName3) : that.fieldName3 != null);

    }

    @Override
    public int hashCode() {
        int result = fieldName1 != null ? fieldName1.hashCode() : 0;
        result = 31 * result + (fieldName2 != null ? fieldName2.hashCode() : 0);
        result = 31 * result + (fieldName3 != null ? fieldName3.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PublicPayload{" +
                "fieldName1='" + fieldName1 + '\'' +
                ", fieldName2='" + fieldName2 + '\'' +
                ", fieldName3='" + fieldName3 + '\'' +
                '}';
    }
}
