package EntityLayer.Ortak;

import java.util.Date;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 20.10.2016.
 */

@Table(name="ORTAK_KISI")
public class OrtakKisi extends BaseEntity{


    @Override
    public String toString()
    {
        return adi +" "+soyadi;
    }

    @Column(name="id",nullable= false)
    private Long id=null;

    @Column(name="dogumYeri",nullable= false)
    private Long dogumYeri=null;

    @Column(name="uyrugu",nullable= false)
    private Long uyrugu=null;

    @Column(name="kisiTur",nullable= false)
    private Integer kisiTur=null;


    @Column(name="adi",nullable= false)
    private String adi=null;

    @Column(name="soyadi",nullable= false)
    private String soyadi=null;


    @Column(name="kimlikNumarasi",nullable= false)
    private String kimlikNumarasi=null;

    @Column(name="tcKimlikNo",nullable= false)
    private String tcKimlikNo=null;

    @Column(name="cinsiyet",nullable= false)
    private Integer cinsiyet=null;

    @Column(name="babaAdi",nullable= false)
    private String babaAdi=null;

    @Column(name="anaAdi",nullable= false)
    private String anaAdi=null;


    @Column(name="dogumTarihi",nullable= false)
    private String dogumTarihi=null;


    @Column(name="meslekTuru",nullable= false)
    private String meslekTuru=null;


    @Column(name="gorevUnvan",nullable= false)
    private String gorevUnvan=null;


    @Column(name="kurumSirket",nullable= false)
    private String kurumSirket=null;



    @Column(name="adiSoyadi",nullable= false)
    private String adiSoyadi=null;

    @Column(name="personelYakini",nullable= false)
    private Boolean personelYakini=null;

    @Column(name="yakinlikDerecesi",nullable= false)
    private String yakinlikDerecesi=null;

    @Column(name="dogumYer",nullable= false)
    private String dogumYer=null;

    @Column(name="seriNo",nullable= false)
    private String seriNo=null;

    @Column(name="cuzdanNo",nullable= false)
    private Integer cuzdanNo=null;

    @Column(name="personelId",nullable= false)
    private Long personelId=null;



    @Column(name="pasaportTur",nullable= false)
    private String pasaportTur=null;

    @Column(name="pasaportNo",nullable= false)
    private Integer pasaportNo=null;


    @Column(name="duzTarihi",nullable= false)
    private String duzTarihi=null;


    @Column(name="gecerlilikTarihi",nullable= false)
    private String gecerlilikTarihi=null;


    @Column(name="sistemKullaniciId",nullable= false)
    private Long sistemKullaniciId=null;



    @Column(name="gunleyenId",nullable= false)
    private Long gunleyenId=null;


    @Column(name="gunlemeZamani",nullable= false)
    private String gunlemeZamani=null;


    @Column(name="mid",nullable= false)
    private Long mid=null;


    @Column(name="mustid",nullable= false)
    private Long mustid=null;







    public Long getGunleyenId() {
        return gunleyenId;
    }

    public void setGunleyenId(Long gunleyenId) {
        this.gunleyenId = gunleyenId;
    }

    public String getGunlemeZamani() {
        return gunlemeZamani;
    }

    public void setGunlemeZamani(String gunlemeZamani) {
        this.gunlemeZamani = gunlemeZamani;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getDogumYeri() {
        return dogumYeri;
    }

    public void setDogumYeri(Long dogumYeri) {
        this.dogumYeri = dogumYeri;
    }

    public Long getUyrugu() {
        return uyrugu;
    }

    public void setUyrugu(Long uyrugu) {
        this.uyrugu = uyrugu;
    }

    public Integer getKisiTur() {
        return kisiTur;
    }

    public void setKisiTur(Integer kisiTur) {
        this.kisiTur = kisiTur;
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

    public String getKimlikNumarasi() {
        return kimlikNumarasi;
    }

    public void setKimlikNumarasi(String kimlikNumarasi) {
        this.kimlikNumarasi = kimlikNumarasi;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public Integer getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(Integer cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getBabaAdi() {
        return babaAdi;
    }

    public void setBabaAdi(String babaAdi) {
        this.babaAdi = babaAdi;
    }

    public String getAnaAdi() {
        return anaAdi;
    }

    public void setAnaAdi(String anaAdi) {
        this.anaAdi = anaAdi;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getMeslekTuru() {
        return meslekTuru;
    }

    public void setMeslekTuru(String meslekTuru) {
        this.meslekTuru = meslekTuru;
    }

    public String getGorevUnvan() {
        return gorevUnvan;
    }

    public void setGorevUnvan(String gorevUnvan) {
        this.gorevUnvan = gorevUnvan;
    }

    public String getKurumSirket() {
        return kurumSirket;
    }

    public void setKurumSirket(String kurumSirket) {
        this.kurumSirket = kurumSirket;
    }

    public String getAdiSoyadi() {
        return adiSoyadi;
    }

    public void setAdiSoyadi(String adiSoyadi) {
        this.adiSoyadi = adiSoyadi;
    }

    public Boolean getPersonelYakini() {
        return personelYakini;
    }

    public void setPersonelYakini(Boolean personelYakini) {
        this.personelYakini = personelYakini;
    }

    public String getYakinlikDerecesi() {
        return yakinlikDerecesi;
    }

    public void setYakinlikDerecesi(String yakinlikDerecesi) {
        this.yakinlikDerecesi = yakinlikDerecesi;
    }

    public String getDogumYer() {
        return dogumYer;
    }

    public void setDogumYer(String dogumYer) {
        this.dogumYer = dogumYer;
    }

    public String getSeriNo() {
        return seriNo;
    }

    public void setSeriNo(String seriNo) {
        this.seriNo = seriNo;
    }

    public Integer getCuzdanNo() {
        return cuzdanNo;
    }

    public void setCuzdanNo(Integer cuzdanNo) {
        this.cuzdanNo = cuzdanNo;
    }

    public Long getPersonelId() {
        return personelId;
    }

    public void setPersonelId(Long personelId) {
        this.personelId = personelId;
    }

    public String getPasaportTur() {
        return pasaportTur;
    }

    public void setPasaportTur(String pasaportTur) {
        this.pasaportTur = pasaportTur;
    }

    public Integer getPasaportNo() {
        return pasaportNo;
    }

    public void setPasaportNo(Integer pasaportNo) {
        this.pasaportNo = pasaportNo;
    }

    public String getDuzTarihi() {
        return duzTarihi;
    }

    public void setDuzTarihi(String duzTarihi) {
        this.duzTarihi = duzTarihi;
    }

    public String getGecerlilikTarihi() {
        return gecerlilikTarihi;
    }

    public void setGecerlilikTarihi(String gecerlilikTarihi) {
        this.gecerlilikTarihi = gecerlilikTarihi;
    }

    public Long getSistemKullaniciId() {
        return sistemKullaniciId;
    }

    public void setSistemKullaniciId(Long sistemKullaniciId) {
        this.sistemKullaniciId = sistemKullaniciId;
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
}
