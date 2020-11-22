package EntityLayer.GeoPortal;

import AnnotationLayer.Column;
import AnnotationLayer.Table;
import EntityLayer.Ortak.BaseEntity;

/*
     {
    "attributes" : {
     "SIRA_NO": 15,
      "KOKENI_OLUSUMU" :2,
      "CAP" : 20,
      "ALAN_KARNE_ID":699,
      "AGAC_TUR_KOD":"Kç"
    }
  }
]
*/
/**
 * Created by Ömer YILDIRIM on 18.4.2016.
 */
@Table(name = "ORNEK_ALAN")
public class ORNEK_ALAN extends BaseEntity {

    @Override
    public String toStringName() {
        return String.valueOf(getNOKTA_NUMARASI());
    }



    @Column(name = "id", nullable = false)
    private Long	id	= null;

    ////Mobile Fields start
    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;

    @Column(name = "orgId")
    private Long orgId;

    @Column(name = "gonderildi")
    private Integer gonderildi;

    @Column(name = "enabled", nullable = false)
    private Integer enabled=0;



    @Column(name = "toplu_senkron_icin_secili", nullable = false)
    private Integer toplu_senkron_icin_secili=0;
    /////////////////////////////

    @Column(name = "PLANNAME", nullable = true)
    private String PLANNAME;
    //// ARCGIS TABLE ELEMENTS
    //// ARCGIS TABLE ELEMENTS
    //// ARCGIS TABLE ELEMENTS
    @Column(name = "OBJECTID", nullable = true)
    private Long OBJECTID;

    @Column(name = "PLAN_HAZIRLIK_ID", nullable = true)
    private Integer PLAN_HAZIRLIK_ID;

    @Column(name = "NOKTA_NUMARASI", nullable = true)
    private Integer NOKTA_NUMARASI;

    @Column(name = "ALAN_BUYUKLUGU", nullable = true)
    private Double ALAN_BUYUKLUGU;

    @Column(name = "ADAY_MESCERE_TIPI_ID", nullable = true)
    private Integer ADAY_MESCERE_TIPI_ID;

    @Column(name = "PAFTA_NO", nullable = true)
    private String PAFTA_NO;

    @Column(name = "ORNEKLEME_ALAN_TIPI", nullable = true)
    private Integer ORNEKLEME_ALAN_TIPI;

    @Column(name = "ENLEM_X", nullable = true)
    private String ENLEM_X;

    @Column(name = "BOYLAM_Y", nullable = true)
    private String BOYLAM_Y;

    @Column(name = "RAKIM_Z", nullable = true)
    private String RAKIM_Z;

    @Column(name = "ORMAN_FORMU", nullable = true)
    private String ORMAN_FORMU;

    @Column(name = "ORTUS_BOLLUK_CALI_KATI", nullable = true)
    private String ORTUS_BOLLUK_CALI_KATI;

    @Column(name = "CALI_KATI_TUR_SAYISI", nullable = true)
    private String CALI_KATI_TUR_SAYISI;

    @Column(name = "ORTUS_BOLLUK_OT_KATI", nullable = true)
    private String ORTUS_BOLLUK_OT_KATI;

    @Column(name = "OT_KATI_TUR_SAYISI", nullable = true)
    private Integer OT_KATI_TUR_SAYISI;

    @Column(name = "MUDAHALE_DURUMU", nullable = true)
    private String MUDAHALE_DURUMU;

    @Column(name = "GECLIK_DURUMU_1", nullable = true)
    private Integer GECLIK_DURUMU_1;

    @Column(name = "YASAM_GUCU_1", nullable = true)
    private Integer YASAM_GUCU_1;

    @Column(name = "GENCLIK_DURUMU_2", nullable = true)
    private Integer GENCLIK_DURUMU_2;

    @Column(name = "YASAM_GUCU_2", nullable = true)
    private Integer YASAM_GUCU_2;

    @Column(name = "MESCERE_TUR_KARISIMI", nullable = true)
    private String MESCERE_TUR_KARISIMI;

    @Column(name = "MESCERE_KAPALILIK", nullable = true)
    private String MESCERE_KAPALILIK;

    @Column(name = "MESCERE_TABAKALILIK", nullable = true)
    private String MESCERE_TABAKALILIK;

