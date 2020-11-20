package EntityLayer.GeoPortal;

import AnnotationLayer.Column;
import AnnotationLayer.FeatureAttribute;
import AnnotationLayer.Table;
import EntityLayer.Ortak.BaseEntity;


/**
 * Created by Ömer YILDIRIM on 27.4.2016.
 */
@Table(name = "EN_KAR_AGAC")
public class EN_KAR_AGAC extends BaseEntity
{

    @Override
    public String toStringName() {
        return getAGAC_TUR_KOD();
    }

    @Override
    public String toString() {
        return "[EN_KAR_AGAC]#";
    }

    @Column(name = "active", nullable = true)
    private Integer	active	= 1;

    @Column(name = "id", nullable = true)
    private Long	id	= 0L;

    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;

    @Column(name = "orgId")
    private Long orgId;


    @Column(name = "gonderildi")
    private Integer gonderildi=0;


    @FeatureAttribute
    @Column(name = "OBJECTID", nullable = false)
    private Long OBJECTID;
    @FeatureAttribute
    @Column(name = "OA_KAR_ID", nullable = false)
    private String OA_KAR_ID;
    @FeatureAttribute
    @Column(name = "AGAC_TUR_ID", nullable = false)
    private Long AGAC_TUR_ID;
    @FeatureAttribute
    @Column(name = "SIRA_NO", nullable = false)
    private Integer SIRA_NO;
    @FeatureAttribute
    @Column(name = "CAP", nullable = false)
    private Integer CAP;
    @FeatureAttribute
    @Column(name = "YAS", nullable = false)
    private Integer YAS;
    @FeatureAttribute
    @Column(name = "CIF_KAB_KAL", nullable = false)
    private Integer CIF_KAB_KAL;
    @FeatureAttribute
    @Column(name = "HAKLA_KAL", nullable = false)
    private Integer HAKLA_KAL;
    @FeatureAttribute
    @Column(name = "AGAC_BOYU", nullable = false)
    private Integer AGAC_BOYU;
    @FeatureAttribute
    @Column(name = "HAKIM_AGAC_BOY", nullable = false)
    private Integer HAKIM_AGAC_BOY;
    @FeatureAttribute
    @Column(name = "GLOBALID", nullable = false)
    private String GLOBALID;
    @FeatureAttribute
    @Column(name = "ORNEK_ALAN_ID", nullable = false)
    private String ORNEK_ALAN_ID;
    @FeatureAttribute
    @Column(name = "MUDAHALE_DURUMU", nullable = false)
    private String MUDAHALE_DURUMU;
    @FeatureAttribute
    @Column(name = "KOKENI_OLUSUMU", nullable = false)
    private String KOKENI_OLUSUMU;
    @FeatureAttribute
    @Column(name = "BIR_CM_HALKA_SAYISI", nullable = false)
    private Integer BIR_CM_HALKA_SAYISI;
    @FeatureAttribute
    @Column(name = "KALITE_SINIFI", nullable = false)
    private String KALITE_SINIFI;
    @FeatureAttribute
    @Column(name = "SILVIKULTUR_DURUM", nullable = false)
    private Integer SILVIKULTUR_DURUM;
    @FeatureAttribute
    @Column(name = "SAGLIK_DURUMU", nullable = false)
    private String SAGLIK_DURUMU;
    @FeatureAttribute
    @Column(name = "PLAN_ID", nullable = false)
    private String PLAN_ID;
    @FeatureAttribute
    @Column(name = "AGAC_TUR_KOD", nullable = false)
    private String AGAC_TUR_KOD;
    @FeatureAttribute
    @Column(name = "ALAN_KARNE_ID", nullable = false)
    private Integer ALAN_KARNE_ID;

    @FeatureAttribute
    @Column(name = "CREATED_USER", nullable = false)
    private String CREATED_USER;

    @FeatureAttribute
    @Column(name = "CREATED_DATE", nullable = false)
    private String CREATED_DATE;

    @FeatureAttribute
    @Column(name = "LAST_EDITED_USER", nullable = false)
    private String LAST_EDITED_USER;

    @FeatureAttribute
    @Column(name = "LAST_EDITED_DATE", nullable = false)
    private String LAST_EDITED_DATE;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    //  public Integer getID() {
    //      return ID;
    //  }
//
    //  public void setID(Integer ID) {
    //      this.ID = ID;
    //  }


