package EntityLayer.Sistem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import AnnotationLayer.Column;
import AnnotationLayer.Table;
import AnnotationLayer.Temporal;
import EntityLayer.Ortak.BaseEntity;
import EnumsLayer.TemporalType;

/**
 * Created by Konumsal PC11 on 10.5.2016.
 */
@Table(name="S_MODUL_KOD_DEGER")
public class SModulKodDeger extends BaseEntity {

    @Override
    public String toStringName() {
        return getDeger();
    }

    @Column(name="id",nullable= false)
    private Long id = null;

    public Long getId(){
        return this.id;
    }

    public void setId(final Long id){
        this.id = id;
    }
    @Column(name="kurum_id",nullable= false)
    private Long kurumId = null;

    public Long getKurumId(){
        return this.kurumId;
    }

    public void setKurumId(final Long kurumId){
        this.kurumId = kurumId;
    }
    @Column(name="modul_id",nullable= false)
    private Long modulId = null;

    public Long getModulId(){
        return this.modulId;
    }

    public void setModulId(final Long modulId){
        this.modulId = modulId;
    }
    @Column(name="dil",nullable= false)
    private String dil = null;

    public String getDil(){
        return this.dil;
    }

    public void setDil(final String dil){
        this.dil = dil;
    }
    @Column(name="grup",nullable= false)
    private String grup = null;

    public String getGrup(){
        return this.grup;
    }

    public void setGrup(final String grup){
        this.grup = grup;
    }
    @Column(name="alt_grup",nullable= true)
    private String altGrup = null;

    public String getAltGrup(){
        return this.altGrup;
    }

    public void setAltGrup(final String altGrup){
        this.altGrup = altGrup;
    }
    @Column(name="kod",nullable= false)
    private String kod = null;

    public String getKod(){
        return this.kod;
    }

    public void setKod(final String kod){
        this.kod = kod;
    }
    @Column(name="sira",nullable= false)
    private Short sira = Short.valueOf("0");

    public Short getSira(){
        return this.sira;
    }

    public void setSira(final Short sira){
        this.sira = sira;
    }
    @Column(name="aktif",nullable= false)
    private Boolean aktif = true;

    public Boolean getAktif(){
        return this.aktif;
    }

    public void setAktif(final Boolean aktif){
        this.aktif = aktif;
    }
    @Column(name="deger",nullable= true)
    private String deger = null;

    public String getDeger(){
        return this.deger;
    }

    public void setDeger(final String deger){
        this.deger = deger;
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
    @Temporal(TemporalType.TIMESTAMP)    @Column(name="gunleme_zamani",nullable= false)
    private Date gunlemeZamani = null;

    public Date getGunlemeZamani(){
        return this.gunlemeZamani;
    }

    public void setGunlemeZamani(final Date gunlemeZamani){
        this.gunlemeZamani = gunlemeZamani;
    }
    @Column(name="gunleyen_id",nullable= false)
    private Long gunleyenId = null;

    public Long getGunleyenId(){
        return this.gunleyenId;
    }

    public void setGunleyenId(final Long gunleyenId){
        this.gunleyenId = gunleyenId;
    }
    @Column(name="ipucu",nullable= true)
    private String ipucu = null;

    public String getIpucu(){
        return this.ipucu;
    }

    public void setIpucu(final String ipucu){
        this.ipucu = ipucu;
    }

    public SModulKodDeger(){
    }

    public SModulKodDeger(final Long id,final Long kurumId,final Long modulId,final String dil,final String grup,final String altGrup,final String kod,final Short sira,final Boolean aktif,final String deger,final String ozelKod1,final String ozelKod2,final Date gunlemeZamani,final Long gunleyenId,final String ipucu){
        this.id = id;
        this.kurumId = kurumId;
        this.modulId = modulId;
        this.dil = dil;
        this.grup = grup;
        this.altGrup = altGrup;
        this.kod = kod;
        this.sira = sira;
        this.aktif = aktif;
        this.deger = deger;
        this.ozelKod1 = ozelKod1;
        this.ozelKod2 = ozelKod2;
        this.gunlemeZamani = gunlemeZamani;
        this.gunleyenId = gunleyenId;
        this.ipucu = ipucu;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof SModulKodDeger )) return false;
        if (id==null)
            return false;
        return  id.equals(((SModulKodDeger) o).id);
    }

    @Override
    public int hashCode() {
        return id!=null ? toString().hashCode() : super.hashCode();
    }

    public String toString() {
        return "[S_MODUL_KOD_DEGER]#" + id;
    }

    private static final long serialVersionUID = 1L;

    public static Map<Class, String> getClassAndTableName() {
        Map<Class,String> m = new HashMap<Class, String>();
        m.put(SModulKodDeger.class, "S_MODUL_KOD_DEGER");
        return m;
    }
    @Override
    public boolean hasProxyDefinitions(){
        return  false;
    }

    @Override
    public Map<String, Object> getLazyCollectionRawValues(){
        Map<String, Object> vals = new java.util.HashMap<String, Object>();
        return vals;
    }

    public String getV(){
        return kod;
    }

    public String getL(){
        return deger;
    }

    public String getG(){
        return null;
    }

    public String getD(){
        return null;
    }

    public String getH(){
        return ipucu;
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
