package EntityLayer.Ortak;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 30.6.2017.
 */

@Table(name = "ORTAK_KAMERA")
public class OrtakKamera extends BaseEntity {

    @Column(name = "id", nullable = false)
    private Long	id	= null;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "bolge_mudurluk_id", nullable = true)
    private Long	bolgeMudurlukId	= null;

    public Long getBolgeMudurlukId() {
        return this.bolgeMudurlukId;
    }

    public void setBolgeMudurlukId(final Long bolgeMudurlukId) {
        this.bolgeMudurlukId = bolgeMudurlukId;
    }



    @Column(name = "isletme_mudurluk_id", nullable = true)
    private Long	isletmeMudurlukId	= null;

    public Long getIsletmeMudurlukId() {
        return this.isletmeMudurlukId;
    }

    public void setIsletmeMudurlukId(final Long isletmeMudurlukId) {
        this.isletmeMudurlukId = isletmeMudurlukId;
    }



    @Column(name = "isletme_seflik_id", nullable = true)
    private Long	isletmeSeflikId	= null;

    public Long getIsletmeSeflikId() {
        return this.isletmeSeflikId;
    }

    public void setIsletmeSeflikId(final Long isletmeSeflikId) {
        this.isletmeSeflikId = isletmeSeflikId;
    }



    @Column(name = "il_id", nullable = true)
    private Long	ilId	= null;

    public Long getIlId() {
        return this.ilId;
    }

    public void setIlId(final Long ilId) {
        this.ilId = ilId;
    }


    @Column(name = "ilce_id", nullable = true)
    private Long	ilceId	= null;

    public Long getIlceId() {
        return this.ilceId;
    }

    public void setIlceId(final Long ilceId) {
        this.ilceId = ilceId;
    }


    @Column(name = "kamera_turu", nullable = true)
    private Integer	kameraTuru	= null;

    /**
     * Hareketli/Sabit
     */
    public Integer getKameraTuru() {
        return this.kameraTuru;
    }

    /**
     * Hareketli/Sabit
     */
    public void setKameraTuru(final Integer kameraTuru) {
        this.kameraTuru = kameraTuru;
    }

    @Column(name = "kamera_marka", nullable = true)
    private String	kameraMarka	= null;

    public String getKameraMarka() {
        return this.kameraMarka;
    }

    public void setKameraMarka(final String kameraMarka) {
        this.kameraMarka = kameraMarka;
    }

    @Column(name = "kamera_model", nullable = true)
    private String	kameraModel	= null;

    public String getKameraModel() {
        return this.kameraModel;
    }

    public void setKameraModel(final String kameraModel) {
        this.kameraModel = kameraModel;
    }

    @Column(name = "kamera_adi", nullable = true)
    private String	kameraAdi	= null;

    public String getKameraAdi() {
        return this.kameraAdi;
    }

    public void setKameraAdi(final String kameraAdi) {
        this.kameraAdi = kameraAdi;
    }

    @Column(name = "kamera_url", nullable = true)
    private String	kameraUrl	= null;

    public String getKameraUrl() {
        return this.kameraUrl;
    }

    public void setKameraUrl(final String kameraUrl) {
        this.kameraUrl = kameraUrl;
    }

    @Column(name = "kullanici_adi", nullable = true)
    private String	kullaniciAdi	= null;

    public String getKullaniciAdi() {
        return this.kullaniciAdi;
    }

    public void setKullaniciAdi(final String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    @Column(name = "sifre", nullable = true)
    private String	sifre	= null;

    public String getSifre() {
        return this.sifre;
    }

    public void setSifre(final String sifre) {
        this.sifre = sifre;
    }

    @Column(name = "tasinmaz_id", nullable = true)
    private Long	tasinmazId	= null;

    /**
     * kuleye baglanan kameralar icin
     */
    public Long getTasinmazId() {
        return this.tasinmazId;
    }

    /**
     * kuleye baglanan kameralar icin
     */
    public void setTasinmazId(final Long tasinmazId) {
        this.tasinmazId = tasinmazId;
    }

    @Column(name = "modul_id", nullable = true)
    private Long	modulId	= null;

    public Long getModulId() {
        return this.modulId;
    }

    public void setModulId(final Long modulId) {
        this.modulId = modulId;
    }

    @Column(name = "mid", nullable = true)
    private Long	mid	= null;


    @Column(name = "mustid", nullable = true)
    private Long	mustid	= null;

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
