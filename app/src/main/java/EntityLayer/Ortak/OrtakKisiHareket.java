package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 28.2.2017.
 */
@Table(name="ORTAK_KISI_HAREKET")
public class OrtakKisiHareket  extends BaseEntity{

    @Column(name="id",nullable= false)
    private Long id=null;

    @Column(name="personelId",nullable= false)
    private Long personelId=null;

    @Column(name="birimId",nullable= false)
    private Long birimId=null;

    @Column(name="adi",nullable= false)
    private String adi=null;


    @Column(name="soyadi",nullable= false)
    private String soyadi=null;

    @Column(name="unvan",nullable= false)
    private String unvan=null;

    @Column(name="sonHareketZamani",nullable= false)
    private String sonHareketZamani=null;

    @Column(name="xkoordinati",nullable= false)
    private String xkoordinati=null;

    @Column(name="ykoordinati",nullable= false)
    private String ykoordinati=null;

    @Column(name="mid",nullable= false)
    private Long mid=null;

    @Column(name="mustid",nullable= false)
    private Long mustid=null;

    @Column(name="lokasyonAktif",nullable= false)
    private Boolean lokasyonAktif=null;


    @Column(name="sonHareketMs",nullable= false)
    private Long sonHareketMs=null;


    public Long getSonHareketMs() {
        return sonHareketMs;
    }

    public void setSonHareketMs(Long sonHareketMs) {
        this.sonHareketMs = sonHareketMs;
    }

    public Boolean getLokasyonAktif() {
        return lokasyonAktif;
    }

    public void setLokasyonAktif(Boolean lokasyonAktif) {
        this.lokasyonAktif = lokasyonAktif;
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
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonelId() {
        return personelId;
    }

    public void setPersonelId(Long personelId) {
        this.personelId = personelId;
    }

    public Long getBirimId() {
        return birimId;
    }

    public void setBirimId(Long birimId) {
        this.birimId = birimId;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getSonHareketZamani() {
        return sonHareketZamani;
    }

    public void setSonHareketZamani(String sonHareketZamani) {
        this.sonHareketZamani = sonHareketZamani;
    }

    public String getXkoordinati() {
        return xkoordinati;
    }

    public void setXkoordinati(String xkoordinati) {
        this.xkoordinati = xkoordinati;
    }

    public String getYkoordinati() {
        return ykoordinati;
    }

    public void setYkoordinati(String ykoordinati) {
        this.ykoordinati = ykoordinati;
    }
}
