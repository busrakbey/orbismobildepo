package EntityLayer.Sistem; /*******************************************************************************
 * This file is generated by JavExpress Studio DB Generator
 * This file is generated at Tue Dec 01 14:14:24 EET 2015
 ******************************************************************************/

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
import EntityLayer.Ortak.BaseEntity;
import EnumsLayer.FetchType;
import EnumsLayer.TemporalType;


@Table(name="S_CALISAN")
public class SCalisan extends BaseEntity {
    @Override
    public String toStringName() {
        return getAdiSoyadi();
    }

    @Column(name="id",nullable= false)
    private Long id = null;

    public Long getId(){
        return this.id;
    }

    public void setId(final Long id){
        this.id = id;
    }

    @Column(name="adi",nullable= false)
    private String adi = null;

    public String getAdi(){
        return this.adi;
    }

    public void setAdi(final String adi){
        this.adi = adi;
    }
    @Column(name="soyadi",nullable= false)
    private String soyadi = null;

    public String getSoyadi(){
        return this.soyadi;
    }

    public void setSoyadi(final String soyadi){
        this.soyadi = soyadi;
    }
    @Column(name="adi_soyadi",nullable= false)
    private String adiSoyadi = null;

    public String getAdiSoyadi(){
        return this.adiSoyadi;
    }

    public void setAdiSoyadi(final String adiSoyadi){
        this.adiSoyadi = adiSoyadi;
    }
    @Column(name="cinsiyet",nullable= true)
    private Integer cinsiyet = Integer.valueOf(1);

    public Integer getCinsiyet(){
        return this.cinsiyet;
    }

    public void setCinsiyet(final Integer cinsiyet){
        this.cinsiyet = cinsiyet;
    }
    @Column(name="dogum_tarihi",nullable= true)
    private Date dogumTarihi = null;

    public Date getDogumTarihi(){
        return this.dogumTarihi;
    }

    public void setDogumTarihi(final Date dogumTarihi){
        this.dogumTarihi = dogumTarihi;
    }
    @Column(name="aktif",nullable= false)
    private Boolean aktif = true;

    public Boolean getAktif(){
        return this.aktif;
    }

    public void setAktif(final Boolean aktif){
        this.aktif = aktif;
    }
    @Column(name="ldap_adresi",nullable= true)
    private String ldapAdresi = null;

    public String getLdapAdresi(){
        return this.ldapAdresi;
    }

    public void setLdapAdresi(final String ldapAdresi){
        this.ldapAdresi = ldapAdresi;
    }
    @Column(name="org_birim_id",nullable= true)
    private Long orgBirimId = null;

    public Long getOrgBirimId(){
        return this.orgBirimId;
    }

    public void setOrgBirimId(final Long orgBirimId){
        this.orgBirimId = orgBirimId;
    }
    @ManyToOne(fetch = FetchType.LAZY,optional=true)
    @JoinColumn(name="org_birim_id",insertable=false,updatable=false,nullable=true)
    private SOrgBirim orgBirim;
    /**
     * Foreign Key : FK_SKULLANICI_orgbirimid
     */
    public SOrgBirim getOrgBirim(){
        return this.orgBirim;
    }
    public void setOrgBirim(SOrgBirim arg){
        this.orgBirim=arg;
    }

    @Column(name="unvan_id",nullable= true)
    private Long unvanId = null;

    public Long getUnvanId(){
        return this.unvanId;
    }

    public void setUnvanId(final Long unvanId){
        this.unvanId = unvanId;
    }


    @Column(name="akademik_unvan",nullable= true)
    private Integer akademikUnvan = null;

    public Integer getAkademikUnvan(){
        return this.akademikUnvan;
    }

    public void setAkademikUnvan(final Integer akademikUnvan){
        this.akademikUnvan = akademikUnvan;
    }
    @Column(name="dahili_kod",nullable= true)
    private String dahiliKod = null;

    public String getDahiliKod(){
        return this.dahiliKod;
    }

    public void setDahiliKod(final String dahiliKod){
        this.dahiliKod = dahiliKod;
    }
    @Column(name="ozel_kod_1",nullable= true)
    private String ozelKod1 = null;

    public String getOzelKod1(){
        return this.ozelKod1;
    }

    public void setOzelKod1(final String ozelKod1){
        this.ozelKod1 = ozelKod1;
    }
    @Column(name="ozel_kod_2",nullable= true)
    private String ozelKod2 = null;

    public String getOzelKod2(){
        return this.ozelKod2;
    }

