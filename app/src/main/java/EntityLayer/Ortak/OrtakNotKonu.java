package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 17.1.2017.
 */

@Table(name = "ORTAK_NOT_KONU")
public class OrtakNotKonu extends BaseEntity{

    @Column(name = "id")
    private Long id;

    @Column(name = "ustId")
    private Long ustId;

    @Column(name = "konuBasligi")
    private String konuBasligi;


    @Column(name = "aciklama")
    private String aciklama;


    @Column(name = "yol")
    private String yol;

    @Column(name = "aktif")
    private Boolean aktif;


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

    public Long getUstId() {
        return ustId;
    }

    public void setUstId(Long ustId) {
        this.ustId = ustId;
    }

    public String getKonuBasligi() {
        return konuBasligi;
    }

    public void setKonuBasligi(String konuBasligi) {
        this.konuBasligi = konuBasligi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getYol() {
        return yol;
    }

    public void setYol(String yol) {
        this.yol = yol;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
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
