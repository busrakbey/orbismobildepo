package EntityLayer.Sistem;

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
import EntityLayer.Ortak.BaseEntity;
import EnumsLayer.FetchType;
import EnumsLayer.TemporalType;

/**
 * Created by Ömer YILDIRIM on 9.2.2016.
 */

@Table(name="S_ORG_BIRIM")
public class SOrgBirim extends BaseEntity {
    @Override
    public String toStringName() {
        return getAdi();
    }

    @Column(name="id",nullable= false)
    private Long id = null;

    @Column(name="ust_id",nullable= true)
    private Long ustId = null;

    @Column(name="yol",nullable= true)
    private String yol = null;

    @Column(name="kurum_id",nullable= true)
    private Long kurumId = null;

    @Column(name="adi",nullable= true)
    private String adi = null;

    @Column(name="aktif",nullable= true)
    private Boolean aktif = true;

    @Column(name="kodu",nullable= true)
    private String kodu = null;

    @Column(name="desimal_kodu",nullable= true)
    private String desimalKodu = null;

    @Column(name="dahili_kod",nullable= true)
    private String dahiliKod = null;

    @Column(name="kategori",nullable= true)
    private Integer kategori = null;

    @Column(name="tipi",nullable= true)
    private Integer tipi = null;

    @Column(name="sinifi",nullable= true)
    private Integer sinifi = null;

    @Column(name="ozel_kod_1",nullable= true)
    private String ozelKod1 = null;

    @Column(name="ozel_kod_2",nullable= true)
    private String ozelKod2 = null;

    @Column(name="adres",nullable= true)
    private String adres = null;

    @Column(name="kat_no",nullable= true)
    private String katNo = null;

    @Column(name="oda_no",nullable= true)
    private String odaNo = null;

    @Column(name="telefon_1",nullable= true)
    private String telefon1 = null;

    @Column(name="telefon_2",nullable= true)
    private String telefon2 = null;

    @Column(name="dahili_telefon_1",nullable= true)
    private String dahiliTelefon1 = null;

    @Column(name="dahili_telefon_2",nullable= true)
    private String dahiliTelefon2 = null;

    @Column(name="eposta",nullable= true)
    private String eposta = null;

    @Column(name="yetkili_id",nullable= true)
    private Long yetkiliId = null;

    @Column(name="gunleme_zamani",nullable= true)
    private Date gunlemeZamani = null;

    @Column(name="gunleyen_id",nullable= true)
    private Long gunleyenId = null;

    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;

    @Column(name = "orgId")
    private Long orgId;


    @Column(name = "gonderildi")
    private Integer gonderildi=0;

    public Long getId(){
        return this.id;
    }

    public void setId(final Long id){
        this.id = id;
    }
    @OneToMany(mappedBy="orgBirim", targetEntity=SCalisan.class, fetch= FetchType.LAZY)
    private List<SCalisan> kullanicilar;
    /**
     * Foreign Key : S_CALISAN -> FK_SKULLANICI_orgbirimid
     */
    public List<SCalisan> getKullanicilar(){
        return this.kullanicilar;
    }
    public void setKullanicilar(List<SCalisan> arg){
        this.kullanicilar=arg;
    }



    public Long getUstId(){
        return this.ustId;
    }

    public void setUstId(final Long ustId){
        this.ustId = ustId;
    }



    public String getYol(){
        return this.yol;
    }

    public void setYol(final String yol){
        this.yol = yol;
    }



    public Long getKurumId(){
        return this.kurumId;
    }

    public void setKurumId(final Long kurumId){
        this.kurumId = kurumId;
    }



    public String getAdi(){
        return this.adi;
    }

    public void setAdi(final String adi){
        this.adi = adi;
    }


    public Boolean getAktif(){
        return this.aktif;
    }

    public void setAktif(final Boolean aktif){
        this.aktif = aktif;
    }


    public String getKodu(){
        return this.kodu;
    }

    public void setKodu(final String kodu){
        this.kodu = kodu;
    }


    public String getDesimalKodu(){
        return this.desimalKodu;
    }

    public void setDesimalKodu(final String desimalKodu){
        this.desimalKodu = desimalKodu;
    }

    public String getDahiliKod(){
        return this.dahiliKod;
    }

    public void setDahiliKod(final String dahiliKod){
        this.dahiliKod = dahiliKod;
    }

    public Integer getKategori(){
        return this.kategori;
    }

    public void setKategori(final Integer kategori){
        this.kategori = kategori;
    }


    public Integer getTipi(){
        return this.tipi;
    }

    public void setTipi(final Integer tipi){
        this.tipi = tipi;
    }


    public Integer getSinifi(){
        return this.sinifi;
    }