    @Column(name = "MESCERE_OLUSUMU", nullable = true)
    private Integer MESCERE_OLUSUMU;

    @Column(name = "BEKLENEN_ORMAN_FONKSIYONU", nullable = true)
    private String BEKLENEN_ORMAN_FONKSIYONU;

    @Column(name = "OZELLIKLI_YER", nullable = true)
    private String OZELLIKLI_YER;

    @Column(name = "YUK_KORU_DEG_TAS_ORMAN", nullable = true)
    private String YUK_KORU_DEG_TAS_ORMAN;

    @Column(name = "SILVIKULTUR_MUDAHALE_ONCELIK", nullable = true)
    private Integer SILVIKULTUR_MUDAHALE_ONCELIK;

    @Column(name = "SILVI_KULTUR_MUDAHALE_SEKLI", nullable = true)
    private String SILVI_KULTUR_MUDAHALE_SEKLI;

    @Column(name = "ODUN_DISI_ORMAN_URUN_ID", nullable = true)
    private String ODUN_DISI_ORMAN_URUN_ID;

    @Column(name = "SUKSESYON_ASAMASI", nullable = true)
    private Integer SUKSESYON_ASAMASI;

    @Column(name = "YABANI_HAYVAN_GOZLEM", nullable = true)
    private String  YABANI_HAYVAN_GOZLEM;

    @Column(name = "YABANI_HAYVAN_TEHDIT_FAKTORU", nullable = true)
    private String YABANI_HAYVAN_TEHDIT_FAKTORU;

    @Column(name = "ARAZI_KULLANIMI", nullable = true)
    private String ARAZI_KULLANIMI;

    @Column(name = "MULKIYET", nullable = true)
    private String MULKIYET;

    @Column(name = "TUR_OTLATMA_DERECESI", nullable = true)
    private String TUR_OTLATMA_DERECESI;

    @Column(name = "DIGER_GOZLEM", nullable = true)
    private String DIGER_GOZLEM;

    @Column(name = "TAKSATOR", nullable = true)
    private String TAKSATOR;

    @Column(name = "OLCME_TARIH", nullable = true)
    private String OLCME_TARIH;

    @Column(name = "OLU_AGAC_NEDENI_1", nullable = true)
    private String OLU_AGAC_NEDENI_1;

    @Column(name = "OLU_AGAC_NEDENI_2", nullable = true)
    private String OLU_AGAC_NEDENI_2;

    @Column(name = "OLU_AGAC_ADET_1", nullable = true)
    private Integer OLU_AGAC_ADET_1;

    @Column(name = "OLU_AGAC_ADET_2", nullable = true)
    private Integer OLU_AGAC_ADET_2;

    @Column(name = "ORTALAMA_BOY_1", nullable = true)
    private Double ORTALAMA_BOY_1;

    @Column(name = "ORTALAMA_BOY_2", nullable = true)
    private Double ORTALAMA_BOY_2;

    @Column(name = "ORTALAMA_CAP_1", nullable = true)
    private Integer ORTALAMA_CAP_1;

    @Column(name = "ORTALAMA_CAP_2", nullable = true)
    private Integer ORTALAMA_CAP_2;

    @Column(name = "GOZLENEN_YABANI_HAYVAN", nullable = true)
    private String  GOZLENEN_YABANI_HAYVAN;

    @Column(name = "GOZLENEN_HAYVAN_ADET", nullable = true)
    private Integer GOZLENEN_HAYVAN_ADET;

    @Column(name = "GLOBAL_NOKTA_ID", nullable = true)
    private String GLOBAL_NOKTA_ID;

    @Column(name = "DURUM", nullable = true)
    private String DURUM;

    @Column(name = "ACIKLAMA", nullable = true)
    private String ACIKLAMA;

    @Column(name = "EKIP_ID", nullable = true)
    private Integer EKIP_ID;

    @Column(name = "GUNLEME_ZAMANI", nullable = true)
    private String GUNLEME_ZAMANI;

    @Column(name = "GUNLEYEN_ID", nullable = true)
    private Integer GUNLEYEN_ID;

    @Column(name = "GLOBALID", nullable = true)
    private String GLOBALID;