    public void setOzelKod2(final String ozelKod2){
        this.ozelKod2 = ozelKod2;
    }
    @Column(name="web_sitesi",nullable= true)
    private String webSitesi = null;

    public String getWebSitesi(){
        return this.webSitesi;
    }

    public void setWebSitesi(final String webSitesi){
        this.webSitesi = webSitesi;
    }
    @Column(name="eposta",nullable= true)
    private String eposta = null;

    public String getEposta(){
        return this.eposta;
    }

    public void setEposta(final String eposta){
        this.eposta = eposta;
    }
    @Column(name="gsm1",nullable= true)
    private String gsm1 = null;

    public String getGsm1(){
        return this.gsm1;
    }

    public void setGsm1(final String gsm1){
        this.gsm1 = gsm1;
    }
    @Column(name="gsm2",nullable= true)
    private String gsm2 = null;

    public String getGsm2(){
        return this.gsm2;
    }

    public void setGsm2(final String gsm2){
        this.gsm2 = gsm2;
    }
    @Column(name="telefon1",nullable= true)
    private String telefon1 = null;

    public String getTelefon1(){
        return this.telefon1;
    }

    public void setTelefon1(final String telefon1){
        this.telefon1 = telefon1;
    }
    @Column(name="telefon2",nullable= true)
    private String telefon2 = null;

    public String getTelefon2(){
        return this.telefon2;
    }

    public void setTelefon2(final String telefon2){
        this.telefon2 = telefon2;
    }
    @Column(name="tckimlik_no",nullable= true)
    private String tckimlikNo = null;

    public String getTckimlikNo(){
        return this.tckimlikNo;
    }

    public void setTckimlikNo(final String tckimlikNo){
        this.tckimlikNo = tckimlikNo;
    }
    @Temporal(TemporalType.TIMESTAMP)    @Column(name="gunleme_zamani",nullable= false)
    private Date gunlemeZamani = null;

    public Date getGunlemeZamani(){
        return this.gunlemeZamani;
    }

    public void setGunlemeZamani(final Date gunlemeZamani){
        this.gunlemeZamani = gunlemeZamani;
    }
    @Column(name="gunleyen_id",nullable= true)
    private Long gunleyenId = null;

    public Long getGunleyenId(){
        return this.gunleyenId;
    }

    public void setGunleyenId(final Long gunleyenId){
        this.gunleyenId = gunleyenId;
    }

    public SCalisan(){
    }

    public SCalisan(final Long id,final String adi,final String soyadi,final String adiSoyadi,final Integer cinsiyet,final Date dogumTarihi,final Boolean aktif,final String ldapAdresi,final Long orgBirimId,final Long unvanId,final Integer akademikUnvan,final String dahiliKod,final String ozelKod1,final String ozelKod2,final String webSitesi,final String eposta,final String gsm1,final String gsm2,final String telefon1,final String telefon2,final String tckimlikNo,final Date gunlemeZamani,final Long gunleyenId){
        this.id = id;
        this.adi = adi;
        this.soyadi = soyadi;
        this.adiSoyadi = adiSoyadi;
        this.cinsiyet = cinsiyet;
        this.dogumTarihi = dogumTarihi;
        this.aktif = aktif;
        this.ldapAdresi = ldapAdresi;
        this.orgBirimId = orgBirimId;
        this.unvanId = unvanId;
        this.akademikUnvan = akademikUnvan;
        this.dahiliKod = dahiliKod;
        this.ozelKod1 = ozelKod1;
        this.ozelKod2 = ozelKod2;
        this.webSitesi = webSitesi;
        this.eposta = eposta;
        this.gsm1 = gsm1;
        this.gsm2 = gsm2;
        this.telefon1 = telefon1;
        this.telefon2 = telefon2;
        this.tckimlikNo = tckimlikNo;
        this.gunlemeZamani = gunlemeZamani;
        this.gunleyenId = gunleyenId;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof SCalisan)) return false;
        if (id==null)
            return false;
        return  id.equals(((SCalisan) o).id);
    }

    @Override
    public int hashCode() {
        return id!=null ? toString().hashCode() : super.hashCode();
    }

    public String toString() {
        return "[S_CALISAN]#" + id;
    }

    private static final long serialVersionUID = 1L;

    public static Map<Class, String> getClassAndTableName() {
        Map<Class,String> m = new HashMap<Class, String>();
        m.put(SCalisan.class, "S_CALISAN");
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

    ////Mobile Fields End

}
/*


* */