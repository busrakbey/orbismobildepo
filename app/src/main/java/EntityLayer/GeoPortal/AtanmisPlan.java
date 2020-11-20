package EntityLayer.GeoPortal;

import java.io.Serializable;

/**
 * Created by Ömer YILDIRIM on 11.5.2016.
 */
public class AtanmisPlan implements Serializable {
    Long planId;
    Long planMid;
    String planAdi;
    String haritaVersiyonAdi;



    String birimAdi;

    public AtanmisPlan(Long planId, String planAdi, String birimAdi , Long planMid_,String haritaVersion_) {
        super();
        this.planId = planId;
        this.planAdi = planAdi;
        this.birimAdi = birimAdi;
        this.planMid = planMid_;
        this.haritaVersiyonAdi = haritaVersion_;
    }

    public Long getPlanMid() {
        return planMid;
    }

    public void setPlanMid(Long planMid) {
        this.planMid = planMid;
    }
    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanAdi() {
        return planAdi;
    }

    public void setPlanAdi(String planAdi) {
        this.planAdi = planAdi;
    }

    public String getBirimAdi() {
        return birimAdi;
    }

    public void setBirimAdi(String birimAdi) {
        this.birimAdi = birimAdi;
    }

    public String getHaritaVersion() {
        return haritaVersiyonAdi;
    }

    public void setHaritaVersion(String haritaVersion) {
        this.haritaVersiyonAdi = haritaVersion;
    }
}