    @Column(name = "BOLMECIKID", nullable = true)
    private String  BOLMECIKID;

    @Column(name = "PLANID", nullable = true)
    private String PLANID;

    @Column(name = "KOKENI", nullable = true)
    private String KOKENI;

    @Column(name = "MESCERE_TIPI", nullable = true)
    private String MESCERE_TIPI;

    @Column(name = "OLCULEN_X", nullable = true)
    private Double OLCULEN_X;

    @Column(name = "OLCULEN_Y", nullable = true)
    private Double OLCULEN_Y;

    @Column(name = "CREATED_USER", nullable = true)
    private String CREATED_USER ;

    @Column(name = "CREATED_DATE", nullable = true)
    private String CREATED_DATE ;

    @Column(name = "LAST_EDITED_USER", nullable = true)
    private String LAST_EDITED_USER ;

    @Column(name = "LAST_EDITED_DATE", nullable = true)
    private String LAST_EDITED_DATE ;

    @Column(name = "GIRILEN_AGAC_SAYISI", nullable = true)
    private Integer GIRILEN_AGAC_SAYISI  ;
    @Column(name = "MANUEL_MI_EKLENDI", nullable = true)
    private Integer MANUEL_MI_EKLENDI;
    @Column(name = "BASMUHENDIS_MESTIP", nullable = true)
    private String BASMUHENDIS_MESTIP;

    @Column(name = "UTM_X", nullable = true)
    private Double UTM_X ;
    @Column(name = "UTM_Y", nullable = true)
    private Double UTM_Y ;

    @Column(name = "UTM_ZONE", nullable = true)
    private Double UTM_ZONE;
    public Long getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(Long OBJECTID) {
        this.OBJECTID = OBJECTID;
    }
    public Integer getPLAN_HAZIRLIK_ID() {
        return PLAN_HAZIRLIK_ID;
    }

    public void setPLAN_HAZIRLIK_ID(Integer PLAN_HAZIRLIK_ID) {
        this.PLAN_HAZIRLIK_ID = PLAN_HAZIRLIK_ID;
    }

    public Integer getNOKTA_NUMARASI() {
        return NOKTA_NUMARASI;
    }

    public void setNOKTA_NUMARASI(Integer NOKTA_NUMARASI) {
        this.NOKTA_NUMARASI = NOKTA_NUMARASI;
    }

    public Double getALAN_BUYUKLUGU() {
        return ALAN_BUYUKLUGU;
    }

    public void setALAN_BUYUKLUGU(Double ALAN_BUYUKLUGU) {
        this.ALAN_BUYUKLUGU = ALAN_BUYUKLUGU;
    }

    public Integer getADAY_MESCERE_TIPI_ID() {
        return ADAY_MESCERE_TIPI_ID;
    }

    public void setADAY_MESCERE_TIPI_ID(Integer ADAY_MESCERE_TIPI_ID) {
        this.ADAY_MESCERE_TIPI_ID = ADAY_MESCERE_TIPI_ID;
    }

    public String getPAFTA_NO() {
        return PAFTA_NO;
    }

    public void setPAFTA_NO(String PAFTA_NO) {
        this.PAFTA_NO = PAFTA_NO;
    }

    public Integer getORNEKLEME_ALAN_TIPI() {
        return ORNEKLEME_ALAN_TIPI;
    }

    public void setORNEKLEME_ALAN_TIPI(Integer ORNEKLEME_ALAN_TIPI) {
        this.ORNEKLEME_ALAN_TIPI = ORNEKLEME_ALAN_TIPI;
    }

    public String getENLEM_X() {
        return ENLEM_X;
    }

    public void setENLEM_X(String ENLEM_X) {
        this.ENLEM_X = ENLEM_X;
    }

    public String getBOYLAM_Y() {
        return BOYLAM_Y;
    }

    public void setBOYLAM_Y(String BOYLAM_Y) {
        this.BOYLAM_Y = BOYLAM_Y;
    }

    public String getRAKIM_Z() {
        return RAKIM_Z;
    }

    public void setRAKIM_Z(String RAKIM_Z) {
        this.RAKIM_Z = RAKIM_Z;
    }

    public String getORMAN_FORMU() {
        return ORMAN_FORMU;
    }

