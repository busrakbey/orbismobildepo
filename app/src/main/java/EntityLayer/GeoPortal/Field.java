package EntityLayer.GeoPortal;



/**
 * Created by Ömer YILDIRIM on 27.4.2016.
 */
public class Field
{
        public static enum Types {
            NOT_SET(""), TYPE_SMALL_INTEGER("esriFieldTypeSmallInteger"), TYPE_INTEGER("esriFieldTypeInteger"), TYPE_SINGLE("esriFieldTypeSingle"), TYPE_DOUBLE(
                    "esriFieldTypeDouble"), TYPE_STRING("esriFieldTypeString"), TYPE_DATE("esriFieldTypeDate"), TYPE_OID("esriFieldTypeOID"), TYPE_GEOMETRY(
                    "esriFieldTypeGeometry"), TYPE_BLOB("esriFieldTypeBlob"), TYPE_RASTER("esriFieldTypeRaster"), TYPE_GUID("esriFieldTypeGUID"), TYPE_GLOBALID(
                    "esriFieldTypeGlobalID"), TYPE_XML("esriFieldTypeXML");
            private String val;

            private Types(String value) {
                val = value;
            }

            public String getValue() {
                return val;
            }

            public static Types ValueOf(String value) {
                if (value != null) {
                    for (int i = 0; i < Types.values().length; i++) {
                        if (Types.values()[i].getValue().contentEquals(value)) {
                            return Types.values()[i];
                        }
                    }
                    return Types.NOT_SET;
                }
                return Types.NOT_SET;
            }
        }

        public String name;
        public String type;
        public String alias;
        public Integer length;


    public Types getType() {
        Types type = Types.ValueOf(this.type);
        return type;
    }

}