    public void setSinifi(final Integer sinifi){
        this.sinifi = sinifi;
    }


    public String getOzelKod1(){
        return this.ozelKod1;
    }

    public void setOzelKod1(final String ozelKod1){
        this.ozelKod1 = ozelKod1;
    }


    public String getOzelKod2(){
        return this.ozelKod2;
    }

    public void setOzelKod2(final String ozelKod2){
        this.ozelKod2 = ozelKod2;
    }


    public String getAdres(){
        return this.adres;
    }

    public void setAdres(final String adres){
        this.adres = adres;
    }


    public String getKatNo(){
        return this.katNo;
    }

    public void setKatNo(final String katNo){
        this.katNo = katNo;
    }


    public String getOdaNo(){
        return this.odaNo;
    }

    public void setOdaNo(final String odaNo){
        this.odaNo = odaNo;
    }


    public String getTelefon1(){
        return this.telefon1;
    }

    public void setTelefon1(final String telefon1){
        this.telefon1 = telefon1;
    }


    public String getTelefon2(){
        return this.telefon2;
    }

    public void setTelefon2(final String telefon2){
        this.telefon2 = telefon2;
    }


    public String getDahiliTelefon1(){
        return this.dahiliTelefon1;
    }

    public void setDahiliTelefon1(final String dahiliTelefon1){
        this.dahiliTelefon1 = dahiliTelefon1;
    }


    public String getDahiliTelefon2(){
        return this.dahiliTelefon2;
    }

    public void setDahiliTelefon2(final String dahiliTelefon2){
        this.dahiliTelefon2 = dahiliTelefon2;
    }


    public String getEposta(){
        return this.eposta;
    }

    public void setEposta(final String eposta){
        this.eposta = eposta;
    }

    public Long getYetkiliId(){
        return this.yetkiliId;
    }

    public void setYetkiliId(final Long yetkiliId){
        this.yetkiliId = yetkiliId;
    }


    public Date getGunlemeZamani(){
        return this.gunlemeZamani;
    }

    public void setGunlemeZamani(final Date gunlemeZamani){
        this.gunlemeZamani = gunlemeZamani;
    }


    public Long getGunleyenId(){
        return this.gunleyenId;
    }

    public void setGunleyenId(final Long gunleyenId){
        this.gunleyenId = gunleyenId;
    }

    public SOrgBirim(){
    }

    public SOrgBirim(final Long id, final Long ustId, final String yol, final Long kurumId, final String adi, final Boolean aktif, final String kodu, final String desimalKodu, final String dahiliKod, final Integer kategori, final Integer tipi, final Integer sinifi, final String ozelKod1, final String ozelKod2, final String adres, final String katNo, final String odaNo, final String telefon1, final String telefon2, final String dahiliTelefon1, final String dahiliTelefon2, final String eposta, final Long yetkiliId, final Date gunlemeZamani, final Long gunleyenId){
        this.id = id;
        this.ustId = ustId;
        this.yol = yol;
        this.kurumId = kurumId;
        this.adi = adi;
        this.aktif = aktif;
        this.kodu = kodu;
        this.desimalKodu = desimalKodu;
        this.dahiliKod = dahiliKod;
        this.kategori = kategori;
        this.tipi = tipi;
        this.sinifi = sinifi;
        this.ozelKod1 = ozelKod1;
        this.ozelKod2 = ozelKod2;
        this.adres = adres;
        this.katNo = katNo;
        this.odaNo = odaNo;
        this.telefon1 = telefon1;
        this.telefon2 = telefon2;
        this.dahiliTelefon1 = dahiliTelefon1;
        this.dahiliTelefon2 = dahiliTelefon2;
        this.eposta = eposta;
        this.yetkiliId = yetkiliId;
        this.gunlemeZamani = gunlemeZamani;
        this.gunleyenId = gunleyenId;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof SOrgBirim)) return false;
        if (id==null)
            return false;
        return  id.equals(((SOrgBirim) o).id);
    }

    @Override
    public int hashCode() {
        return id!=null ? toString().hashCode() : super.hashCode();
    }

    public String toString() {
        return "[S_ORG_BIRIM]#" + id;
    }

    private static final long serialVersionUID = 1L;

    public static Map<Class, String> getClassAndTableName() {
        Map<Class,String> m = new HashMap<Class, String>();
        m.put(SOrgBirim.class, "S_ORG_BIRIM");
        return m;
    }
    @Override
    public boolean hasProxyDefinitions(){
        return true;
    }

    @Override
    public Map<String, Object> getLazyCollectionRawValues(){
        Map<String, Object> vals = new HashMap<String, Object>();
        return vals;
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

    ////Mobile Fields End

}

/*


* */