    public void setORMAN_FORMU(String ORMAN_FORMU) {
        this.ORMAN_FORMU = ORMAN_FORMU;
    }

    public String getORTUS_BOLLUK_CALI_KATI() {
        return ORTUS_BOLLUK_CALI_KATI;
    }

    public void setORTUS_BOLLUK_CALI_KATI(String ORTUS_BOLLUK_CALI_KATI) {
        this.ORTUS_BOLLUK_CALI_KATI = ORTUS_BOLLUK_CALI_KATI;
    }

    public String getCALI_KATI_TUR_SAYISI() {
        return CALI_KATI_TUR_SAYISI;
    }

    public void setCALI_KATI_TUR_SAYISI(String CALI_KATI_TUR_SAYISI) {
        this.CALI_KATI_TUR_SAYISI = CALI_KATI_TUR_SAYISI;
    }

    public String getORTUS_BOLLUK_OT_KATI() {
        return ORTUS_BOLLUK_OT_KATI;
    }

    public void setORTUS_BOLLUK_OT_KATI(String ORTUS_BOLLUK_OT_KATI) {
        this.ORTUS_BOLLUK_OT_KATI = ORTUS_BOLLUK_OT_KATI;
    }

    public Integer getOT_KATI_TUR_SAYISI() {
        return OT_KATI_TUR_SAYISI;
    }

    public void setOT_KATI_TUR_SAYISI(Integer OT_KATI_TUR_SAYISI) {
        this.OT_KATI_TUR_SAYISI = OT_KATI_TUR_SAYISI;
    }

    public String getMUDAHALE_DURUMU() {
        return MUDAHALE_DURUMU;
    }

    public void setMUDAHALE_DURUMU(String MUDAHALE_DURUMU) {
        this.MUDAHALE_DURUMU = MUDAHALE_DURUMU;
    }

    public Integer getGECLIK_DURUMU_1() {
        return GECLIK_DURUMU_1;
    }

    public void setGECLIK_DURUMU_1(Integer GECLIK_DURUMU_1) {
        this.GECLIK_DURUMU_1 = GECLIK_DURUMU_1;
    }

    public Integer getYASAM_GUCU_1() {
        return YASAM_GUCU_1;
    }

    public void setYASAM_GUCU_1(Integer YASAM_GUCU_1) {
        this.YASAM_GUCU_1 = YASAM_GUCU_1;
    }

    public Integer getGENCLIK_DURUMU_2() {
        return GENCLIK_DURUMU_2;
    }

    public void setGENCLIK_DURUMU_2(Integer GENCLIK_DURUMU_2) {
        this.GENCLIK_DURUMU_2 = GENCLIK_DURUMU_2;
    }

    public Integer getYASAM_GUCU_2() {
        return YASAM_GUCU_2;
    }

    public void setYASAM_GUCU_2(Integer YASAM_GUCU_2) {
        this.YASAM_GUCU_2 = YASAM_GUCU_2;
    }

    public String getMESCERE_TUR_KARISIMI() {
        return MESCERE_TUR_KARISIMI;
    }

    public void setMESCERE_TUR_KARISIMI(String MESCERE_TUR_KARISIMI) {
        this.MESCERE_TUR_KARISIMI = MESCERE_TUR_KARISIMI;
    }

    public String getMESCERE_KAPALILIK() {
        return MESCERE_KAPALILIK;
    }

    public void setMESCERE_KAPALILIK(String MESCERE_KAPALILIK) {
        this.MESCERE_KAPALILIK = MESCERE_KAPALILIK;
    }

    public String getMESCERE_TABAKALILIK() {
        return MESCERE_TABAKALILIK;
    }

    public void setMESCERE_TABAKALILIK(String MESCERE_TABAKALILIK) {
        this.MESCERE_TABAKALILIK = MESCERE_TABAKALILIK;
    }

    public Integer getMESCERE_OLUSUMU() {
        return MESCERE_OLUSUMU;
    }

    public void setMESCERE_OLUSUMU(Integer MESCERE_OLUSUMU) {
        this.MESCERE_OLUSUMU = MESCERE_OLUSUMU;
    }

