package EntityLayer.Ortak;


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
 * Created by Ömer YILDIRIM on 2.2.2016.
 */
@Table(name = "ORTAK_AGAC_TURU")
public class OrtakAgacTuru extends BaseEntity{

    @Override
    public String toStringName() {
        return getAgacAdi();
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


    @Column(name = "enabled", nullable = false)
    private Integer enabled=0;


    @Column(name = "secimDate")
    private String secimDate;


    @Column(name = "id", nullable = false)
    private Long	id	= null;

    @Column(name = "ust_id", nullable = true)
    private Long	ustId	= null;
    @Column(name = "agac_cinsi_id", nullable = false)
    private Long	agacCinsiId	= null;
    @Column(name = "tur", nullable = true)
    private String	tur	= null;
    @Column(name = "yol", nullable = false)
    private String	yol	= null;
    @Column(name = "rumuz", nullable = true)
    private Long	rumuz	= null;
    @Column(name = "agac_adi", nullable = false)
    private String	agacAdi	= null;
    @Column(name = "kategori", nullable = true)
    private String	kategori	= null;
    @Column(name = "latin_adi", nullable = true)
    private String	latinAdi	= null;
    @Column(name = "ingilizce_adi", nullable = true)
    private String	ingilizceAdi	= null;
    @Column(name = "diger_dil_adi", nullable = true)
    private String	digerDilAdi	= null;
    @Column(name = "aktif", nullable = true)
    private Boolean	aktif	= true;
    @Column(name = "zehirli_sivi_gaz_salgilar", nullable = true)
    private Boolean	zehirliSiviGazSalgilar	= null;
    @Column(name = "kururken_ceker", nullable = true)
    private Boolean	kururkenCeker	= null;
    @Column(name = "bozulma_suresi", nullable = true)
    private Integer	bozulmaSuresi	= null;
    @Column(name = "kisa_kod", nullable = false)
    private String	kisaKod	= null;
    @Column(name = "fiziki_etkilere_dayanimi", nullable = true)
    private String	fizikiEtkilereDayanimi	= null;
    @Column(name = "kesim_zorlugu", nullable = true)
    private String	kesimZorlugu	= null;
    @Column(name = "ortalama_boy", nullable = true)
    private Integer	ortalamaBoy	= null;
    @Column(name = "kabugu_soyulacak", nullable = true)
    private Boolean	kabuguSoyulacak	= null;
    @Column(name = "amenajman_sira", nullable = true)
    private Long	amenajmanSira	= null;
    @Column(name = "gunleme_zamani", nullable = false)
    private Date	gunlemeZamani	= null;
    @Column(name = "gunleyen_id", nullable = false)
    private Long	gunleyenId	= null;

    public String getSecimDate() {
        return secimDate;
    }

    public void setSecimDate(String secimDate) {
        this.secimDate = secimDate;
    }

    public Long getUstId() {
        return this.ustId;
    }

    public void setUstId(final Long ustId) {
        this.ustId = ustId;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ust_id", insertable = false, updatable = false, nullable = true)
    private OrtakAgacTuru agacTuruId;

    /**
     * Foreign Key : FK_ORTAKAGACTURU_ustid
     */
    public OrtakAgacTuru getAgacTuruId() {
        return this.agacTuruId;
    }

    public void setAgacTuruId(OrtakAgacTuru arg) {
        this.agacTuruId = arg;
    }



    public Long getAgacCinsiId() {
        return this.agacCinsiId;
    }

    public void setAgacCinsiId(final Long agacCinsiId) {
        this.agacCinsiId = agacCinsiId;
    }




    public String getTur() {
        return this.tur;
    }

    public void setTur(final String tur) {
        this.tur = tur;
    }



    public String getYol() {
        return this.yol;
    }

    public void setYol(final String yol) {
        this.yol = yol;
    }



    public Long getRumuz() {
        return this.rumuz;
    }

    public void setRumuz(final Long rumuz) {
        this.rumuz = rumuz;
    }



    public String getAgacAdi() {
        return this.agacAdi;
    }

    public void setAgacAdi(final String agacAdi) {
        this.agacAdi = agacAdi;
    }



    public String getKategori() {
        return this.kategori;
    }

    public void setKategori(final String kategori) {
        this.kategori = kategori;
    }



    public String getLatinAdi() {
        return this.latinAdi;
    }

    public void setLatinAdi(final String latinAdi) {
        this.latinAdi = latinAdi;
    }



    public String getIngilizceAdi() {
        return this.ingilizceAdi;
    }

    public void setIngilizceAdi(final String ingilizceAdi) {
        this.ingilizceAdi = ingilizceAdi;
    }



    public String getDigerDilAdi() {
        return this.digerDilAdi;
    }

    public void setDigerDilAdi(final String digerDilAdi) {
        this.digerDilAdi = digerDilAdi;
    }



    public Boolean getAktif() {
        return this.aktif;
    }

    public void setAktif(final Boolean aktif) {
        this.aktif = aktif;
    }



    public Boolean getZehirliSiviGazSalgilar() {
        return this.zehirliSiviGazSalgilar;
    }

    public void setZehirliSiviGazSalgilar(final Boolean zehirliSiviGazSalgilar) {
        this.zehirliSiviGazSalgilar = zehirliSiviGazSalgilar;
    }


    public Boolean getKururkenCeker() {
        return this.kururkenCeker;
    }

    public void setKururkenCeker(final Boolean kururkenCeker) {
        this.kururkenCeker = kururkenCeker;
    }



    public Integer getBozulmaSuresi() {
        return this.bozulmaSuresi;
    }

    public void setBozulmaSuresi(final Integer bozulmaSuresi) {
        this.bozulmaSuresi = bozulmaSuresi;
    }

    public String getKisaKod() {
        return this.kisaKod;
    }

    public void setKisaKod(final String kisaKod) {
        this.kisaKod = kisaKod;
    }




    public String getFizikiEtkilereDayanimi() {
        return this.fizikiEtkilereDayanimi;
    }

    public void setFizikiEtkilereDayanimi(final String fizikiEtkilereDayanimi) {
        this.fizikiEtkilereDayanimi = fizikiEtkilereDayanimi;
    }


    public String getKesimZorlugu() {
        return this.kesimZorlugu;
    }

    public void setKesimZorlugu(final String kesimZorlugu) {
        this.kesimZorlugu = kesimZorlugu;
    }


    public Integer getOrtalamaBoy() {
        return this.ortalamaBoy;
    }

    public void setOrtalamaBoy(final Integer ortalamaBoy) {
        this.ortalamaBoy = ortalamaBoy;
    }


    public Boolean getKabuguSoyulacak() {
        return this.kabuguSoyulacak;
    }

    public void setKabuguSoyulacak(final Boolean kabuguSoyulacak) {
        this.kabuguSoyulacak = kabuguSoyulacak;
    }


    public Long getAmenajmanSira() {
        return this.amenajmanSira;
    }

    public void setAmenajmanSira(final Long amenajmanSira) {
        this.amenajmanSira = amenajmanSira;
    }



    public Date getGunlemeZamani() {
        return this.gunlemeZamani;
    }

    public void setGunlemeZamani(final Date gunlemeZamani) {
        this.gunlemeZamani = gunlemeZamani;
    }

    public Long getGunleyenId() {
        return this.gunleyenId;
    }

    public void setGunleyenId(final Long gunleyenId) {
        this.gunleyenId = gunleyenId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gunleyen_id", insertable = false, updatable = false)
    private SKullanici gunleyenDtoNickName;

    /**
     * Foreign Key : FK_ORTAKAGACTURU_gunleyenid
     */
    public SKullanici getGunleyenDtoNickName() {
        return this.gunleyenDtoNickName;
    }

    public void setGunleyenDtoNickName(SKullanici arg) {
        this.gunleyenDtoNickName = arg;
    }

    public OrtakAgacTuru() {
    }

    public OrtakAgacTuru(final Long id, final Long ustId, final Long agacCinsiId, final String tur, final String yol, final Long rumuz, final String agacAdi, final String kategori, final String latinAdi, final String ingilizceAdi, final String digerDilAdi, final Boolean aktif, final Boolean zehirliSiviGazSalgilar, final Boolean kururkenCeker, final Integer bozulmaSuresi, final String kisaKod, final String fizikiEtkilereDayanimi, final String kesimZorlugu, final Integer ortalamaBoy, final Boolean kabuguSoyulacak, final Long amenajmanSira, final Date gunlemeZamani, final Long gunleyenId) {
        this.id = id;
        this.ustId = ustId;
        this.agacCinsiId = agacCinsiId;
        this.tur = tur;
        this.yol = yol;
        this.rumuz = rumuz;
        this.agacAdi = agacAdi;
        this.kategori = kategori;
        this.latinAdi = latinAdi;
        this.ingilizceAdi = ingilizceAdi;
        this.digerDilAdi = digerDilAdi;
        this.aktif = aktif;
        this.zehirliSiviGazSalgilar = zehirliSiviGazSalgilar;
        this.kururkenCeker = kururkenCeker;
        this.bozulmaSuresi = bozulmaSuresi;
        this.kisaKod = kisaKod;
        this.fizikiEtkilereDayanimi = fizikiEtkilereDayanimi;
        this.kesimZorlugu = kesimZorlugu;
        this.ortalamaBoy = ortalamaBoy;
        this.kabuguSoyulacak = kabuguSoyulacak;
        this.amenajmanSira = amenajmanSira;
        this.gunlemeZamani = gunlemeZamani;
        this.gunleyenId = gunleyenId;
    }


    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OrtakAgacTuru))
            return false;
        if (id == null)
            return false;
        return id.equals(((OrtakAgacTuru) o).id);
    }

    @Override
    public int hashCode() {
        return id != null ? toString().hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return agacAdi;
    }

    private static final long	serialVersionUID	= 1L;

    public static Map<Class, String> getClassAndTableName() {
        Map<Class,String> m = new HashMap<Class, String>();
        m.put(OrtakAgacTuru.class, "ORTAK_AGAC_TURU");
        return m;
    }
    @Override
    public boolean hasProxyDefinitions() {
        return true;
    }

    @Override
    public Map<String, Object> getLazyCollectionRawValues() {
        Map<String, Object> vals = new HashMap<String, Object>();

        return vals;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
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

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    ////Mobile Fields End
}
/*
    public static Map<Class, String> getClassAndTableName() {
        Map<Class,String> m = new HashMap<Class, String>();
        m.put(OrtakAgacTuru.class, "ORTAK_AGAC_TURU");
        return m;
    }

    */