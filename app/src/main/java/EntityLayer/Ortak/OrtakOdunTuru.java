package EntityLayer.Ortak;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import AnnotationLayer.Column;
import AnnotationLayer.JoinColumn;
import AnnotationLayer.ManyToOne;
import AnnotationLayer.OneToMany;
import AnnotationLayer.Table;
import AnnotationLayer.Temporal;
import AnnotationLayer.Transient;
import EntityLayer.Sistem.SKullanici;
import EnumsLayer.FetchType;
import EnumsLayer.TemporalType;

/**
 * Created by isahin on 2.2.2016.
 */
@Table(name = "ORTAK_ODUN_TURU")
public class OrtakOdunTuru extends BaseEntity{

    @Override
    public String toStringName() {
        return getAdi();
    }

    @Column(name = "mid")
    private Long mid;

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
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSablonTur() {
        return sablonTur;
    }

    public void setSablonTur(String sablonTur) {
        this.sablonTur = sablonTur;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public Long getOlcuBirimId() {
        return olcuBirimId;
    }

    public void setOlcuBirimId(Long olcuBirimId) {
        this.olcuBirimId = olcuBirimId;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public BigDecimal getMaxBoy() {
        return maxBoy;
    }

    public void setMaxBoy(BigDecimal maxBoy) {
        this.maxBoy = maxBoy;
    }

    public BigDecimal getMinBoy() {
        return minBoy;
    }

    public void setMinBoy(BigDecimal minBoy) {
        this.minBoy = minBoy;
    }

    public BigDecimal getKisaBoyCm() {
        return kisaBoyCm;
    }

    public void setKisaBoyCm(BigDecimal kisaBoyCm) {
        this.kisaBoyCm = kisaBoyCm;
    }

    public BigDecimal getOrtaBoyCm() {
        return ortaBoyCm;
    }

    public void setOrtaBoyCm(BigDecimal ortaBoyCm) {
        this.ortaBoyCm = ortaBoyCm;
    }

    public Integer getVahidiFiyatRaporSira() {
        return vahidiFiyatRaporSira;
    }

    public void setVahidiFiyatRaporSira(Integer vahidiFiyatRaporSira) {
        this.vahidiFiyatRaporSira = vahidiFiyatRaporSira;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getMaktaHesapNo() {
        return maktaHesapNo;
    }

    public void setMaktaHesapNo(String maktaHesapNo) {
        this.maktaHesapNo = maktaHesapNo;
    }

    public String getOrmanIciHesapNo() {
        return ormanIciHesapNo;
    }

    public void setOrmanIciHesapNo(String ormanIciHesapNo) {
        this.ormanIciHesapNo = ormanIciHesapNo;
    }

    public String getOrmanDisiHesapNo() {
        return ormanDisiHesapNo;
    }

    public void setOrmanDisiHesapNo(String ormanDisiHesapNo) {
        this.ormanDisiHesapNo = ormanDisiHesapNo;
    }

    public BigDecimal getTarifeBedeli() {
        return tarifeBedeli;
    }

    public void setTarifeBedeli(BigDecimal tarifeBedeli) {
        this.tarifeBedeli = tarifeBedeli;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @Column(name = "mustid")
    private Long mustid;


    @Column(name = "id", nullable = false)
    private Long	id	= null;

    @Column(name = "sablon_tur", nullable = true)
    private String	sablonTur	= null;

    @Column(name = "adi", nullable = false)
    private String	adi	= null;

    @Column(name = "olcu_birim_id", nullable = true)
    private Long	olcuBirimId	= null;

    @Column(name = "kategori", nullable = false)
    private String	kategori	= null;

    @Column(name = "aktif", nullable = true)
    private Boolean	aktif	= null;

    @Column(name = "max_boy", nullable = false)
    private BigDecimal maxBoy	= null;

    @Column(name = "min_boy", nullable = false)
    private BigDecimal minBoy	= null;

    @Column(name = "kisa_boy_cm", nullable = false)
    private BigDecimal kisaBoyCm	= null;

    @Column(name = "orta_boy_cm", nullable = false)
    private BigDecimal ortaBoyCm	= null;

    @Column(name = "vahidi_fiyat_rapor_sira", nullable = false)
    private Integer	vahidiFiyatRaporSira	= null;

    @Column(name = "aciklama", nullable = false)
    private String	aciklama	= null;

    @Column(name = "makta_hesap_no", nullable = false)
    private String	maktaHesapNo	= null;


    @Column(name = "orman_ici_hesap_no", nullable = false)
    private String	ormanIciHesapNo	= null;


    @Column(name = "orman_disi_hesap_no", nullable = false)
    private String	ormanDisiHesapNo	= null;


    @Column(name = "tarife_bedeli", nullable = false)
    private BigDecimal tarifeBedeli	= null;

    @Column(name = "kod", nullable = false)
    private String	kod	= null;


    @Column(name = "enabled", nullable = false)
    private Integer enabled=0;

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public OrtakOdunTuru() {
    }

    public String toString() {
        return adi;
    }


}