    public String getBEKLENEN_ORMAN_FONKSIYONU() {
        return BEKLENEN_ORMAN_FONKSIYONU;
    }

    public void setBEKLENEN_ORMAN_FONKSIYONU(String BEKLENEN_ORMAN_FONKSIYONU) {
        this.BEKLENEN_ORMAN_FONKSIYONU = BEKLENEN_ORMAN_FONKSIYONU;
    }

    public String getOZELLIKLI_YER() {
        return OZELLIKLI_YER;
    }

    public void setOZELLIKLI_YER(String OZELLIKLI_YER) {
        this.OZELLIKLI_YER = OZELLIKLI_YER;
    }

    public String getYUK_KORU_DEG_TAS_ORMAN() {
        return YUK_KORU_DEG_TAS_ORMAN;
    }

    public void setYUK_KORU_DEG_TAS_ORMAN(String YUK_KORU_DEG_TAS_ORMAN) {
        this.YUK_KORU_DEG_TAS_ORMAN = YUK_KORU_DEG_TAS_ORMAN;
    }

    public Integer getSILVIKULTUR_MUDAHALE_ONCELIK() {
        return SILVIKULTUR_MUDAHALE_ONCELIK;
    }

    public void setSILVIKULTUR_MUDAHALE_ONCELIK(Integer SILVIKULTUR_MUDAHALE_ONCELIK) {
        this.SILVIKULTUR_MUDAHALE_ONCELIK = SILVIKULTUR_MUDAHALE_ONCELIK;
    }

    public String getSILVI_KULTUR_MUDAHALE_SEKLI() {
        return SILVI_KULTUR_MUDAHALE_SEKLI;
    }

    public void setSILVI_KULTUR_MUDAHALE_SEKLI(String SILVI_KULTUR_MUDAHALE_SEKLI) {
        this.SILVI_KULTUR_MUDAHALE_SEKLI = SILVI_KULTUR_MUDAHALE_SEKLI;
    }

    public String getODUN_DISI_ORMAN_URUN_ID() {
        return ODUN_DISI_ORMAN_URUN_ID;
    }

    public void setODUN_DISI_ORMAN_URUN_ID(String ODUN_DISI_ORMAN_URUN_ID) {
        this.ODUN_DISI_ORMAN_URUN_ID = ODUN_DISI_ORMAN_URUN_ID;
    }

    public Integer getSUKSESYON_ASAMASI() {
        return SUKSESYON_ASAMASI;
    }

    public void setSUKSESYON_ASAMASI(Integer SUKSESYON_ASAMASI) {
        this.SUKSESYON_ASAMASI = SUKSESYON_ASAMASI;
    }

    public String getYABANI_HAYVAN_GOZLEM() {
        return YABANI_HAYVAN_GOZLEM;
    }

    public void setYABANI_HAYVAN_GOZLEM(String YABANI_HAYVAN_GOZLEM) {
        this.YABANI_HAYVAN_GOZLEM = YABANI_HAYVAN_GOZLEM;
    }

    public String getYABANI_HAYVAN_TEHDIT_FAKTORU() {
        return YABANI_HAYVAN_TEHDIT_FAKTORU;
    }

    public void setYABANI_HAYVAN_TEHDIT_FAKTORU(String YABANI_HAYVAN_TEHDIT_FAKTORU) {
        this.YABANI_HAYVAN_TEHDIT_FAKTORU = YABANI_HAYVAN_TEHDIT_FAKTORU;
    }

    public String getARAZI_KULLANIMI() {
        return ARAZI_KULLANIMI;
    }

    public void setARAZI_KULLANIMI(String ARAZI_KULLANIMI) {
        this.ARAZI_KULLANIMI = ARAZI_KULLANIMI;
    }

    public String getMULKIYET() {
        return MULKIYET;
    }

    public void setMULKIYET(String MULKIYET) {
        this.MULKIYET = MULKIYET;
    }

    public String getTUR_OTLATMA_DERECESI() {
        return TUR_OTLATMA_DERECESI;
    }

    public void setTUR_OTLATMA_DERECESI(String TUR_OTLATMA_DERECESI) {
        this.TUR_OTLATMA_DERECESI = TUR_OTLATMA_DERECESI;
    }

