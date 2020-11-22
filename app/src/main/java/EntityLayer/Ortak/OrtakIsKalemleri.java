package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 17.1.2017.
 */

@Table(name = "ORTAK_IS_KALEMLERI")
public class OrtakIsKalemleri extends BaseEntity{

    @Column(name = "id")
    private Long id;

    @Column(name = "isKalemTanim")
    private String isKalemTanim;

    @Column(name = "isKalemKategori")
    private String isKalemKategori;


    @Column(name = "ilgiliBirimId")
    private Long ilgiliBirimId;


    @Column(name = "kodu")
    private String kodu;

    @Column(name = "olusturulmaTarihi")
    private String olusturulmaTarihi;

    @Column(name = "bitisTarihi")
    private String bitisTarihi;

    @Column(name = "aktif")
    private Boolean aktif;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "gunlemeZamani", nullable = true)
    private String gunlemeZamani	= null;

    @Column(name = "gunleyenId", nullable = true)
    private Long gunleyenId	= null;

    @Column(name = "modulId")
    private Integer modulId;


    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getIsKalemTanim() {
        return isKalemTanim;
    }

    public void setIsKalemTanim(String isKalemTanim) {
        this.isKalemTanim = isKalemTanim;
    }

    public String getIsKalemKategori() {
        return isKalemKategori;
    }

    public void setIsKalemKategori(String isKalemKategori) {
        this.isKalemKategori = isKalemKategori;
    }

    public Long getIlgiliBirimId() {
        return ilgiliBirimId;
    }

    public void setIlgiliBirimId(Long ilgiliBirimId) {
        this.ilgiliBirimId = ilgiliBirimId;
    }

    public String getKodu() {
        return kodu;
    }

    public void setKodu(String kodu) {
        this.kodu = kodu;
    }

    public String getOlusturulmaTarihi() {
        return olusturulmaTarihi;
    }

    public void setOlusturulmaTarihi(String olusturulmaTarihi) {
        this.olusturulmaTarihi = olusturulmaTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getGunlemeZamani() {
        return gunlemeZamani;
    }

    public void setGunlemeZamani(String gunlemeZamani) {
        this.gunlemeZamani = gunlemeZamani;
    }

    public Long getGunleyenId() {
        return gunleyenId;
    }

    public void setGunleyenId(Long gunleyenId) {
        this.gunleyenId = gunleyenId;
    }

    public Integer getModulId() {
        return modulId;
    }

    public void setModulId(Integer modulId) {
        this.modulId = modulId;
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
