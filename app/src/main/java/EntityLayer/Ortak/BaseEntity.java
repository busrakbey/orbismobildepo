package EntityLayer.Ortak;

import java.util.HashMap;
import java.util.Map;

import InterfaceLayer.IEntityView;

/**
 * Created by Ömer YILDIRIM on 9.2.2016.
 */
abstract public class BaseEntity implements IEntityView {

   /* @Column(name = "id")
    private Long id;
    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;

    @Column(name = "orgId")
    private Long orgId;

    @Column(name = "xKoordinat")
    private Double xKoordinat;

    @Column(name = "yKoordinat")
    private Double yKoordinat;

    @Column(name = "gonderildi")
    private Integer gonderildi=0;

*/

    /// Extend Fields
//    @Column(name = "id")
//    private Long id;

///Extend Fields getters and setters


//////////////////////////  SINIR
    private Map<String, Object> lazyCollectionRawValues;
    public Map<String, Object> getLazyCollectionRawValues() {
        return lazyCollectionRawValues;
    }

    public void setLazyCollectionRawValues(Map<String, Object> lazyCollectionRawValues) {
        this.lazyCollectionRawValues = lazyCollectionRawValues;
    }

    public boolean hasProxyDefinitions() {
        return true;
    }
    @Override
    public String toStringName() {
        return null;
    }

    public String toString() {
        return null;
    }
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof BaseEntity))
            return false;
        if (getId() == null)
            return false;
        return getId().equals(((BaseEntity) o).getId());
    }
    public static Map<Class,String> getClassAndTableName()
    {
        Map<Class,String> _map = new HashMap<Class,String>();
        _map.put(BaseEntity.class,"BASE_ENTITY");
        return _map;
    }

    public Long getId() {
        return null;
    }

    public void setId(Long id_) {}

    public Long getOrgId() {
        return null;
    }

    public void setOrgId(Long orgId) {

    }

    public Long getMid() {
     return null;
    }

    public void setMid(Long mid) {

    }

    public Long getMustid() {
        return null;
    }

    public void setMustid(Long mustid) {
    }

    public Integer getGonderildi() {return null;}

    public void setGonderildi(Integer gonderildi) {}
    public Long getOBJECTID() {
        return null;
    }
}