    public String getDIGER_GOZLEM() {
        return DIGER_GOZLEM;
    }

    public void setDIGER_GOZLEM(String DIGER_GOZLEM) {
        this.DIGER_GOZLEM = DIGER_GOZLEM;
    }

    public String getTAKSATOR() {
        return TAKSATOR;
    }

    public void setTAKSATOR(String TAKSATOR) {
        this.TAKSATOR = TAKSATOR;
    }

    public String getOLCME_TARIH() {
        return OLCME_TARIH;
    }

    public void setOLCME_TARIH(String OLCME_TARIH) {
        this.OLCME_TARIH = OLCME_TARIH;
    }

    public String getOLU_AGAC_NEDENI_1() {
        return OLU_AGAC_NEDENI_1;
    }

    public void setOLU_AGAC_NEDENI_1(String OLU_AGAC_NEDENI_1) {
        this.OLU_AGAC_NEDENI_1 = OLU_AGAC_NEDENI_1;
    }

    public String getOLU_AGAC_NEDENI_2() {
        return OLU_AGAC_NEDENI_2;
    }

    public void setOLU_AGAC_NEDENI_2(String OLU_AGAC_NEDENI_2) {
        this.OLU_AGAC_NEDENI_2 = OLU_AGAC_NEDENI_2;
    }

    public Integer getOLU_AGAC_ADET_1() {
        return OLU_AGAC_ADET_1;
    }

    public void setOLU_AGAC_ADET_1(Integer OLU_AGAC_ADET_1) {
        this.OLU_AGAC_ADET_1 = OLU_AGAC_ADET_1;
    }

    public Integer getOLU_AGAC_ADET_2() {
        return OLU_AGAC_ADET_2;
    }

    public void setOLU_AGAC_ADET_2(Integer OLU_AGAC_ADET_2) {
        this.OLU_AGAC_ADET_2 = OLU_AGAC_ADET_2;
    }

    public Double getORTALAMA_BOY_1() {
        return ORTALAMA_BOY_1;
    }

    public void setORTALAMA_BOY_1(Double ORTALAMA_BOY_1) {
        this.ORTALAMA_BOY_1 = ORTALAMA_BOY_1;
    }

    public Double getORTALAMA_BOY_2() {
        return ORTALAMA_BOY_2;
    }

    public void setORTALAMA_BOY_2(Double ORTALAMA_BOY_2) {
        this.ORTALAMA_BOY_2 = ORTALAMA_BOY_2;
    }

    public Integer getORTALAMA_CAP_1() {
        return ORTALAMA_CAP_1;
    }

    public void setORTALAMA_CAP_1(Integer ORTALAMA_CAP_1) {
        this.ORTALAMA_CAP_1 = ORTALAMA_CAP_1;
    }

    public Integer getORTALAMA_CAP_2() {
        return ORTALAMA_CAP_2;
    }

    public void setORTALAMA_CAP_2(Integer ORTALAMA_CAP_2) {
        this.ORTALAMA_CAP_2 = ORTALAMA_CAP_2;
    }

    public String getGOZLENEN_YABANI_HAYVAN() {
        return GOZLENEN_YABANI_HAYVAN;
    }

    public void setGOZLENEN_YABANI_HAYVAN(String GOZLENEN_YABANI_HAYVAN) {
        this.GOZLENEN_YABANI_HAYVAN = GOZLENEN_YABANI_HAYVAN;
    }

    public Integer getGOZLENEN_HAYVAN_ADET() {
        return GOZLENEN_HAYVAN_ADET;
    }

    public void setGOZLENEN_HAYVAN_ADET(Integer GOZLENEN_HAYVAN_ADET) {
        this.GOZLENEN_HAYVAN_ADET = GOZLENEN_HAYVAN_ADET;
    }

    public String getGLOBAL_NOKTA_ID() {
        return GLOBAL_NOKTA_ID;
    }

    public void setGLOBAL_NOKTA_ID(String GLOBAL_NOKTA_ID) {
        this.GLOBAL_NOKTA_ID = GLOBAL_NOKTA_ID;
    }

    public String getDURUM() {
        return DURUM;
    }

    public void setDURUM(String DURUM) {
        this.DURUM = DURUM;
    }

