package EntityLayer.GeoPortal;

import java.util.Date;

import AnnotationLayer.Column;
import AnnotationLayer.Table;
import EntityLayer.Ortak.BaseEntity;

/**
 * Created by isahin on 27.7.2016.
 */

@Table(name = "AGAC_HACIM_ARTIM_TABLOSU")
public class AGAC_HACIM_ARTIM_TABLOSU extends BaseEntity {


    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "tur", nullable = false)
    private Integer tur;
    @Column(name = "seflikId", nullable = false)
    private Long seflikId;
    @Column(name = "agacTuruId", nullable = false)
    private Long agacTuruId;
    @Column(name = "ortaCap", nullable = false)
    private Double ortaCap;
    @Column(name = "hacim", nullable = false)
    private Double hacim;
    @Column(name = "artim", nullable = false)
    private Double artim;
    @Column(name = "gpPlanCizelgeId", nullable = true)
    private Long gpPlanCizelgeId;
    @Column(name = "gpPlanHazirlikId", nullable = true)
    private Long gpPlanHazirlikId;
    @Column(name = "aktif", nullable = true)
    private Boolean aktif;
    @Column(name = "gunlemeZamani", nullable = false)
    private Date gunlemeZamani;
    @Column(name = "gunleyenId", nullable = false)
    private Long gunleyenId;



    @Column(name = "capKademesiAlt", nullable = false)
    private Double capKademesiAlt;
    @Column(name = "capKademesiUst", nullable = false)
    private Double capKademesiUst;

    private String capSinifi;
    private Double  bir_hektar_agac_sayisi_tmp;
    private Double bir_hektar_hacim_1_tmp;
    private Double bir_hektar_artim_1_tmp;



    private Double b_hacim_column_data = 0.0;
    private Double c_hacim_column_data = 0.0;
    private Double d_hacim_column_data = 0.0;
    private Double e_hacim_column_data = 0.0;
    private Integer b_sayi_column_data = 0;
    private Integer c_sayi_column_data = 0;
    private Integer d_sayi_column_data = 0;
    private Integer e_sayi_column_data = 0;
    private Double toplam_hacim_column_data = 0.0;
    private Integer toplam_sayi_column_data = 0;



    private String agacTurKod;//gerektiğinde ağaç adı olsun


    @Column(name = "mid")
    private Long mid;
    @Column(name = "mustid")
    private Long mustid;
    @Column(name = "orgId")
    private Long orgId;
    @Column(name = "gonderildi")
    private Integer gonderildi=0;

    @Column(name = "enabled", nullable = false)
    private Integer enabled=0;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTur() {
        return tur;
    }

    public void setTur(Integer tur) {
        this.tur = tur;
    }

    public Long getSeflik_id() {
        return seflikId;
    }

    public void setSeflik_id(Long seflik_id) {
        this.seflikId = seflik_id;
    }

    public Long getAgacTuruId() {
        return agacTuruId;
    }

    public void setAgacTuruId(Long agacTuruId) {
        this.agacTuruId = agacTuruId;
    }

    public Double getOrtaCap() {
        return ortaCap;
    }

    public void setOrtaCap(Double ortaCap) {
        this.ortaCap = ortaCap;
    }

    public Double getHacim() {
        return hacim;
    }

    public void setHacim(Double hacim) {
        this.hacim = hacim;
    }

    public Double getArtim() {
        return artim;
    }

    public void setArtim(Double artim) {
        this.artim = artim;
    }

    public Long getGpPlanCizelgeId() {
        return gpPlanCizelgeId;
    }

    public void setGpPlanCizelgeId(Long gpPlanCizelgeId) {
        this.gpPlanCizelgeId = gpPlanCizelgeId;
    }

    public Long getGpPlanHazirlikId() {
        return gpPlanHazirlikId;
    }

    public void setGpPlanHazirlikId(Long gpPlanHazirlikId) {
        this.gpPlanHazirlikId = gpPlanHazirlikId;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public Date getGunlemeZamani() {
        return gunlemeZamani;
    }

    public void setGunlemeZamani(Date gunlemeZamani) {
        this.gunlemeZamani = gunlemeZamani;
    }

    public Long getGunleyenId() {
        return gunleyenId;
    }

    public void setGunleyenId(Long gunleyenId) {
        this.gunleyenId = gunleyenId;
    }

    @Override
    public Long getMid() {
        return mid;
    }

    @Override
    public void setMid(Long mid) {
        this.mid = mid;
    }

    @Override
    public Long getMustid() {
        return mustid;
    }

    @Override
    public void setMustid(Long mustid) {
        this.mustid = mustid;
    }

    @Override
    public Long getOrgId() {
        return orgId;
    }

    @Override
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public Integer getGonderildi() {
        return gonderildi;
    }

    @Override
    public void setGonderildi(Integer gonderildi) {
        this.gonderildi = gonderildi;
    }


    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }


    public Double getCapKademesiAlt() {
        return capKademesiAlt;
    }

    public void setCapKademesiAlt(Double capKademesiAlt) {
        this.capKademesiAlt = capKademesiAlt;
    }

    public Double getCapKademesiUst() {
        return capKademesiUst;
    }

    public void setCapKademesiUst(Double capKademesiUst) {
        this.capKademesiUst = capKademesiUst;
    }

    public String getCapSinifi() {
        return capSinifi;
    }

    public void setCapSinifi(String capSinifi) {
        this.capSinifi = capSinifi;
    }

    public Double getBir_hektar_agac_sayisi_tmp() {
        return bir_hektar_agac_sayisi_tmp;
    }

    public void setBir_hektar_agac_sayisi_tmp(Double bir_hektar_agac_sayisi_tmp) {
        this.bir_hektar_agac_sayisi_tmp = bir_hektar_agac_sayisi_tmp;
    }

    public Double getBir_hektar_hacim_1_tmp() {
        return bir_hektar_hacim_1_tmp;
    }

    public void setBir_hektar_hacim_1_tmp(Double bir_hektar_hacim_1_tmp) {
        this.bir_hektar_hacim_1_tmp = bir_hektar_hacim_1_tmp;
    }

    public Double getBir_hektar_artim_1_tmp() {
        return bir_hektar_artim_1_tmp;
    }

    public void setBir_hektar_artim_1_tmp(Double bir_hektar_artim_1_tmp) {
        this.bir_hektar_artim_1_tmp = bir_hektar_artim_1_tmp;
    }
    public String getAgacTurKod() {
        return agacTurKod;
    }

    public void setAgacTurKod(String agacTurKod) {
        this.agacTurKod = agacTurKod;
    }


    public Double getB_hacim_column_data() {
        return b_hacim_column_data;
    }

    public void setB_hacim_column_data(Double b_hacim_column_data) {
        this.b_hacim_column_data = b_hacim_column_data;
    }

    public Double getC_hacim_column_data() {
        return c_hacim_column_data;
    }

    public void setC_hacim_column_data(Double c_hacim_column_data) {
        this.c_hacim_column_data = c_hacim_column_data;
    }

    public Double getE_hacim_column_data() {
        return e_hacim_column_data;
    }

    public void setE_hacim_column_data(Double e_hacim_column_data) {
        this.e_hacim_column_data = e_hacim_column_data;
    }

    public Double getD_hacim_column_data() {
        return d_hacim_column_data;
    }

    public void setD_hacim_column_data(Double d_hacim_column_data) {
        this.d_hacim_column_data = d_hacim_column_data;
    }

    public Integer getB_sayi_column_data() {
        return b_sayi_column_data;
    }

    public void setB_sayi_column_data(Integer b_sayi_column_data) {
        this.b_sayi_column_data = b_sayi_column_data;
    }

    public Integer getC_sayi_column_data() {
        return c_sayi_column_data;
    }

    public void setC_sayi_column_data(Integer c_sayi_column_data) {
        this.c_sayi_column_data = c_sayi_column_data;
    }

    public Integer getD_sayi_column_data() {
        return d_sayi_column_data;
    }

    public void setD_sayi_column_data(Integer d_sayi_column_data) {
        this.d_sayi_column_data = d_sayi_column_data;
    }

    public Integer getE_sayi_column_data() {
        return e_sayi_column_data;
    }

    public void setE_sayi_column_data(Integer e_sayi_column_data) {
        this.e_sayi_column_data = e_sayi_column_data;
    }



    public Integer getToplam_sayi_column_data() {
        return toplam_sayi_column_data;
    }

    public void setToplam_sayi_column_data(Integer toplam_sayi_column_data) {
        this.toplam_sayi_column_data = toplam_sayi_column_data;
    }

    public Double getToplam_hacim_column_data() {
        return toplam_hacim_column_data;
    }

    public void setToplam_hacim_column_data(Double toplam_hacim_column_data) {
        this.toplam_hacim_column_data = toplam_hacim_column_data;
    }

}
