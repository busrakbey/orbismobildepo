package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 17.1.2017.
 */

@Table(name = "ORTAK_NOT")
public class OrtakNot extends BaseEntity{


    @Column(name = "id")
    private Long id;

    @Column(name = "isKalemId")
    private Long isKalemId;

    @Column(name = "personelId")
    private Long personelId;


    @Column(name = "notKonuId")
    private Long notKonuId;


    @Column(name = "aktif")
    private Boolean aktif;

    @Column(name = "notAlinmaTarihi")
    private String notAlinmaTarihi;

    @Column(name = "notAciklama")
    private String notAciklama;

    @Column(name = "gunlemeZamani", nullable = true)
    private String gunlemeZamani	= null;

    @Column(name = "gunleyenId", nullable = true)
    private Long gunleyenId	= null;


    @Column(name = "modulId")
    private Integer modulId;

    @Column(name = "guzergahId")
    private Integer guzergahId;


    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;


    @Column(name = "ilgiliBirimId")
    private Long ilgiliBirimId;

    @Column(name = "OBJECTID", nullable = true)
    private Long OBJECTID;

    @Column(name = "yolKod")
    private String yolKod;

    @Column(name = "yolAdi")
    private String yolAdi;

    @Column(name = "yolKategori")
    private Integer yolKategori;

    public Integer getYolKategori() {
        return yolKategori;
    }

    public void setYolKategori(Integer yolKategori) {
        this.yolKategori = yolKategori;
    }

    public String getYolKod() {
        return yolKod;
    }

    public void setYolKod(String yolKod) {
        this.yolKod = yolKod;
    }

    public String getYolAdi() {
        return yolAdi;
    }

    public void setYolAdi(String yolAdi) {
        this.yolAdi = yolAdi;
    }

    public Long getIlgiliBirimId() {
        return ilgiliBirimId;
    }

    public void setIlgiliBirimId(Long ilgiliBirimId) {
        this.ilgiliBirimId = ilgiliBirimId;
    }

    @Override
    public Long getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(Long OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public Long getBirimId() {
        return ilgiliBirimId;
    }

    public void setBirimId(Long birimId) {
        this.ilgiliBirimId = birimId;
    }

    @Override
    public String toStringName() {
        return getNotAciklama();
    }


    public Integer getModulId() {
        return modulId;
    }

    public void setModulId(Integer modulId) {
        this.modulId = modulId;
    }

    public Integer getGuzergahId() {
        return guzergahId;
    }

    public void setGuzergahId(Integer guzergahId) {
        this.guzergahId = guzergahId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsKalemId() {
        return isKalemId;
    }

    public void setIsKalemId(Long isKalemId) {
        this.isKalemId = isKalemId;
    }

    public Long getPersonelId() {
        return personelId;
    }

    public void setPersonelId(Long personelId) {
        this.personelId = personelId;
    }

    public Long getNotKonuId() {
        return notKonuId;
    }

    public void setNotKonuId(Long notKonuId) {
        this.notKonuId = notKonuId;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public String getNotAlinmaTarihi() {
        return notAlinmaTarihi;
    }

    public void setNotAlinmaTarihi(String notAlinmaTarihi) {
        this.notAlinmaTarihi = notAlinmaTarihi;
    }

    public String getNotAciklama() {
        return notAciklama;
    }

    public void setNotAciklama(String notAciklama) {
        this.notAciklama = notAciklama;
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