    public String getACIKLAMA() {
        return ACIKLAMA;
    }

    public void setACIKLAMA(String ACIKLAMA) {
        this.ACIKLAMA = ACIKLAMA;
    }

    public Integer getEKIP_ID() {
        return EKIP_ID;
    }

    public void setEKIP_ID(Integer EKIP_ID) {
        this.EKIP_ID = EKIP_ID;
    }

    public String getGUNLEME_ZAMANI() {
        return GUNLEME_ZAMANI;
    }

    public void setGUNLEME_ZAMANI(String GUNLEME_ZAMANI) {
        this.GUNLEME_ZAMANI = GUNLEME_ZAMANI;
    }

    public Integer getGUNLEYEN_ID() {
        return GUNLEYEN_ID;
    }

    public void setGUNLEYEN_ID(Integer GUNLEYEN_ID) {
        this.GUNLEYEN_ID = GUNLEYEN_ID;
    }

    public String getGLOBALID() {
        return GLOBALID;
    }

    public void setGLOBALID(String GLOBALID) {
        this.GLOBALID = GLOBALID;
    }

    public String getBOLMECIKID() {
        return BOLMECIKID;
    }

    public void setBOLMECIKID(String BOLMECIKID) {
        this.BOLMECIKID = BOLMECIKID;
    }

    public String getPLANID() {
        return PLANID;
    }

    public void setPLANID(String PLANID) {
        this.PLANID = PLANID;
    }

    public String getKOKENI() {
        return KOKENI;
    }

    public void setKOKENI(String KOKENI) {
        this.KOKENI = KOKENI;
    }

    public String getMESCERE_TIPI() {
        return MESCERE_TIPI;
    }

    public void setMESCERE_TIPI(String MESCERE_TIPI) {
        this.MESCERE_TIPI = MESCERE_TIPI;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public Double getOLCULEN_X() {
        return OLCULEN_X;
    }

    public void setOLCULEN_X(Double OLCULEN_X) {
        this.OLCULEN_X = OLCULEN_X;
    }

    public Double getOLCULEN_Y() {
        return OLCULEN_Y;
    }

    public void setOLCULEN_Y(Double OLCULEN_Y) {
        this.OLCULEN_Y = OLCULEN_Y;
    }

    public String getPLANNAME() {
        return PLANNAME;
    }

    public void setPLANNAME(String PLANNAME) {
        this.PLANNAME = PLANNAME;
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

    public Integer getGIRILEN_AGAC_SAYISI() {
        return GIRILEN_AGAC_SAYISI;
    }

    public void setGIRILEN_AGAC_SAYISI(Integer GIRILEN_AGAC_SAYISI) {
        this.GIRILEN_AGAC_SAYISI = GIRILEN_AGAC_SAYISI;
    }

    public Integer getMANUEL_MI_EKLENDI() {
        return MANUEL_MI_EKLENDI;
    }

    public void setMANUEL_MI_EKLENDI(Integer MANUEL_MI_EKLENDI) {
        this.MANUEL_MI_EKLENDI = MANUEL_MI_EKLENDI;
    }

    public String getBASMUHENDIS_MESTIP() {
        return BASMUHENDIS_MESTIP;
    }

    public void setBASMUHENDIS_MESTIP(String BASMUHENDIS_MESTIP) {
        this.BASMUHENDIS_MESTIP = BASMUHENDIS_MESTIP;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Double getUTM_X() {
        return UTM_X;
    }

    public void setUTM_X(Double UTM_X) {
        this.UTM_X = UTM_X;
    }

    public Double getUTM_Y() {
        return UTM_Y;
    }

    public void setUTM_Y(Double UTM_Y) {
        this.UTM_Y = UTM_Y;
    }

    public Double getUTM_ZONE() {
        return UTM_ZONE;
    }

    public void setUTM_ZONE(Double UTM_ZONE) {
        this.UTM_ZONE = UTM_ZONE;
    }


    public Integer getToplu_senkron_icin_secili() {
        return toplu_senkron_icin_secili;
    }

    public void setToplu_senkron_icin_secili(Integer toplu_senkron_icin_secili) {
        this.toplu_senkron_icin_secili = toplu_senkron_icin_secili;
    }

    ////Mobile Fields End
}