    @Override
    public Long getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(Long OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getOA_KAR_ID() {
        return OA_KAR_ID;
    }

    public void setOA_KAR_ID(String OA_KAR_ID) {
        this.OA_KAR_ID = OA_KAR_ID;
    }

    public Long getAGAC_TUR_ID() {
        return AGAC_TUR_ID;
    }

    public void setAGAC_TUR_ID(Long AGAC_TUR_ID) {
        this.AGAC_TUR_ID = AGAC_TUR_ID;
    }

    public Integer getSIRA_NO() {
        return SIRA_NO;
    }

    public void setSIRA_NO(Integer SIRA_NO) {
        this.SIRA_NO = SIRA_NO;
    }

    public Integer getCAP() {
        return CAP;
    }

    public void setCAP(Integer CAP) {
        this.CAP = CAP;
    }

    public Integer getYAS() {
        return YAS;
    }

    public void setYAS(Integer YAS) {
        this.YAS = YAS;
    }

    public Integer getCIF_KAB_KAL() {
        return CIF_KAB_KAL;
    }

    public void setCIF_KAB_KAL(Integer CIF_KAB_KAL) {
        this.CIF_KAB_KAL = CIF_KAB_KAL;
    }

    public Integer getHAKLA_KAL() {
        return HAKLA_KAL;
    }

    public void setHAKLA_KAL(Integer HAKLA_KAL) {
        this.HAKLA_KAL = HAKLA_KAL;
    }

    public Integer getAGAC_BOYU() {
        return AGAC_BOYU;
    }

    public void setAGAC_BOYU(Integer AGAC_BOYU) {
        this.AGAC_BOYU = AGAC_BOYU;
    }

    public Integer getHAKIM_AGAC_BOY() {
        return HAKIM_AGAC_BOY;
    }

    public void setHAKIM_AGAC_BOY(Integer HAKIM_AGAC_BOY) {
        this.HAKIM_AGAC_BOY = HAKIM_AGAC_BOY;
    }

    public String getGLOBALID() {
        return GLOBALID;
    }

    public void setGLOBALID(String GLOBALID) {
        this.GLOBALID = GLOBALID;
    }

    public String getORNEK_ALAN_ID() {
        return ORNEK_ALAN_ID;
    }

    public void setORNEK_ALAN_ID(String ORNEK_ALAN_ID) {
        this.ORNEK_ALAN_ID = ORNEK_ALAN_ID;
    }

    public String getMUDAHALE_DURUMU() {
        return MUDAHALE_DURUMU;
    }

    public void setMUDAHALE_DURUMU(String MUDAHALE_DURUMU) {
        this.MUDAHALE_DURUMU = MUDAHALE_DURUMU;
    }

    public String getKOKENI_OLUSUMU() {
        return KOKENI_OLUSUMU;
    }

    public void setKOKENI_OLUSUMU(String KOKENI_OLUSUMU) {
        this.KOKENI_OLUSUMU = KOKENI_OLUSUMU;
    }

    public Integer getBIR_CM_HALKA_SAYISI() {
        return BIR_CM_HALKA_SAYISI;
    }

    public void setBIR_CM_HALKA_SAYISI(Integer BIR_CM_HALKA_SAYISI) {
        this.BIR_CM_HALKA_SAYISI = BIR_CM_HALKA_SAYISI;
    }

    public String getKALITE_SINIFI() {
        return KALITE_SINIFI;
    }

    public void setKALITE_SINIFI(String KALITE_SINIFI) {
        this.KALITE_SINIFI = KALITE_SINIFI;
    }

    public Integer getSILVIKULTUR_DURUM() {
        return SILVIKULTUR_DURUM;
    }

    public void setSILVIKULTUR_DURUM(Integer SILVIKULTUR_DURUM) {
        this.SILVIKULTUR_DURUM = SILVIKULTUR_DURUM;
    }

    public String getSAGLIK_DURUMU() {
        return SAGLIK_DURUMU;
    }

    public void setSAGLIK_DURUMU(String SAGLIK_DURUMU) {
        this.SAGLIK_DURUMU = SAGLIK_DURUMU;
    }

    public String getPLAN_ID() {
        return PLAN_ID;
    }

    public void setPLAN_ID(String PLAN_ID) {
        this.PLAN_ID = PLAN_ID;
    }

    public String getAGAC_TUR_KOD() {
        return AGAC_TUR_KOD;
    }

    public void setAGAC_TUR_KOD(String AGAC_TUR_KOD) {
        this.AGAC_TUR_KOD = AGAC_TUR_KOD;
    }

    public Integer getALAN_KARNE_ID() {
        return ALAN_KARNE_ID;
    }

    public void setALAN_KARNE_ID(Integer ALAN_KARNE_ID) {
        this.ALAN_KARNE_ID = ALAN_KARNE_ID;
    }

    ////Mobile Fields start


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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
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


    public Integer getTemp_nokta_no() {
        return temp_nokta_no;
    }

    public void setTemp_nokta_no(Integer temp_nokta_no) {
        this.temp_nokta_no = temp_nokta_no;
    }

    @Column(name = "temp_nokta_no", nullable = true)
    private Integer temp_nokta_no=0;

    ////Mobile Fields End


}
