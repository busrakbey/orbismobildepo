package EntityLayer.GeoPortal;


import java.io.Serializable;

import AnnotationLayer.Column;
import AnnotationLayer.Table;
import EntityLayer.Ortak.BaseEntity;

/**
 * Created by Konumsal PC11 on 18.4.2016.
 */
@Table(name = "PLAN")
public class PLAN extends BaseEntity
{
    @Column(name = "OBJECTID", nullable = false)
    private Long OBJECTID;
    @Column(name = "PLAN_ADI", nullable = false)
    private String PLAN_ADI;
    @Column(name = "PLAN_YIL", nullable = false)
    private String PLAN_YIL;
    @Column(name = "PLAN_TUR", nullable = false)
    private Integer PLAN_TUR;
    @Column(name = "PLAN_DURUM", nullable = false)
    private Integer PLAN_DURUM;
    @Column(name = "GLOBALID", nullable = false)
    private String GLOBALID;
    @Column(name = "PLANID", nullable = false)
    private Long PLANID;
    @Column(name = "CREATED_USER", nullable = false)
    private String CREATED_USER;
    @Column(name = "CREATED_DATE", nullable = false)
    private String CREATED_DATE;
    @Column(name = "LAST_EDITED_USER", nullable = false)
    private String LAST_EDITED_USER;
    @Column(name = "LAST_EDITED_DATE", nullable = false)
    private String LAST_EDITED_DATE;
    @Column(name = "PLANNAME", nullable = false)
    private String PLANNAME;

    @Column(name = "haritaVersiyonAdi", nullable = false)
    private String haritaVersiyonAdi;


    @Column(name = "planVerisiYuklendi", nullable = false)
    private Integer planVerisiYuklendi;


    public String getHaritaVersiyonAdi() {
        return haritaVersiyonAdi;
    }

    public void setHaritaVersiyonAdi(String haritaVersiyonAdi) {
        this.haritaVersiyonAdi = haritaVersiyonAdi;
    }

    public Integer getPlanVerisiYuklendi() {
        return planVerisiYuklendi;
    }

    public void setPlanVerisiYuklendi(Integer planVerisiYuklendi) {
        this.planVerisiYuklendi = planVerisiYuklendi;
    }

    @Override
    public String toStringName() {
        StringBuilder builder = new StringBuilder();
        if (getPLANNAME() !=null)
        {
            String[] temp = getPLANNAME().split("Harita Versiyon:");
            builder.append(temp[0]);
            builder.append(System.getProperty("line.separator"));
            //builder.append("Harita Versiyon:");
            //builder.append(temp[1]);

           // builder.append(getPLANNAME());
            builder.append(" ");

        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return String.valueOf(PLANID);
    }

    public static String getArcGisLayerID()
    {
        return "3";
    }
    public static String getArcGisLayerName()
    {
        return "PLAN";
    }

    ////Mobile Fields start
    @Column(name = "id")
    private Long id;
    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;

    @Column(name = "orgId")
    private Long orgId;


    @Column(name = "gonderildi")
    private Integer gonderildi=0;

    @Override
    public Long getMid() {return mid;}

    @Override
    public void setMid(Long mid) {this.mid = mid;}

    @Override
    public Long getMustid() {return mustid;}

    @Override
    public void setMustid(Long mustid) {this.mustid = mustid;}

    @Override
    public Long getOrgId() {return orgId;}

    @Override
    public void setOrgId(Long orgId) {this.orgId = orgId;}

    @Override
    public Integer getGonderildi() {return gonderildi;}

    @Override
    public void setGonderildi(Integer gonderildi) {this.gonderildi = gonderildi;}

    @Override
    public Long getId() {return id;}

    @Override
    public void setId(Long id) {this.id = id;}

    public Long getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(Long OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getPLAN_ADI() {
        return PLAN_ADI;
    }

    public void setPLAN_ADI(String PLAN_ADI) {
        this.PLAN_ADI = PLAN_ADI;
    }

    public String getPLAN_YIL() {
        return PLAN_YIL;
    }

    public void setPLAN_YIL(String PLAN_YIL) {
        this.PLAN_YIL = PLAN_YIL;
    }

    public Integer getPLAN_TUR() {
        return PLAN_TUR;
    }

    public void setPLAN_TUR(Integer PLAN_TUR) {
        this.PLAN_TUR = PLAN_TUR;
    }

    public Integer getPLAN_DURUM() {
        return PLAN_DURUM;
    }

    public void setPLAN_DURUM(Integer PLAN_DURUM) {
        this.PLAN_DURUM = PLAN_DURUM;
    }

    public String getGLOBALID() {
        return GLOBALID;
    }

    public void setGLOBALID(String GLOBALID) {
        this.GLOBALID = GLOBALID;
    }

    public Long getPLANID() {
        return PLANID;
    }

    public void setPLANID(Long PLANID) {
        this.PLANID = PLANID;
    }

    public String getCREATED_USER() {
        return CREATED_USER;
    }

    public void setCREATED_USER(String CREATED_USER) {
        this.CREATED_USER = CREATED_USER;
    }

    public String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(String CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }

    public String getLAST_EDITED_USER() {
        return LAST_EDITED_USER;
    }

    public void setLAST_EDITED_USER(String LAST_EDITED_USER) {
        this.LAST_EDITED_USER = LAST_EDITED_USER;
    }

    public String getLAST_EDITED_DATE() {
        return LAST_EDITED_DATE;
    }

    public void setLAST_EDITED_DATE(String LAST_EDITED_DATE) {
        this.LAST_EDITED_DATE = LAST_EDITED_DATE;
    }

    public String getPLANNAME() {
        return PLANNAME;
    }

    public void setPLANNAME(String PLANNAME) {
        this.PLANNAME = PLANNAME;
    }


    ////Mobile Fields End
}
